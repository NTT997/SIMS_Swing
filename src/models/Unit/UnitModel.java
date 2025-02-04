package Models.Unit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entities.Units;
import Models.ConnectDB;

public class UnitModel {
	public List<Units> findAll() {
		List<Units> units = new ArrayList<Units>();
		try (PreparedStatement preparedStatement = ConnectDB.connection()
				.prepareStatement("SELECT * FROM units");
				ResultSet resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				Units unit = new Units();
				unit.setUnit_id(resultSet.getInt("unit_id"));
				unit.setUnit_name(resultSet.getString("unit_name"));
				unit.setDescription(resultSet.getString("description"));
				units.add(unit);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			units = null;
		} finally {
			ConnectDB.disconnect();
		}
		return units;
	}
}
