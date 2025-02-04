package Models.Supplier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Entities.Suppliers;
import Models.ConnectDB;

public class SupplierModel {
	public boolean creatSupplier(Suppliers suppliers) {
		boolean status = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement(
					"INSERT INTO suppliers (name, email, tel, website, created_at) VALUES (?, ?, ?, ?, ?)");

			preparedStatement.setString(1, suppliers.getName());
			preparedStatement.setString(2, suppliers.getMail());
			preparedStatement.setInt(3, suppliers.getTel());
			preparedStatement.setString(4, suppliers.getWebsite());
			preparedStatement.setTimestamp(5, new Timestamp(suppliers.getCreated_at().getTime()));
			status = preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			status = false;
		} finally {
			ConnectDB.disconnect();
		}
		return status;
	}

	public List<Suppliers> findAllSupplier() {
		List<Suppliers> suppliers = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("select * from suppliers");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Suppliers supplier = new Suppliers();
				supplier.setSupplier_id(resultSet.getInt("supplier_id"));
				supplier.setName(resultSet.getString("name"));
				supplier.setMail(resultSet.getString("email"));
				supplier.setTel(resultSet.getInt("tel"));
				supplier.setWebsite(resultSet.getString("website"));
				supplier.setCreated_at(resultSet.getDate("created_at"));
				suppliers.add(supplier);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			suppliers = null;
		} finally {
			ConnectDB.disconnect();
		}
		return suppliers;
	}

	public Suppliers findbyIdSuplier(int ID) {
		Suppliers suppliers = null;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("select * from suppliers where supplier_id = ?");
			preparedStatement.setInt(1, ID);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Suppliers supplier = new Suppliers();
				supplier.setSupplier_id(resultSet.getInt("supplier_id"));
				supplier.setName(resultSet.getString("name"));
				supplier.setMail(resultSet.getString("email"));
				supplier.setTel(resultSet.getInt("tel"));
				supplier.setWebsite(resultSet.getString("website"));
				supplier.setCreated_at(resultSet.getDate("created_at"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			suppliers = null;
		} finally {
			ConnectDB.disconnect();
		}
		return suppliers;
	}

}
