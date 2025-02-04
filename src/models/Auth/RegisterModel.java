package Models.Auth;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import org.mindrot.jbcrypt.BCrypt;

import Entities.Employees;
import Models.ConnectDB;

public class RegisterModel {
	public boolean Register(Employees employee) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement(
					"insert into employees(username,password,email,tel,address,image,status,token,position_id,start_working)"
							+ " values(?,?,?,?,?,?,?,?,?,?)");

			preparedStatement.setString(1, employee.getUsername());
			preparedStatement.setString(2, BCrypt.hashpw(employee.getPassword(), BCrypt.gensalt()));
			preparedStatement.setString(3, employee.getEmail());
			preparedStatement.setString(4, employee.getTel());
			preparedStatement.setString(5, employee.getAddress());
			byte[] DefaultImage = Files.readAllBytes(Paths.get("src/Resources/Images/employee-default.png"));
			if (employee.getImage() != null) {
				preparedStatement.setBytes(6, employee.getImage());
			} else {
				preparedStatement.setBytes(6, DefaultImage);
			}
			preparedStatement.setBoolean(7, false);
			preparedStatement.setString(8, employee.getToken());
			preparedStatement.setInt(9, employee.getPosition_id());
			Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
			preparedStatement.setTimestamp(10, currentTimestamp);
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
