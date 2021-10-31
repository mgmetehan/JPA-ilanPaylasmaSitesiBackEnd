package com.proje.repository;

import java.util.Date;
import java.util.List;

import com.proje.model.Brand;
import com.proje.model.Category;
import com.proje.model.Product;
import com.proje.model.ProductDetails;
public interface ProductRepository {// querieleri kullanmak için

	Product saveProduct(Product product);

	Brand saveBrand(Brand brand);

	Category saveCategory(Category category);

	List<Product> findProducts();

	List<Product> findProductsEntities(int firstResult, int maxResult);

	Product findProductById(int productId);

	List<ProductDetails> findProductDetails();

	List<Product> findByCategoryName(String categoryName);

	int findProductCount();

	List<Product> findAddDateLater(Date addDate);

}
