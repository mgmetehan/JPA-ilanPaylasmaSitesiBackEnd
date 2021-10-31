package com.proje.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.proje.model.Brand;
import com.proje.model.Category;
import com.proje.model.Product;
import com.proje.model.ProductDetails;
import com.proje.queries.ProductQueries;
import com.proje.repository.ProductRepository;

public class ProductRepositoryImpl implements ProductRepository {

	public EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence-unit");
	public EntityManager entityManager = entityManagerFactory.createEntityManager();
	public EntityTransaction entityTransaction = entityManager.getTransaction();

	@Override
	public Product saveProduct(Product product) {
		// �r�n� kaydetiyor ve kaydeti�i �r�n� g�steriyor
		this.entityTransaction.begin();

		this.entityManager.persist(product);

		this.entityTransaction.commit();

		return product;
	}

	@Override
	public Brand saveBrand(Brand brand) {
		// marka kaydetiyor ve kaydeti�i markay� g�steriyor
		this.entityTransaction.begin();

		this.entityManager.persist(brand);

		this.entityTransaction.commit();

		return brand;
	}

	@Override
	public Category saveCategory(Category category) {
		// category kaydetiyor ve kaydeti�i category g�steriyor
		this.entityTransaction.begin();

		this.entityManager.persist(category);

		this.entityTransaction.commit();

		return category;
	}

	@Override
	public List<Product> findProducts() {
		// T�m productlar�n listesini al

		// Query query = this.entityManager.createQuery(ProductQueries.findProducts);
		// List<Product> products = query.getResultList();

		TypedQuery<Product> typedQuery = this.entityManager.createQuery(ProductQueries.findProducts, Product.class);
		List<Product> products = typedQuery.getResultList();
//2 yolluda kullanabilirsin ama typedQuery daha iyi hem sorgumuzu hem de sorgunun ��kt�s�n�n tipini veriyoruz.
// !��kt� t�r� vermedende kullanabiliriz
		return products;
	}

	@Override
	public List<Product> findProductsEntities(int firstResult, int maxResult) {
		TypedQuery<Product> typedQuery = this.entityManager.createQuery(ProductQueries.findProducts, Product.class);
		// TypedQuery<Product> typedQuery = this.entityManager.createQuery("SELECT p
		// FROM Product p");
		// !!Sorguyu direkt b�ylede yazabiliriz

		// T�m �r�nleri getirir ama bizim verdi�imiz firstResulttan sonraki de�erleri
		// getirir �rnek 10 dan ba�lar
		// MaxResult ise ka� de�er getirece�ini belirtir
		typedQuery.setFirstResult(firstResult);
		typedQuery.setMaxResults(maxResult);

		List<Product> products = typedQuery.getResultList();

		return products;

	}

	@Override
	public Product findProductById(int productId) {// parametreli sorgu
		// Id ismi verilen product� bul
		TypedQuery<Product> typedQuery = this.entityManager.createQuery(ProductQueries.findProductById, Product.class);
		// "SELECT p FROM Product p WHERE p.productId=:productId" ->sorgu
		typedQuery.setParameter("productId", productId);

		// ilk k�s�m bizim sorgu k�sm�nda verdi�imiz ":" noktal� isim sonraki ise bizim
		// burda verdi�imiz de�er.
		// K�saca ilk hangi parametreye de�er verece�imizi yaz�yoruz

		Product product = typedQuery.getSingleResult();
		return product;
	}

	@Override
	public List<ProductDetails> findProductDetails() {
		// productDetails in i�ini doldurduk ve onun tamam�n� getir dedik
		TypedQuery<ProductDetails> typedQuery = this.entityManager.createQuery(ProductQueries.findProductDetails,
				ProductDetails.class);

		List<ProductDetails> productDetails = typedQuery.getResultList();

		return productDetails;
	}

	@Override
	public ProductDetails findProductDetailsById(int productId) {
		// productDetails in i�ini doldurduk ve id si verileni getir dedik
		TypedQuery<ProductDetails> typedQuery = this.entityManager.createQuery(ProductQueries.findProductDetailById,
				ProductDetails.class);

		typedQuery.setParameter("productId", productId);

		ProductDetails productDetails = typedQuery.getSingleResult();

		return productDetails;
	}

	@Override
	public List<String> findProductName() {
		// B�t�n �r�nlerin ismini getirir
		TypedQuery<String> typedQuery = this.entityManager.createQuery(ProductQueries.findProductNames, String.class);

		List<String> productName = typedQuery.getResultList();

		return productName;
	}

	@Override
	public List<Object[]> findProductNameAndPrice() {
		// �r�r�n�n ismini ve fiyat�n� getir
		// Dizi bekledi�i i�in typed yerine direkt query kulland�k
		Query query = this.entityManager.createQuery(ProductQueries.findProductNamePrice);

		List<Object[]> productNameAndPrice = query.getResultList();

		return productNameAndPrice;
	}

	@Override
	public List<Product> findGreatPrice(double unitPrice) {
		// Belirledi�imiz fiyattan daha y�ksek olanlar� getir
		TypedQuery<Product> typedQuery = this.entityManager.createQuery(ProductQueries.findGreatPrice, Product.class);

		typedQuery.setParameter("unitPrice", unitPrice);

		List<Product> products = typedQuery.getResultList();

		return products;
	}

	@Override
	public List<Product> findGreatAndLessPrice(double minUnitPrice, double maxUnitPrice) {
		// �ki fiyat aras� �r�nleri getir
		TypedQuery<Product> typedQuery = this.entityManager.createQuery(ProductQueries.findGreatLessPrice,
				Product.class);

		typedQuery.setParameter("minUnitPrice", minUnitPrice);
		typedQuery.setParameter("maxUnitPrice", maxUnitPrice);

		List<Product> products = typedQuery.getResultList();
		return products;
	}

	@Override
	public List<Product> findBetweenPrice(double minUnitPrice, double maxUnitPrice) {
		// �ki fiyat aras� �r�nleri getir daha kolay hali
		TypedQuery<Product> typedQuery = this.entityManager.createQuery(ProductQueries.findBetweenPrice, Product.class);

		typedQuery.setParameter("minUnitPrice", minUnitPrice);
		typedQuery.setParameter("maxUnitPrice", maxUnitPrice);

		List<Product> products = typedQuery.getResultList();
		return products;
	}

	@Override
	public List<Product> findLikeProductName(String productName) {
		// Bizim yazd���m�z aratmaya g�re onlar� i�eren ismi getir// �rnek isminin ilk 2
		// harfini arat�p aramak gibi
		TypedQuery<Product> typedQuery = this.entityManager.createQuery(ProductQueries.findLikeProductName,
				Product.class);

		typedQuery.setParameter("productName", "%" + productName + "%");
		// like komutun �zelli�i oldu�u i�in "%" koyduk bu sayede yazd���m�z kelimeyi
		// i�eri�in hem ba��nda hem sonunda hem de ortada olmas�n� bak�yor

		List<Product> products = typedQuery.getResultList();

		return products;
	}

	@Override
	public List<Product> findInCategoryName(String categoryName1, String categoryName2) {
		// 2 tane category ismi veriyoruz o categorydeki t�m �r�nleri getir
		TypedQuery<Product> typedQuery = this.entityManager.createQuery(ProductQueries.findCategoryName, Product.class);

		typedQuery.setParameter("categoryName1", categoryName1);
		typedQuery.setParameter("categoryName2", categoryName2);

		List<Product> products = typedQuery.getResultList();
		return products;
	}

	@Override
	public List<Product> findAllProduct(int categoryId) {
		// All k�sm�n i�indeki verdi�imiz categorydeki t�m �r�nlerin fiyat�n� getirdi
		// sonra gelen
		// fiyattan daha y�ksek olan t�m �r�nleri getir dedik
		TypedQuery<Product> typedQuery = this.entityManager.createQuery(ProductQueries.findAllProduct, Product.class);

		typedQuery.setParameter("categoryId", categoryId);

		List<Product> products = typedQuery.getResultList();
		return products;
	}

	@Override
	public List<Object[]> findFunctionsPrice() {
		// �r�nlerin fiyat�n ortalamas�,toplam�,max olan�,min olan� getir
		Query query = this.entityManager.createQuery(ProductQueries.findFunctionsPrice);

		List<Object[]> functionPrice = query.getResultList();

		return functionPrice;
	}

	@Override
	public List<Object[]> findGroupByCategory() {
		// Category ismine g�re grupla ve ortlama fiyat getir
		Query query = this.entityManager.createQuery(ProductQueries.findGroupByCategory);

		List<Object[]> groupByCategory = query.getResultList();

		return groupByCategory;
	}

	@Override
	public List<Object[]> findGroupByHavingCategory(double unitPrice) {
		Query query = this.entityManager.createQuery(ProductQueries.findGroupByHavingCategory);

		List<Object[]> groupHavingByCategory = query.getResultList();

		return groupHavingByCategory;
	}

	@Override
	public List<Product> findOrderByPrice() {
		// Fiyat bilgilerine g�re s�rala
		TypedQuery<Product> typedQuery = this.entityManager.createQuery(ProductQueries.findOrderByPrice, Product.class);

		List<Product> products = typedQuery.getResultList();

		return products;
	}

}
