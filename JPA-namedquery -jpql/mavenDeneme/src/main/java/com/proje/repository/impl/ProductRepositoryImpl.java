package com.proje.repository.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import com.proje.model.Brand;
import com.proje.model.Category;
import com.proje.model.Product;
import com.proje.model.ProductDetails;
import com.proje.repository.ProductRepository;
public class ProductRepositoryImpl implements ProductRepository {
	public EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence-unit");
	public EntityManager entityManager = entityManagerFactory.createEntityManager();
	public EntityTransaction entityTransaction = entityManager.getTransaction();

	public Product saveProduct(Product product) {
		// ürünü kaydetiyor ve kaydetiði ürünü gösteriyor
		entityTransaction.begin();

		entityManager.persist(product);

		entityTransaction.commit();

		return product;
	}

	public Brand saveBrand(Brand brand) {
		// marka kaydetiyor ve kaydetiði markayý gösteriyor
		entityTransaction.begin();

		entityManager.persist(brand);

		entityTransaction.commit();

		return brand;
	}

	public Category saveCategory(Category category) {
		// category kaydetiyor ve kaydetiði category gösteriyor
		entityTransaction.begin();

		entityManager.persist(category);

		entityTransaction.commit();

		return category;
	}

	public List<Product> findProducts() {
		// Tüm productlarýn listesini al

		TypedQuery<Product> typedQuery = this.entityManager.createNamedQuery("Product.findProducts", Product.class);
		List<Product> products = typedQuery.getResultList();
		// !Çýktý türü vermedende kullanabiliriz
		return products;
	}

	public List<Product> findProductsEntities(int firstResult, int maxResult) {
		TypedQuery<Product> typedQuery = this.entityManager.createNamedQuery("Product.findProducts", Product.class);
		// TypedQuery<Product> typedQuery = this.entityManager.createQuery("SELECT p
		// FROM Product p");
		// !!Sorguyu direkt böylede yazabiliriz

		// Tüm ürünleri getirir ama bizim verdiðimiz firstResulttan sonraki deðerleri
		// getirir örnek 10 dan baþlar
		// MaxResult ise kaç deðer getireceðini belirtir
		typedQuery.setFirstResult(firstResult);
		typedQuery.setMaxResults(maxResult);

		List<Product> products = typedQuery.getResultList();

		return products;

	}

	public Product findProductById(int productId) {// parametreli sorgu
		// Id ismi verilen productý bul
		TypedQuery<Product> typedQuery = this.entityManager.createNamedQuery("Product.findById", Product.class);
		// "SELECT p FROM Product p WHERE p.productId=:productId" ->sorgu
		typedQuery.setParameter("productId", productId);

		// ilk kýsým bizim sorgu kýsmýnda verdiðimiz ":" noktalý isim sonraki ise bizim
		// burda verdiðimiz deðer.
		// Kýsaca ilk hangi parametreye deðer vereceðimizi yazýyoruz

		Product product = typedQuery.getSingleResult();
		return product;
	}

	public List<ProductDetails> findProductDetails() {
		// productDetails in içini doldurduk ve onun tamamýný getir dedik
		TypedQuery<ProductDetails> typedQuery = this.entityManager.createNamedQuery("Product.findProductDetails",
				ProductDetails.class);

		List<ProductDetails> productDetails = typedQuery.getResultList();

		return productDetails;
	}

	public List<Product> findByCategoryName(String categoryName) {
		TypedQuery<Product> typedQuery = this.entityManager.createNamedQuery("Product.findByCategoryName",
				Product.class);
		typedQuery.setParameter("name", categoryName);
		List<Product> products = typedQuery.getResultList();

		return products;
	}

	public int findProductCount() {
		TypedQuery<Long> typedQuery = this.entityManager.createNamedQuery("Product.findCount", Long.class);

		Long count = typedQuery.getSingleResult();

		return count.intValue();
	}

	public List<Product> findAddDateLater(Date addDate) {
		TypedQuery<Product> typedQuery = this.entityManager.createNamedQuery("Product.findAddDateLater", Product.class);
		// zamanýn türünü yaz
		typedQuery.setParameter("addDate", addDate, TemporalType.TIMESTAMP);

		return typedQuery.getResultList();
	}

}
