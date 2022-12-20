package name.bychkov.test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

@Startup
@Singleton
public class Checker {
	@Resource
	ManagedScheduledExecutorService scheduler;

	private static List<URI> serverUrls = Arrays.asList(
			URI.create("https://bvfalcon.github.io/wildfly-http-client-async-test/test1.json"),
			URI.create("https://bvfalcon.github.io/wildfly-http-client-async-test/test2.json"));

	@PostConstruct
	public void initialize() {
		scheduler.schedule(this::tick, 0L, TimeUnit.SECONDS);
	}

	public void tick() {
		System.out.println("Timer started. Current time: " + LocalTime.now());
		CompletableFuture<Void>[] futures = new CompletableFuture[serverUrls.size()];
		for (int i = 0; i < serverUrls.size(); i++) {
			URI url = serverUrls.get(i);
			futures[i] = call(url).thenApplyAsync(response -> {
				try {
					String json = response.body();
					System.out.println("raw json: " + json);
					List<SimpleDto> parsed = parse(json);
					System.out.println("parsed: " + parsed);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}, scheduler);
		}
		CompletableFuture.allOf(futures).join();
	}

	private List<SimpleDto> parse(String json) throws Exception {
		try (Jsonb jsonb = JsonbBuilder.create()) {
			List<SimpleDto> list = jsonb.fromJson(json, new ArrayList<SimpleDto>() {
			}.getClass().getGenericSuperclass());

			return list;
		}
	}

	private CompletableFuture<HttpResponse<String>> call(URI uri) {
		HttpClient client = HttpClient.newBuilder().executor(scheduler).build();
		HttpRequest request = HttpRequest.newBuilder().uri(uri).build();
		return client.sendAsync(request, BodyHandlers.ofString());
	}
}
