package Entities;

public class Units {
	private int unit_id;
	private String unit_name;
	private String description;

	public int getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(int unit_id) {
		this.unit_id = unit_id;
	}

	public String getUnit_name() {
		return unit_name;
	}

	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Units(int unit_id, String unit_name, String description) {
		super();
		this.unit_id = unit_id;
		this.unit_name = unit_name;
		this.description = description;
	}

	public Units() {
		super();
	}

}
