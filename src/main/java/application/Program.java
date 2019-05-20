package application;

import Classes.DB;
import model.dao.impl.SellerDAO;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		SellerDAO sellerDAO = new SellerDAO(DB.getConnection());
		
		System.out.println("=== TEST 1: seller findById ===");
		Seller seller = sellerDAO.findById(3);
		DB.closeConnection();
		
		System.out.println(seller);
	}

}
