package Models.Employees;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import Entities.Employees;
import Models.ConnectDB;

public class EmployeeModel {
	public List<Employees> FindAll() {
		List<Employees> employeesList = new ArrayList<Employees>();
		try {
			PreparedStatement prepareStatement = ConnectDB.connection().prepareStatement("select * from employees");
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Employees employee = new Employees();
				employee.setEmployee_id(resultSet.getInt("employee_id"));
				employee.setUsername(resultSet.getString("username"));
				employee.setPassword(resultSet.getString("password"));
				employee.setEmail(resultSet.getString("email"));
				employee.setTel(resultSet.getString("tel"));
				employee.setAddress(resultSet.getString("address"));
				employee.setImage(resultSet.getBytes("image"));
				employee.setStatus(resultSet.getBoolean("status"));
				employee.setToken(resultSet.getString("token"));
				employee.setPosition_id(resultSet.getInt("position_id"));
				employee.setStart_working(resultSet.getDate("start_working"));
				employeesList.add(employee);

			}
		} catch (Exception e) {
			e.printStackTrace();
			employeesList = null;
		} finally {
			ConnectDB.disconnect();
		}
		return employeesList;
	}

	public Employees FindByEmployeeName(String username) {
		Employees employees = null;
		try {
			PreparedStatement prepareStatement = ConnectDB.connection()
					.prepareStatement("select * from employees where username = ?");
			prepareStatement.setString(1, username);
			ResultSet resultSet = prepareStatement.executeQuery();
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

	public Employees FindByEmployeeID(int employeeID) {
		Employees employees = null;
		try {
			PreparedStatement prepareStatement = ConnectDB.connection()
					.prepareStatement("select * from employees where employee_id = ?");
			prepareStatement.setInt(1, employeeID);
			ResultSet resultSet = prepareStatement.executeQuery();
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

	public Employees FindByEmployeeEmail(String email) {
		Employees employees = null;
		try {
			PreparedStatement prepareStatement = ConnectDB.connection()
					.prepareStatement("select * from employees where email = ?");
			prepareStatement.setString(1, email);
			ResultSet resultSet = prepareStatement.executeQuery();
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
	
	public boolean ChangePassword(int employeeID,String password) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement(
					"update employees set password =? where employee_id =?");
			preparedStatement.setString(1, BCrypt.hashpw(password, BCrypt.gensalt()));
			preparedStatement.setInt(2, employeeID);
			result = preparedStatement.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			ConnectDB.disconnect();
		}
		return result;
	}
	public boolean UpdateAccount(Employees employees) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement(
					"update employees set tel =?,address =?,image =? where employee_id =?");
			preparedStatement.setString(1,employees.getTel());
			preparedStatement.setString(2, employees.getAddress());
			preparedStatement.setBytes(3, employees.getImage());
			preparedStatement.setInt(4, employees.getEmployee_id());
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
