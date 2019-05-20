package application;

import java.util.Date;
import java.util.Random;

import Classes.DB;
import model.dao.impl.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		Department department = new Department(2, null);
		SellerDAO sellerDAO = new SellerDAO(DB.getConnection());

		System.out.println("=== TEST 1: seller findById ===");
		Seller seller = sellerDAO.findById(3);
		System.out.println(seller);

		System.out.println("\n=== TEST 2: seller findByDepartment ===");
		for (Seller sellerTemp : sellerDAO.findByDepartment(department)) {
			System.out.println(sellerTemp);
		}

		System.out.println("\n=== TEST 3: seller findAll ===");
		for (Seller sellerTemp : sellerDAO.findAll()) {
			System.out.println(sellerTemp);
		}

		System.out.println("\n=== TEST 4: seller insert ===");
		Seller sellerInsert = new Seller(null, "Weder Monteiro", "weder_monteiro@outlook.com.br", new Date(), 6200.0,
				department);
		sellerDAO.insert(sellerInsert);
		System.out.println("Inserido novo id = " + sellerInsert.getId());
		
		System.out.println("\n=== TEST 5: seller update ===");
		Seller sellerUpdate = sellerDAO.findById(1);
		sellerUpdate.setName("Marta" + new Random().nextInt());
		sellerDAO.update(sellerUpdate);
		System.out.println("Atualizado" + sellerUpdate);

		DB.closeConnection();
	}

}
