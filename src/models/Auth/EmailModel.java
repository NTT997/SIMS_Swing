package Models.Auth;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.mindrot.jbcrypt.BCrypt;

import Entities.Employees;
import Models.ConnectDB;

public class EmailModel {
	public Employees FindByEmail(String emailString) {
		Employees employees = null;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("select * from employees where email =?");
			preparedStatement.setString(1, emailString);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				employees = new Employees();
				employees.setEmployee_id(resultSet.getInt("employee_id"));
				employees.setUsername(resultSet.getString("username"));
				employees.setPassword(resultSet.getString("password"));
				employees.setEmail(resultSet.getString("email"));
				employees.setTel(resultSet.getString("tel"));
				employees.setAddress(resultSet.getString("address"));
				employees.setImage(resultSet.getBytes("image"));
				employees.setStatus(resultSet.getBoolean("status"));
				employees.setToken(resultSet.getString("token"));
				employees.setPosition_id(resultSet.getInt("position_id"));
				employees.setStart_working(resultSet.getDate("start_working"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			employees = null;
		} finally {
			ConnectDB.disconnect();
		}
		return employees;
	}

	public boolean ChangeVerifyCode(String email, String verifyCode) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("update employees set token = ? where email = ?");
			preparedStatement.setString(1, verifyCode);
			preparedStatement.setString(2, email);
			result = preparedStatement.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			ConnectDB.disconnect();
		}
		return result;
	}

	public boolean VerifyEmail(String email, String verifyCode) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("update employees set status =true where email =? and token=?");
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, verifyCode);
			result = preparedStatement.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			ConnectDB.disconnect();
		}
		return result;
	}

	public boolean ChangePasswordByEmail(String email, String password) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("update employees set password =? where email =?");
			preparedStatement.setString(1, BCrypt.hashpw(password, BCrypt.gensalt()));
			preparedStatement.setString(2, email);
			result = preparedStatement.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			ConnectDB.disconnect();
		}
		return result;
	}
}
