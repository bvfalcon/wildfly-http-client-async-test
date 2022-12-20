package name.bychkov.test;

import java.io.Serializable;

import lombok.Data;

@Data
public class SimpleDto implements Serializable {
	private static final long serialVersionUID = 441278854164789286L;

	private Integer id;
	private String name;
}
