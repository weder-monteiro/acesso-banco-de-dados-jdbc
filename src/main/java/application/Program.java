package application;

import Classes.DB;
import model.dao.impl.SellerDAO;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		SellerDAO sellerDAO = new SellerDAO(DB.getConnection());
		
		System.out.println("=== TEST 1: seller findById ===");
		Seller seller = sellerDAO.findById(3);
		System.out.println(seller);
		
		System.out.println("\n=== TEST 2: seller findByDepartment ===");
		for(Seller sellerTemp : sellerDAO.findByDepartment(seller.getDepartment())) {
			System.out.println(sellerTemp);
		}
		
		System.out.println("\n=== TEST 3: seller findAll ===");
		for(Seller sellerTemp : sellerDAO.findAll()) {
			System.out.println(sellerTemp);
		}
		
		DB.closeConnection();
	}

}
