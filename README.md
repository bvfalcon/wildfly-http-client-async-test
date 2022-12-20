HttpClient asynchronous call with ManagedExecutorService on Wildfly
==============

Java 11 and Maven 3.6.3 are required.

Build and run from source with command: `mvn`

### Error

After aplication started we have an error

<details>
<summary>java.lang.ClassNotFoundException: org.eclipse.yasson.JsonBindingProvider from [Module "javax.json.bind.api" version 1.0.2 from local module loader...</summary>

```
12:50:16,808 INFO  [stdout] (EE-ManagedScheduledExecutorService-default-Thread-1) Timer started. Current time: 12:50:16.807128300

12:50:16,916 INFO  [org.jboss.as.server] (management-handler-thread - 1) WFLYSRV0010: Deployed "wildfly-httpclient-async-test-1.0.0-SNAPSHOT.jar" (runtime-name : "wildfly-httpclient-async-test-1.0.0-SNAPSHOT.jar")
12:50:17,398 INFO  [stdout] (EE-ManagedScheduledExecutorService-default-Thread-3) raw json: [
12:50:17,404 INFO  [stdout] (EE-ManagedScheduledExecutorService-default-Thread-3) 	{
12:50:17,404 INFO  [stdout] (EE-ManagedScheduledExecutorService-default-Thread-3) 		"id": 1,
12:50:17,404 INFO  [stdout] (EE-ManagedScheduledExecutorService-default-Thread-3) 		"name": "Tom Sawyer"
12:50:17,404 INFO  [stdout] (EE-ManagedScheduledExecutorService-default-Thread-3) 	},
12:50:17,404 INFO  [stdout] (EE-ManagedScheduledExecutorService-default-Thread-3) 	{
12:50:17,404 INFO  [stdout] (EE-ManagedScheduledExecutorService-default-Thread-3) 		"id": 2,
12:50:17,404 INFO  [stdout] (EE-ManagedScheduledExecutorService-default-Thread-3) 		"name": "Huckleberry Finn"
12:50:17,404 INFO  [stdout] (EE-ManagedScheduledExecutorService-default-Thread-3) 	}
12:50:17,404 INFO  [stdout] (EE-ManagedScheduledExecutorService-default-Thread-3) ]
12:50:17,405 INFO  [stdout] (EE-ManagedScheduledExecutorService-default-Thread-2) raw json: [
12:50:17,405 INFO  [stdout] (EE-ManagedScheduledExecutorService-default-Thread-2) 	{
12:50:17,405 INFO  [stdout] (EE-ManagedScheduledExecutorService-default-Thread-2) 		"id": 3,
12:50:17,405 INFO  [stdout] (EE-ManagedScheduledExecutorService-default-Thread-2) 		"name": "Injun Joe"
12:50:17,405 INFO  [stdout] (EE-ManagedScheduledExecutorService-default-Thread-2) 	},
12:50:17,405 INFO  [stdout] (EE-ManagedScheduledExecutorService-default-Thread-2) 	{
12:50:17,405 INFO  [stdout] (EE-ManagedScheduledExecutorService-default-Thread-2) 		"id": 4,
12:50:17,406 INFO  [stdout] (EE-ManagedScheduledExecutorService-default-Thread-2) 		"name": "Muff Potter"
12:50:17,406 INFO  [stdout] (EE-ManagedScheduledExecutorService-default-Thread-2) 	}
12:50:17,406 INFO  [stdout] (EE-ManagedScheduledExecutorService-default-Thread-2) ]
12:50:17,412 ERROR [stderr] (EE-ManagedScheduledExecutorService-default-Thread-3) javax.json.bind.JsonbException: JSON Binding provider org.eclipse.yasson.JsonBindingProvider not found
12:50:17,412 ERROR [stderr] (EE-ManagedScheduledExecutorService-default-Thread-3) 	at javax.json.bind.api@1.0.2//javax.json.bind.spi.JsonbProvider.provider(JsonbProvider.java:96)
12:50:17,413 ERROR [stderr] (EE-ManagedScheduledExecutorService-default-Thread-3) 	at javax.json.bind.api@1.0.2//javax.json.bind.JsonbBuilder.create(JsonbBuilder.java:85)
12:50:17,413 ERROR [stderr] (EE-ManagedScheduledExecutorService-default-Thread-3) 	at deployment.wildfly-httpclient-async-test-1.0.0-SNAPSHOT.jar//name.bychkov.test.Checker.parse(Checker.java:59)
12:50:17,414 ERROR [stderr] (EE-ManagedScheduledExecutorService-default-Thread-3) 	at deployment.wildfly-httpclient-async-test-1.0.0-SNAPSHOT.jar//name.bychkov.test.Checker.lambda$tick$0(Checker.java:47)
12:50:17,414 ERROR [stderr] (EE-ManagedScheduledExecutorService-default-Thread-3) 	at java.base/java.util.concurrent.CompletableFuture$UniApply.tryFire(CompletableFuture.java:642)
12:50:17,414 ERROR [stderr] (EE-ManagedScheduledExecutorService-default-Thread-3) 	at java.base/java.util.concurrent.CompletableFuture$Completion.run(CompletableFuture.java:478)
12:50:17,414 ERROR [stderr] (EE-ManagedScheduledExecutorService-default-Thread-3) 	at org.jboss.as.ee@26.1.2.Final//org.jboss.as.ee.concurrent.ControlPointUtils$ControlledRunnable.run(ControlPointUtils.java:125)
12:50:17,415 ERROR [stderr] (EE-ManagedScheduledExecutorService-default-Thread-3) 	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515)
12:50:17,415 ERROR [stderr] (EE-ManagedScheduledExecutorService-default-Thread-3) 	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
12:50:17,415 ERROR [stderr] (EE-ManagedScheduledExecutorService-default-Thread-3) 	at org.glassfish.javax.enterprise.concurrent//org.glassfish.enterprise.concurrent.internal.ManagedFutureTask.run(ManagedFutureTask.java:117)
12:50:17,415 ERROR [stderr] (EE-ManagedScheduledExecutorService-default-Thread-3) 	at org.glassfish.javax.enterprise.concurrent//org.glassfish.enterprise.concurrent.internal.ManagedScheduledThreadPoolExecutor$ManagedScheduledFutureTask.access$101(ManagedScheduledThreadPoolExecutor.java:360)
12:50:17,415 ERROR [stderr] (EE-ManagedScheduledExecutorService-default-Thread-3) 	at org.glassfish.javax.enterprise.concurrent//org.glassfish.enterprise.concurrent.internal.ManagedScheduledThreadPoolExecutor$ManagedScheduledFutureTask.run(ManagedScheduledThreadPoolExecutor.java:509)
12:50:17,415 ERROR [stderr] (EE-ManagedScheduledExecutorService-default-Thread-3) 	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
12:50:17,416 ERROR [stderr] (EE-ManagedScheduledExecutorService-default-Thread-3) 	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
12:50:17,416 ERROR [stderr] (EE-ManagedScheduledExecutorService-default-Thread-3) 	at java.base/java.lang.Thread.run(Thread.java:829)
12:50:17,416 ERROR [stderr] (EE-ManagedScheduledExecutorService-default-Thread-3) 	at org.glassfish.javax.enterprise.concurrent//org.glassfish.enterprise.concurrent.ManagedThreadFactoryImpl$ManagedThread.run(ManagedThreadFactoryImpl.java:227)
12:50:17,420 ERROR [stderr] (EE-ManagedScheduledExecutorService-default-Thread-3) Caused by: java.lang.ClassNotFoundException: org.eclipse.yasson.JsonBindingProvider from [Module "javax.json.bind.api" version 1.0.2 from local module loader @18cebaa5 (finder: local module finder @463b4ac8 (roots: C:\git\wildfly-http-client-async-test\target\server\modules,C:\git\wildfly-http-client-async-test\target\server\modules\system\layers\base))]
12:50:17,420 ERROR [stderr] (EE-ManagedScheduledExecutorService-default-Thread-3) 	at org.jboss.modules.ModuleClassLoader.findClass(ModuleClassLoader.java:200)
12:50:17,421 ERROR [stderr] (EE-ManagedScheduledExecutorService-default-Thread-3) 	at org.jboss.modules.ConcurrentClassLoader.performLoadClassUnchecked(ConcurrentClassLoader.java:410)
12:50:17,421 ERROR [stderr] (EE-ManagedScheduledExecutorService-default-Thread-3) 	at org.jboss.modules.ConcurrentClassLoader.performLoadClass(ConcurrentClassLoader.java:398)
12:50:17,421 ERROR [stderr] (EE-ManagedScheduledExecutorService-default-Thread-3) 	at org.jboss.modules.ConcurrentClassLoader.loadClass(ConcurrentClassLoader.java:116)
12:50:17,421 ERROR [stderr] (EE-ManagedScheduledExecutorService-default-Thread-3) 	at java.base/java.lang.Class.forName0(Native Method)
12:50:17,422 ERROR [stderr] (EE-ManagedScheduledExecutorService-default-Thread-3) 	at java.base/java.lang.Class.forName(Class.java:315)
12:50:17,422 ERROR [stderr] (EE-ManagedScheduledExecutorService-default-Thread-3) 	at javax.json.bind.api@1.0.2//javax.json.bind.spi.JsonbProvider.provider(JsonbProvider.java:93)
12:50:17,422 ERROR [stderr] (EE-ManagedScheduledExecutorService-default-Thread-3) 	... 15 more
```

</details>

## Error description

In EJB-Component we make [**asynchronous** http-call](https://github.com/bvfalcon/wildfly-http-client-async-test/blob/master/src/main/java/name/bychkov/test/Checker.java#L68) using ManagedScheduledExecutorService as executor for HttpClient. Then we [process results asynchronously](https://github.com/bvfalcon/wildfly-http-client-async-test/blob/master/src/main/java/name/bychkov/test/Checker.java#L43) using [the same ManagedScheduledExecutorService](https://github.com/bvfalcon/wildfly-http-client-async-test/blob/master/src/main/java/name/bychkov/test/Checker.java#L53) too. While processing we have this error.

In case of [**synchronous** http-call](https://github.com/bvfalcon/wildfly-http-client-async-test/blob/sync-call/src/main/java/name/bychkov/test/Checker.java#L71) this error has not aqcuired.

For this reason we can assume the reason of error in http-call **asynchronity**.
