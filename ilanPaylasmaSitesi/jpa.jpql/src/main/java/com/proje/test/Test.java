package com.proje.test;

import java.util.Date;
import com.proje.model.Brand;
import com.proje.model.Category;
import com.proje.model.Product;
import com.proje.repository.ProductRepository;
import com.proje.repository.impl.ProductRepositoryImpl;

public class Test {
	private static ProductRepository productRepository = new ProductRepositoryImpl();

	public static void main(String[] args) {
		insertData();

	}

	public static void insertData() {
		Brand brand1 = new Brand("Apple");
		Brand brand2 = new Brand("Lg");
		Brand brand3 = new Brand("Sony");
		Brand brand4 = new Brand("Samsung");
		Brand brand5 = new Brand("Asus");

		Category category1 = new Category("Bilgisiyar");
		Category category2 = new Category("Tablet");
		Category category3 = new Category("Telefon");

		Product product1 = new Product("Iphone X", 7900, 2, brand1, category3, new Date());
		Product product2 = new Product("LG G3", 2500, 1, brand2, category3, new Date());
		Product product3 = new Product("Sony Expeia", 2700, 1, brand3, category3, new Date());

		Product product4 = new Product("Samsung Z500", 1700, 1, brand4, category1, new Date());
		Product product5 = new Product("Mac i5", 4100, 1, brand1, category1, new Date());
		Product product6 = new Product("Asus i7", 3100, 2, brand5, category1, new Date());

		Product product7 = new Product("Galaxy Tablet", 2300, 1, brand4, category2, new Date());
		Product product8 = new Product("Ipad", 2600, 3, brand5, category2, new Date());
		Product product9 = new Product("Sony Tablet", 2400, 1, brand3, category2, new Date());

		productRepository.saveBrand(brand1);
		productRepository.saveBrand(brand2);
		productRepository.saveBrand(brand3);
		productRepository.saveBrand(brand4);
		productRepository.saveBrand(brand5);

		productRepository.saveCategory(category1);
		productRepository.saveCategory(category2);
		productRepository.saveCategory(category3);

		productRepository.saveProduct(product1);
		productRepository.saveProduct(product2);
		productRepository.saveProduct(product3);
		productRepository.saveProduct(product4);
		productRepository.saveProduct(product5);
		productRepository.saveProduct(product6);
		productRepository.saveProduct(product7);
		productRepository.saveProduct(product8);
		productRepository.saveProduct(product9);

	}

}
