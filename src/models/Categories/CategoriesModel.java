package Models.Categories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.protocol.x.ReusableOutputStream;

import Entities.Categories;
import Entities.Product_category;
import Entities.Products;
import Models.ConnectDB;

public class CategoriesModel {
	public List<Categories> findAllCategories() {
		List<Categories> categoriesList = new ArrayList<>();
		try (PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("SELECT * FROM categories");
				ResultSet resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				Categories category = new Categories();
				category.setId(resultSet.getInt("category_id"));
				category.setName(resultSet.getString("name"));
				category.setDescription(resultSet.getString("description"));
				category.setCreated_at(resultSet.getDate("created_at"));
				category.setDisable(resultSet.getBoolean("disable"));
				categoriesList.add(category);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectDB.disconnect();
		}

		return categoriesList;
	}

	public boolean addCategory(Categories category) {
		try (Connection conn = ConnectDB.connection();
				PreparedStatement pstmt = conn.prepareStatement(
						"INSERT INTO categories (name, description, created_at, disable) VALUES (?, ?, ?, ?)")) {

			pstmt.setString(1, category.getName());
			pstmt.setString(2, category.getDescription());
			pstmt.setDate(3, new java.sql.Date(category.getCreated_at().getTime()));
			pstmt.setBoolean(4, category.isDisable());

			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDB.disconnect();
		}

		return false;
	}

	public int findCategoryID(String nameCategory) {
		Connection connection = null;
		int categoryID = -1; // Giá trị mặc định nếu không tìm thấy

		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("SELECT category_id FROM categories WHERE name LIKE ?");
			preparedStatement.setString(1, nameCategory);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				categoryID = resultSet.getInt("category_id");
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

		return categoryID;
	}

	public List<Product_category> findAllProdcutcodebycategoryID(int category_id) {
		List<Product_category> list = new ArrayList<>();
		Connection connection = null;

		try {
			connection = ConnectDB.connection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM product_category WHERE category_id = ?");
			preparedStatement.setInt(1, category_id);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Product_category product_category = new Product_category();
				product_category.setCategory_id(resultSet.getInt("category_id"));
				product_category.setProduct_code(resultSet.getString("product_code"));
				list.add(product_category);
			}
		} catch (SQLException e) {
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

		return list;
	}

}
