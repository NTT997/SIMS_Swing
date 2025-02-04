package Models.Invoice;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Entities.Invoice_detail;
import Models.ConnectDB;

public class InvoiceDetailModel {
	public boolean create(Invoice_detail invoice_detail) {
		boolean result = true;
		try {
			PreparedStatement prepareStatement = ConnectDB.connection().prepareStatement(
					"insert into invoice_detail(product_code, invoice_id, product_price, product_quantity, is_discount, discount_percent, returned, created_at) values(?,?,?,?,?,?,?,?)");
			prepareStatement.setString(1, invoice_detail.getProduct_code());
			prepareStatement.setString(2, invoice_detail.getInvoice_id());
			prepareStatement.setDouble(3, invoice_detail.getProduct_price());
			prepareStatement.setDouble(4, invoice_detail.getProduct_quantity());
			prepareStatement.setBoolean(5, invoice_detail.isIs_discount());
			prepareStatement.setInt(6, invoice_detail.getDiscount_percent());
			prepareStatement.setBoolean(7, invoice_detail.isReturned());
			prepareStatement.setTimestamp(8, invoice_detail.getCreated_at());
			result = prepareStatement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			ConnectDB.disconnect();
		}
		return result;
	}

	public List<Invoice_detail> findByInvoiceId(String invoice_id) {
		List<Invoice_detail> invoices = new ArrayList<Invoice_detail>();
		try {
			PreparedStatement prepareStatement = ConnectDB.connection()
					.prepareStatement("select * from invoice_detail where invoice_id = ?");
			prepareStatement.setString(1, invoice_id);
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Invoice_detail invoiceDetail = new Invoice_detail();
				invoiceDetail.setId(resultSet.getInt("id"));
				invoiceDetail.setProduct_code(resultSet.getString("product_code"));
				invoiceDetail.setInvoice_id(resultSet.getString("invoice_id"));
				invoiceDetail.setProduct_price(resultSet.getDouble("product_price"));
				invoiceDetail.setProduct_quantity(resultSet.getDouble("product_quantity"));
				invoiceDetail.setIs_discount(resultSet.getBoolean("is_discount"));
				invoiceDetail.setDiscount_percent(resultSet.getInt("discount_percent"));
				invoiceDetail.setReturned(resultSet.getBoolean("returned"));
				invoiceDetail.setCreated_at(resultSet.getTimestamp("created_at"));
				invoices.add(invoiceDetail);
			}
		} catch (Exception e) {
			e.printStackTrace();
			invoices = null;
		} finally {
			ConnectDB.disconnect();
		}
		return invoices;
	}

	public Invoice_detail findById(int id) {
		Invoice_detail invoiceDetail = null;
		try {
			PreparedStatement prepareStatement = ConnectDB.connection()
					.prepareStatement("select * from invoice_detail where id = ?");
			prepareStatement.setInt(1, id);
			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				invoiceDetail = new Invoice_detail();
				invoiceDetail.setId(resultSet.getInt("id"));
				invoiceDetail.setProduct_code(resultSet.getString("product_code"));
				invoiceDetail.setInvoice_id(resultSet.getString("invoice_id"));
				invoiceDetail.setProduct_price(resultSet.getDouble("product_price"));
				invoiceDetail.setProduct_quantity(resultSet.getDouble("product_quantity"));
				invoiceDetail.setIs_discount(resultSet.getBoolean("is_discount"));
				invoiceDetail.setDiscount_percent(resultSet.getInt("discount_percent"));
				invoiceDetail.setReturned(resultSet.getBoolean("returned"));
				invoiceDetail.setCreated_at(resultSet.getTimestamp("created_at"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			invoiceDetail = null;
		} finally {
			ConnectDB.disconnect();
		}
		return invoiceDetail;
	}

	public boolean updateIsReturned(int invoiceDetailId) {
		boolean result = true;
		try {
			PreparedStatement prepareStatement = ConnectDB.connection()
					.prepareStatement("UPDATE invoice_detail SET returned = true WHERE id=?");
			prepareStatement.setInt(1, invoiceDetailId);

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
