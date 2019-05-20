package Factory;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.entities.Department;
import model.entities.Seller;

public class SellerFactory {

	public static Seller getSeller(ResultSet resultSet) throws SQLException {
		Department department = DepartmentFactory.getDepartment(resultSet);
		return new Seller(resultSet.getInt("Id"), resultSet.getString("Name"), resultSet.getString("Email"),
				resultSet.getDate("BirthDate"), resultSet.getDouble("BaseSalary"), department);
	}
}
