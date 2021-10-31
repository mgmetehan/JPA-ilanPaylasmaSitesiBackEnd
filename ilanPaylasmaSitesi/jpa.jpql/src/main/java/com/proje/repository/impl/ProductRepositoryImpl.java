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
		// ürünü kaydetiyor ve kaydetiði ürünü gösteriyor
		this.entityTransaction.begin();

		this.entityManager.persist(product);

		this.entityTransaction.commit();

		return product;
	}

	@Override
	public Brand saveBrand(Brand brand) {
		// marka kaydetiyor ve kaydetiði markayý gösteriyor
		this.entityTransaction.begin();

		this.entityManager.persist(brand);

		this.entityTransaction.commit();

		return brand;
	}

	@Override
	public Category saveCategory(Category category) {
		// category kaydetiyor ve kaydetiði category gösteriyor
		this.entityTransaction.begin();

		this.entityManager.persist(category);

		this.entityTransaction.commit();

		return category;
	}

	@Override
	public List<Product> findProducts() {
		// Tüm productlarýn listesini al

		// Query query = this.entityManager.createQuery(ProductQueries.findProducts);
		// List<Product> products = query.getResultList();

		TypedQuery<Product> typedQuery = this.entityManager.createQuery(ProductQueries.findProducts, Product.class);
		List<Product> products = typedQuery.getResultList();
//2 yolluda kullanabilirsin ama typedQuery daha iyi hem sorgumuzu hem de sorgunun çýktýsýnýn tipini veriyoruz.
// !Çýktý türü vermedende kullanabiliriz
		return products;
	}

	@Override
	public List<Product> findProductsEntities(int firstResult, int maxResult) {
		TypedQuery<Product> typedQuery = this.entityManager.createQuery(ProductQueries.findProducts, Product.class);
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

	@Override
	public Product findProductById(int productId) {// parametreli sorgu
		// Id ismi verilen productý bul
		TypedQuery<Product> typedQuery = this.entityManager.createQuery(ProductQueries.findProductById, Product.class);
		// "SELECT p FROM Product p WHERE p.productId=:productId" ->sorgu
		typedQuery.setParameter("productId", productId);

		// ilk kýsým bizim sorgu kýsmýnda verdiðimiz ":" noktalý isim sonraki ise bizim
		// burda verdiðimiz deðer.
		// Kýsaca ilk hangi parametreye deðer vereceðimizi yazýyoruz

		Product product = typedQuery.getSingleResult();
		return product;
	}

	@Override
	public List<ProductDetails> findProductDetails() {
		// productDetails in içini doldurduk ve onun tamamýný getir dedik
		TypedQuery<ProductDetails> typedQuery = this.entityManager.createQuery(ProductQueries.findProductDetails,
				ProductDetails.class);

		List<ProductDetails> productDetails = typedQuery.getResultList();

		return productDetails;
	}

	@Override
	public ProductDetails findProductDetailsById(int productId) {
		// productDetails in içini doldurduk ve id si verileni getir dedik
		TypedQuery<ProductDetails> typedQuery = this.entityManager.createQuery(ProductQueries.findProductDetailById,
				ProductDetails.class);

		typedQuery.setParameter("productId", productId);

		ProductDetails productDetails = typedQuery.getSingleResult();

		return productDetails;
	}

	@Override
	public List<String> findProductName() {
		// Bütün ürünlerin ismini getirir
		TypedQuery<String> typedQuery = this.entityManager.createQuery(ProductQueries.findProductNames, String.class);

		List<String> productName = typedQuery.getResultList();

		return productName;
	}

	@Override
	public List<Object[]> findProductNameAndPrice() {
		// Ürürünün ismini ve fiyatýný getir
		// Dizi beklediði için typed yerine direkt query kullandýk
		Query query = this.entityManager.createQuery(ProductQueries.findProductNamePrice);

		List<Object[]> productNameAndPrice = query.getResultList();

		return productNameAndPrice;
	}

	@Override
	public List<Product> findGreatPrice(double unitPrice) {
		// Belirlediðimiz fiyattan daha yüksek olanlarý getir
		TypedQuery<Product> typedQuery = this.entityManager.createQuery(ProductQueries.findGreatPrice, Product.class);

		typedQuery.setParameter("unitPrice", unitPrice);

		List<Product> products = typedQuery.getResultList();

		return products;
	}

	@Override
	public List<Product> findGreatAndLessPrice(double minUnitPrice, double maxUnitPrice) {
		// Ýki fiyat arasý ürünleri getir
		TypedQuery<Product> typedQuery = this.entityManager.createQuery(ProductQueries.findGreatLessPrice,
				Product.class);

		typedQuery.setParameter("minUnitPrice", minUnitPrice);
		typedQuery.setParameter("maxUnitPrice", maxUnitPrice);

		List<Product> products = typedQuery.getResultList();
		return products;
	}

	@Override
	public List<Product> findBetweenPrice(double minUnitPrice, double maxUnitPrice) {
		// Ýki fiyat arasý ürünleri getir daha kolay hali
		TypedQuery<Product> typedQuery = this.entityManager.createQuery(ProductQueries.findBetweenPrice, Product.class);

		typedQuery.setParameter("minUnitPrice", minUnitPrice);
		typedQuery.setParameter("maxUnitPrice", maxUnitPrice);

		List<Product> products = typedQuery.getResultList();
		return products;
	}

	@Override
	public List<Product> findLikeProductName(String productName) {
		// Bizim yazdýðýmýz aratmaya göre onlarý içeren ismi getir// örnek isminin ilk 2
		// harfini aratýp aramak gibi
		TypedQuery<Product> typedQuery = this.entityManager.createQuery(ProductQueries.findLikeProductName,
				Product.class);

		typedQuery.setParameter("productName", "%" + productName + "%");
		// like komutun özelliði olduðu için "%" koyduk bu sayede yazdýðýmýz kelimeyi
		// içeriðin hem baþýnda hem sonunda hem de ortada olmasýný bakýyor

		List<Product> products = typedQuery.getResultList();

		return products;
	}

	@Override
	public List<Product> findInCategoryName(String categoryName1, String categoryName2) {
		// 2 tane category ismi veriyoruz o categorydeki tüm ürünleri getir
		TypedQuery<Product> typedQuery = this.entityManager.createQuery(ProductQueries.findCategoryName, Product.class);

		typedQuery.setParameter("categoryName1", categoryName1);
		typedQuery.setParameter("categoryName2", categoryName2);

		List<Product> products = typedQuery.getResultList();
		return products;
	}

	@Override
	public List<Product> findAllProduct(int categoryId) {
		// All kýsmýn içindeki verdiðimiz categorydeki tüm ürünlerin fiyatýný getirdi
		// sonra gelen
		// fiyattan daha yüksek olan tüm ürünleri getir dedik
		TypedQuery<Product> typedQuery = this.entityManager.createQuery(ProductQueries.findAllProduct, Product.class);

		typedQuery.setParameter("categoryId", categoryId);

		List<Product> products = typedQuery.getResultList();
		return products;
	}

	@Override
	public List<Object[]> findFunctionsPrice() {
		// Ürünlerin fiyatýn ortalamasý,toplamý,max olaný,min olaný getir
		Query query = this.entityManager.createQuery(ProductQueries.findFunctionsPrice);

		List<Object[]> functionPrice = query.getResultList();

		return functionPrice;
	}

	@Override
	public List<Object[]> findGroupByCategory() {
		// Category ismine göre grupla ve ortlama fiyat getir
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
		// Fiyat bilgilerine göre sýrala
		TypedQuery<Product> typedQuery = this.entityManager.createQuery(ProductQueries.findOrderByPrice, Product.class);

		List<Product> products = typedQuery.getResultList();

		return products;
	}

}
