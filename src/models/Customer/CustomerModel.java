package Models.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Entities.Customers;
import Models.ConnectDB;

public class CustomerModel {
	public Customers findByEmail(String email) {
		Customers customer = null;
		try {
			PreparedStatement prepareStatement = ConnectDB.connection()
					.prepareStatement("select * from customers where email = ?");
			prepareStatement.setString(1, email);
			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				customer = new Customers();
				customer.setId(resultSet.getInt("customer_id"));
				customer.setName(resultSet.getString("customer_name"));
				customer.setGender(resultSet.getBoolean("gender"));
				customer.setEmail(resultSet.getString("email"));
				customer.setTel(resultSet.getString("tel"));
				customer.setAddress(resultSet.getString("address"));
				customer.setCreated_at(resultSet.getDate("created_at"));
			}
		} catch (Exception e) {
			customer = null;
		} finally {
			ConnectDB.disconnect();
		}

		return customer;
	}
	
	public Customers findById(int id) {
		Customers customer = null;
		try {
			PreparedStatement prepareStatement = ConnectDB.connection()
					.prepareStatement("select * from customers where customer_id = ?");
			prepareStatement.setInt(1, id);
			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				customer = new Customers();
				customer.setId(resultSet.getInt("customer_id"));
				customer.setName(resultSet.getString("customer_name"));
				customer.setGender(resultSet.getBoolean("gender"));
				customer.setEmail(resultSet.getString("email"));
				customer.setTel(resultSet.getString("tel"));
				customer.setAddress(resultSet.getString("address"));
				customer.setCreated_at(resultSet.getDate("created_at"));
			}
		} catch (Exception e) {
			customer = null;
		} finally {
			ConnectDB.disconnect();
		}

		return customer;
	}

	public boolean create(Customers customer) {
		boolean result = true;
		try {
			PreparedStatement prepareStatement = ConnectDB.connection().prepareStatement(
					"insert into customers(customer_name, gender, email, tel, address, created_at) values (?,?,?,?,?,?)");
			prepareStatement.setString(1, customer.getName());
			prepareStatement.setBoolean(2, customer.isGender());
			prepareStatement.setString(3, customer.getEmail());
			prepareStatement.setString(4, customer.getTel());
			prepareStatement.setString(5, customer.getAddress());
			prepareStatement.setDate(6, new java.sql.Date(customer.getCreated_at().getTime()));

			result = prepareStatement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			ConnectDB.disconnect();
		}
		return result;
	}
}
