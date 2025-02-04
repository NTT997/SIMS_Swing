package Models.Product;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.mysql.cj.conf.ConnectionUrl.Type;

import Entities.Invoice;
import Entities.Products;
import Models.ConnectDB;

public class ProductModel {
	// show available product
	public List<Products> findAll() {
		List<Products> products = new ArrayList<Products>();
		try (PreparedStatement preparedStatement = ConnectDB.connection()
				.prepareStatement("SELECT * FROM products where disable = false");
				ResultSet resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				Products product = new Products();
				product.setProduct_code(resultSet.getString("product_code"));
				product.setName(resultSet.getString("name"));
				product.setPrice(resultSet.getDouble("price"));
				product.setQuantity(resultSet.getDouble("quantity"));
				product.setImage(resultSet.getBytes("image"));
				product.setDiscount_percent(resultSet.getInt("discount_percent"));
				product.setDisable(resultSet.getBoolean("disable"));
				product.setCreated_at(resultSet.getDate("created_at"));
				product.setUnit_id(resultSet.getInt("unit_id"));
				products.add(product);

			}
		}

		catch (SQLException e) {
			e.printStackTrace();
			products = null;
		} finally {
			ConnectDB.disconnect();
		}
		return products;
	}

	public boolean create(Products product) {
		boolean result = true;
		try {
			PreparedStatement prepareStatement = ConnectDB.connection().prepareStatement(
					"insert into products(product_code, name, price, quantity, image, discount_percent, disable, created_at, deleted_at, unit_id) values(?,?,?,?,?,?,?,?,?,?)");
			prepareStatement.setString(1, product.getProduct_code());
			prepareStatement.setString(2, product.getName());
			prepareStatement.setDouble(3, product.getPrice());
			prepareStatement.setDouble(4, product.getQuantity());
			prepareStatement.setBytes(5, product.getImage());
			prepareStatement.setInt(6, product.getDiscount_percent());
			prepareStatement.setBoolean(7, product.isDisable());
			prepareStatement.setTimestamp(8, new Timestamp(product.getCreated_at().getTime()));

			if (product.getDeleted_at() != null) {
				prepareStatement.setTimestamp(9, new Timestamp(product.getDeleted_at().getTime()));
			} else {
				prepareStatement.setNull(9, Types.TIMESTAMP);
			}

			prepareStatement.setInt(10, product.getUnit_id());

			result = prepareStatement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			ConnectDB.disconnect();
		}
		return result;
	}

	public List<Products> findDeleted() {
		List<Products> products = new ArrayList<Products>();
		try (PreparedStatement preparedStatement = ConnectDB.connection()
				.prepareStatement("SELECT * FROM products where disable = true");
				ResultSet resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				Products product = new Products();
				product.setProduct_code(resultSet.getString("product_code"));
				product.setName(resultSet.getString("name"));
				product.setPrice(resultSet.getDouble("price"));
				product.setQuantity(resultSet.getDouble("quantity"));
				product.setImage(resultSet.getBytes("image"));
				product.setDiscount_percent(resultSet.getInt("discount_percent"));
				product.setDisable(resultSet.getBoolean("disable"));
				product.setCreated_at(resultSet.getDate("created_at"));
				product.setDeleted_at(resultSet.getDate("deleted_at"));
				product.setUnit_id(resultSet.getInt("unit_id"));
				products.add(product);

			}
		}

		catch (SQLException e) {
			e.printStackTrace();
			products = null;
		} finally {
			ConnectDB.disconnect();
		}
		return products;
	}

	// show even deleted product
	public List<Products> showAll() {
		List<Products> products = new ArrayList<Products>();
		try (PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("SELECT * FROM products");
				ResultSet resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				Products product = new Products();
				product.setProduct_code(resultSet.getString("product_code"));
				product.setName(resultSet.getString("name"));
				product.setPrice(resultSet.getDouble("price"));
				product.setQuantity(resultSet.getDouble("quantity"));
				product.setImage(resultSet.getBytes("image"));
				product.setDiscount_percent(resultSet.getInt("discount_percent"));
				product.setDisable(resultSet.getBoolean("disable"));
				product.setCreated_at(resultSet.getDate("created_at"));
				product.setUnit_id(resultSet.getInt("unit_id"));
				products.add(product);
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
			products = null;
		} finally {
			ConnectDB.disconnect();
		}
		return products;
	}

	public Products findById(String product_code) {
		Products products = null;
		try {
			PreparedStatement prepareStatement = ConnectDB.connection()
					.prepareStatement("select * from products where product_code like ?");
			prepareStatement.setString(1, product_code + "%");
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				products = new Products();
				products.setProduct_code(resultSet.getString("product_code"));
				products.setName(resultSet.getString("name"));
				products.setPrice(resultSet.getDouble("price"));
				products.setQuantity(resultSet.getDouble("quantity"));
				products.setImage(resultSet.getBytes("image"));
				products.setDiscount_percent(resultSet.getInt("discount_percent"));
				products.setDisable(resultSet.getBoolean("disable"));
				products.setCreated_at(resultSet.getDate("created_at"));
				products.setUnit_id(resultSet.getInt("unit_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			products = null;
		} finally {
			ConnectDB.disconnect();
		}
		return products;
	}

	public boolean addImage(Products product, InputStream imageStream) {
		boolean statusAdd = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement(
					"INSERT INTO products (product_code, name, price, quantity, image, discount_percent, disable, created_at, unit_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, product.getProduct_code());
			preparedStatement.setString(2, product.getName());
			preparedStatement.setDouble(3, product.getPrice());
			preparedStatement.setDouble(4, product.getQuantity());

			// Set the image using setBlob
			preparedStatement.setBlob(5, imageStream);

			preparedStatement.setDouble(6, product.getDiscount_percent());
			preparedStatement.setBoolean(7, product.isDisable());
			preparedStatement.setDate(8, new java.sql.Date(product.getCreated_at().getTime()));
			preparedStatement.setInt(9, product.getUnit_id());

			statusAdd = preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			statusAdd = false;
		} finally {
			ConnectDB.disconnect();
		}
		return statusAdd;
	}

	public boolean update(Products product, InputStream newImageStream) {
		boolean statusUpdate = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement(
					"UPDATE products SET name = ?, price = ?, quantity = ?, image = ?, discount_percent = ?, disable = ?, created_at = ?, unit_id = ? WHERE product_code = ?");
			preparedStatement.setString(1, product.getName());
			preparedStatement.setDouble(2, product.getPrice());
			preparedStatement.setDouble(3, product.getQuantity());

			// Set the new image using setBlob
			preparedStatement.setBlob(4, newImageStream);

			preparedStatement.setDouble(5, product.getDiscount_percent());
			preparedStatement.setBoolean(6, product.isDisable());
			preparedStatement.setDate(7, new java.sql.Date(product.getCreated_at().getTime()));
			preparedStatement.setInt(8, product.getUnit_id());
			preparedStatement.setString(9, product.getProduct_code());

			statusUpdate = preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			statusUpdate = false;
		} finally {
			ConnectDB.disconnect();
		}
		return statusUpdate;
	}

	public boolean updateQuantity(double quantity, String productCode) {
		boolean statusUpdate = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("UPDATE products SET quantity = ? WHERE product_code = ?");

			preparedStatement.setDouble(1, quantity);
			preparedStatement.setString(2, productCode);
			statusUpdate = preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			statusUpdate = false;
		} finally {
			ConnectDB.disconnect();
		}
		return statusUpdate;
	}

	public boolean updatePrice(double price, String productCode) {
		boolean statusUpdate = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("UPDATE products SET price = ? WHERE product_code = ?");

			preparedStatement.setDouble(1, price);
			preparedStatement.setString(2, productCode);
			statusUpdate = preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			statusUpdate = false;
		} finally {
			ConnectDB.disconnect();
		}
		return statusUpdate;
	}

	public boolean delete(Date deleted_at, String productCode, boolean status) {
		boolean statusUpdate = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("UPDATE products SET disable = ?, deleted_at = ? WHERE product_code = ?");

			preparedStatement.setBoolean(1, status);
			// Set the "deleted_at" field to the current timestamp
			if (deleted_at != null) {
				Timestamp currentTimestamp = new Timestamp(deleted_at.getTime());
				preparedStatement.setTimestamp(2, currentTimestamp);
			} else {
				preparedStatement.setNull(2, Types.BOOLEAN);
			}
			preparedStatement.setString(3, productCode);
			statusUpdate = preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			statusUpdate = false;
		} finally {
			ConnectDB.disconnect();
		}
		return statusUpdate;
	}

	public List<Products> findbyname(String keyword) {
		List<Products> products = new ArrayList<>();
		Connection connection = null;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("SELECT * FROM products WHERE name LIKE ?");
			preparedStatement.setString(1, "%" + keyword + "%");
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Products product = new Products();
				product.setProduct_code(resultSet.getString("product_code"));
				product.setName(resultSet.getString("name"));
				product.setPrice(resultSet.getDouble("price"));
				product.setQuantity(resultSet.getDouble("quantity"));
				product.setImage(resultSet.getBytes("image"));
				product.setDiscount_percent(resultSet.getInt("discount_percent"));
				product.setDisable(resultSet.getBoolean("disable"));
				product.setCreated_at(resultSet.getDate("created_at"));
				product.setUnit_id(resultSet.getInt("unit_id"));
				;
				products.add(product);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return products;
	}
}
