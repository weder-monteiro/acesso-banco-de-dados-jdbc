package application;

import java.util.List;

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
		List<Seller> sellers = sellerDAO.findByDepartment(seller.getDepartment());
		for(Seller sellerTemp : sellers) {
			System.out.println(sellerTemp);
		}
		
		DB.closeConnection();
	}

}
