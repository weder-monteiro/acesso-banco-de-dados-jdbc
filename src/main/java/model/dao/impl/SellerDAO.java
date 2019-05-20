package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Classes.DB;
import Exceptions.DbException;
import Factory.SellerFactory;
import model.dao.GenericoDAO;
import model.entities.Seller;

public class SellerDAO implements GenericoDAO<Seller, Integer> {

	private Connection connection;

	public SellerDAO(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(Seller t) {
	}

	@Override
	public void update(Seller t) {
	}

	@Override
	public void deleteById(Integer id) {
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "WHERE seller.Id = ?");
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Seller seller = SellerFactory.getSeller(resultSet);
				return seller;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(preparedStatement);
			DB.closeResultSet(resultSet);
		}
	}

	@Override
	public List<Seller> findAll() {
		return null;
	}
}
