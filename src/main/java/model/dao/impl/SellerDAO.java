package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Classes.DB;
import Exceptions.DbException;
import Factory.DepartmentFactory;
import Factory.SellerFactory;
import model.dao.GenericoDAO;
import model.entities.Department;
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
					"SELECT seller.*,department.Name as DepName FROM seller INNER JOIN department ON seller.DepartmentId = department.Id WHERE seller.Id = ?");
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

	public List<Seller> findByDepartment(Department department) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Seller> sellers = new ArrayList<>();
		Map<Integer, Department> departmentMap = new HashMap<>(); 

		try {
			preparedStatement = connection.prepareStatement(
					"SELECT seller.*,department.Name as DepName FROM seller INNER JOIN department ON seller.DepartmentId = department.Id WHERE DepartmentId = ? ORDER BY Name");
			preparedStatement.setInt(1, department.getId());
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Department departmentTemp = departmentMap.get(resultSet.getInt("DepartmentId"));
				
				if(departmentTemp == null) {
					departmentTemp = DepartmentFactory.getDepartment(resultSet);
					departmentMap.put(resultSet.getInt("DepartmentId"), departmentTemp);
				}
				
				Seller seller = SellerFactory.getSeller(resultSet, departmentTemp);
				sellers.add(seller);
			}
			return sellers;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(preparedStatement);
			DB.closeResultSet(resultSet);
		}
	}
}
