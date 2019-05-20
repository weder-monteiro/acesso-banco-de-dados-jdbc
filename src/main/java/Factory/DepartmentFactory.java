package Factory;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.entities.Department;

public class DepartmentFactory {

	public static Department getDepartment(ResultSet resultSet) throws SQLException {
		return new Department(resultSet.getInt("DepartmentId"), resultSet.getString("DepName"));
	}
}
