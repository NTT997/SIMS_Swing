package Entities;

import java.util.Arrays;
import java.util.Date;

public class Products {
	private String product_code;
	private String name;
	private double price;
	private double quantity;
	private byte[] image;
	private int discount_percent;
	private boolean disable;
	private Date created_at;
	private Date deleted_at;
	private int unit_id;

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public int getDiscount_percent() {
		return discount_percent;
	}

	public void setDiscount_percent(int discount_percent) {
		this.discount_percent = discount_percent;
	}

	public boolean isDisable() {
		return disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getDeleted_at() {
		return deleted_at;
	}

	public void setDeleted_at(Date deleted_at) {
		this.deleted_at = deleted_at;
	}

	public int getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(int unit_id) {
		this.unit_id = unit_id;
	}

	public Products(String product_code, String name, double price, double quantity, byte[] image, int discount_percent,
			boolean disable, Date created_at, Date deleted_at, int unit_id) {
		super();
		this.product_code = product_code;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.image = image;
		this.discount_percent = discount_percent;
		this.disable = disable;
		this.created_at = created_at;
		this.deleted_at = deleted_at;
		this.unit_id = unit_id;
	}

	public Products() {
		super();
	}
}