package Models.Invoice;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Entities.Return_product;
import Models.ConnectDB;

public class ReturnProductModel {
	public boolean create(Return_product returnProduct) {
		boolean result = true;
		try {
			PreparedStatement prepareStatement = ConnectDB.connection().prepareStatement(
					"insert into return_product(invoice_detail_id, reason, returned_at) values(?,?,?)");
			prepareStatement.setInt(1, returnProduct.getInvoice_detail_id());
			prepareStatement.setString(2, returnProduct.getReason());
			Timestamp currentTimestamp = new Timestamp(returnProduct.getReturned_at().getTime());
			prepareStatement.setTimestamp(3, currentTimestamp);
			result = prepareStatement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			ConnectDB.disconnect();
		}
		return result;
	}

	public List<Return_product> returnedListAll() {
		List<Return_product> list = new ArrayList<>();
		try {
			PreparedStatement prepareStatement = ConnectDB.connection()
					.prepareStatement("select * from return_product");
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Return_product returnedProduct = new Return_product();
				returnedProduct.setId(resultSet.getInt("id"));
				returnedProduct.setInvoice_detail_id(resultSet.getInt("invoice_detail_id"));
				returnedProduct.setReason(resultSet.getString("reason"));
				returnedProduct.setReturned_at(resultSet.getDate("returned_at"));
				list.add(returnedProduct);
			}
		} catch (Exception e) {
			// TODO: handle exception
			list = null;
			e.printStackTrace();
		} finally {
			ConnectDB.disconnect();
		}
		return list;
	}
}
