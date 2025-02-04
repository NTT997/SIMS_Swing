package Models.Auth;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.mindrot.jbcrypt.BCrypt;

import Entities.Employees;
import Models.ConnectDB;

public class LoginModel {
	public Employees Login(String usernameOrEmail, String password) {
		Employees employee = null;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("select * from employees where username =? or email=?");
			preparedStatement.setString(1, usernameOrEmail);
			preparedStatement.setString(2, usernameOrEmail);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				if (BCrypt.checkpw(password, resultSet.getString("password"))) {
					employee = new Employees();
					employee.setEmployee_id(resultSet.getInt("employee_id"));
					employee.setUsername(resultSet.getString("username"));
					employee.setEmail(resultSet.getString("email"));
					employee.setTel(resultSet.getString("tel"));
					employee.setAddress(resultSet.getString("address"));
					employee.setImage(resultSet.getBytes("image"));
					employee.setStatus(resultSet.getBoolean("status"));
					employee.setStart_working(resultSet.getDate("start_working"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			employee = null;
		} finally {
			ConnectDB.disconnect();
		}
		return employee;

	}
}
