package Entities;

import java.util.Date;

public class Employees {
	private int employee_id;
	private String username;
	private String password;
	private String email;
	private String tel;
	private String address;
	private byte[] image;
	private boolean status;
	private String token;
	private int position_id;
	private Date start_working;

	public Employees() {
		super();
	}

	public Employees(int employee_id, String username, String password, String email, String tel, String address,
			byte[] image, boolean status, String token, int position_id, Date start_working) {
		super();
		this.employee_id = employee_id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.tel = tel;
		this.address = address;
		this.image = image;
		this.status = status;
		this.token = token;
		this.position_id = position_id;
		this.start_working = start_working;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getPosition_id() {
		return position_id;
	}

	public void setPosition_id(int position_id) {
		this.position_id = position_id;
	}

	public Date getStart_working() {
		return start_working;
	}

	public void setStart_working(Date start_working) {
		this.start_working = start_working;
	}
}
