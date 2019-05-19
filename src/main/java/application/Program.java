package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import Classes.DB;
import Exceptions.DbIntegrityException;

public class Program {

	public static void main(String[] args) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Connection connection = null;
		Statement statement = null;
		Statement statement2 = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement2 = null;
		PreparedStatement preparedStatement3 = null;

		try {
			/* Select */
			connection = DB.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from seller");
			while (resultSet.next()) {
				System.out.println(resultSet.getInt("Id") + ", " + resultSet.getString("Name"));
			}
			/* Select */

			Integer idUsuarioCriado = null;

			/* Insert */
			preparedStatement = connection.prepareStatement(
					"insert into seller (Name, Email, BirthDate, BaseSalary, DepartmentId) values (?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, "Weder Monteiro");
			preparedStatement.setString(2, "weder_monteiro@outlook.com.br");
			preparedStatement.setDate(3, new java.sql.Date(simpleDateFormat.parse("16/09/1994").getTime()));
			preparedStatement.setDouble(4, 3000.0);
			preparedStatement.setInt(5, 4);
			int linhasAlteradas = preparedStatement.executeUpdate();
			if (linhasAlteradas > 0) {
				ResultSet resultSet2 = preparedStatement.getGeneratedKeys();
				while (resultSet2.next()) {
					int id = resultSet2.getInt(1);
					System.out.println("Feito! Id = " + id);
					idUsuarioCriado = id;
				}
			} else {
				System.out.println("Nenhuma linha alterada!");
			}
			/* Insert */

			/* Update */
			preparedStatement2 = connection
					.prepareStatement("update seller set BaseSalary = BaseSalary + ? where (Id = ?)");
			preparedStatement2.setDouble(1, 200.0);
			preparedStatement2.setInt(2, idUsuarioCriado);
			int linhasAlteradas2 = preparedStatement2.executeUpdate();
			System.out.println("Linhas alteradas: " + linhasAlteradas2);
			/* Update */

			/* Delete */
			preparedStatement3 = connection.prepareStatement("delete from seller where Id = ?");
			preparedStatement3.setInt(1, idUsuarioCriado);
			int linhasAlteradas3 = preparedStatement2.executeUpdate();
			System.out.println("Linhas deletadas: " + linhasAlteradas3);
			/* Delete */

			/* Transacao */
			connection.setAutoCommit(false);
			statement2 = connection.createStatement();
			int linhasAlteradas4 = statement2
					.executeUpdate("update seller set BaseSalary = 2090 where DepartmentId = 1");
			connection.commit();
			System.out.println("Linhas de transações" + linhasAlteradas4);
			connection.rollback();
			/* Transacao */

		} catch (SQLException | ParseException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			DB.closeResultSet(resultSet);
			DB.closeStatement(statement);
			DB.closeStatement(statement2);
			DB.closeStatement(preparedStatement);
			DB.closeStatement(preparedStatement2);
			DB.closeStatement(preparedStatement3);
			DB.closeConnection();
		}
	}

}
