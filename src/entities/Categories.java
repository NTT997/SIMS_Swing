package Entities;

import java.util.Date;

public class Categories {
	private int id;
	private String name;
	private String description;
	private Date created_at;
	private boolean disable;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public boolean isDisable() {
		return disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}

	public Categories(int id, String name, String description, Date created_at, boolean disable) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.created_at = created_at;
		this.disable = disable;
	}

	public Categories() {
		super();
	}

	@Override
	public String toString() {
		return "Categories [id=" + id + ", name=" + name + ", description=" + description + ", created_at=" + created_at
				+ ", disable=" + disable + "]";
	}

}