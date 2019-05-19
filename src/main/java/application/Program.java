package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import Classes.DB;

public class Program {

	public static void main(String[] args) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DB.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from seller");

			while (resultSet.next()) {
				System.out.println(resultSet.getInt("Id") + ", " + resultSet.getString("Name"));
			}

			preparedStatement = connection.prepareStatement("insert into seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) " + "values " + "(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, "Weder Monteiro");
			preparedStatement.setString(2, "weder_monteiro@outlook.com.br");
			preparedStatement.setDate(3, new java.sql.Date(simpleDateFormat.parse("16/09/1994").getTime()));
			preparedStatement.setDouble(4, 3000.0);
			preparedStatement.setInt(5, 4);

			int linhasAlteradas = preparedStatement.executeUpdate();

			if (linhasAlteradas > 0) {
				ResultSet resultSet2 = preparedStatement.getGeneratedKeys();
				while(resultSet2.next()) {
					int id = resultSet2.getInt(1);
					System.out.println("Feito! Id = " + id);
				}
			} else {
				System.out.println("Nenhuma linha alterada!");
			}
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(resultSet);
			DB.closeStatement(statement);
			DB.closeStatement(preparedStatement);
			DB.closeConnection();
		}
	}

}
