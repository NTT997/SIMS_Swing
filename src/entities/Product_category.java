package Entities;

public class Product_category {
private int category_id;
private String product_code;
public int getCategory_id() {
	return category_id;
}
public void setCategory_id(int category_id) {
	this.category_id = category_id;
}
public String getProduct_code() {
	return product_code;
}
public void setProduct_code(String product_code) {
	this.product_code = product_code;
}
public Product_category(int category_id, String product_code) {
	super();
	this.category_id = category_id;
	this.product_code = product_code;
}
public Product_category() {
	super();
}
@Override
public String toString() {
	return "Product_category [category_id=" + category_id + ", product_code=" + product_code + "]";
}


}
