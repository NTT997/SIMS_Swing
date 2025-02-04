package Entities;

import java.util.Date;

public class Customers {
	private int id;
	private String name;
	private boolean gender;
	private String email;
	private String tel;
	private String address;
	private Date created_at;

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

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Customers(int id, String name, boolean gender, String email, String tel, String address, Date created_at) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.tel = tel;
		this.address = address;
		this.created_at = created_at;
	}

	public Customers() {
		super();
	}

	@Override
	public String toString() {
		return "Customers [id=" + id + ", name=" + name + ", gender=" + gender + ", email=" + email + ", tel=" + tel
				+ ", address=" + address + ", created_at=" + created_at + "]";
	}

}