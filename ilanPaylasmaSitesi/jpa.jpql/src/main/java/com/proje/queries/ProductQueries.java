package com.proje.queries;

public class ProductQueries {
	/*
	 * Bu normal bir sql sorgu =Select * from Product 
	 * Bu sorguyu biz hql de =Select * p from Product p þeklinde 
	 * oluþturuyoruz sanki o tablonun bi deðiþkenini yaratýyor gibi
	 * 
	 * Ör/ Select * from Product Where productId=2 
	 * HQL/ Select p from Product p Where p.productId=2
	 * 
	 */

	public static final String findProducts = "SELECT p FROM Product p";
	// Tüm productlarýn listesini al

	public static final String findProductById = "SELECT p FROM Product p WHERE p.productId=:productId";
	// Id ismi verilen productý bul

	public static final String findProductDetails = "SELECT new com.proje.model.ProductDetails(p.name, p.unitPrice, p.avaible, b.name, c.name) "
			+ "FROM Product p LEFT JOIN p.brand b LEFT JOIN p.category c";
	// productDetails in içini doldurduk ve onun tamamýný getir dedik

	public static final String findProductDetailById = "SELECT new com.proje.model.ProductDetails(p.name, p.unitPrice, p.avaible, b.name, c.name) "
			+ "FROM Product p LEFT JOIN p.brand b LEFT JOIN p.category c WHERE p.productId=: productId";
	// productDetails in içini doldurduk ve id si verileni getir dedik

	public static final String findProductNames = "SELECT p.name FROM Product p";
	// Bütün ürünlerin ismini getirir

	public static final String findProductNamePrice = "SELECT p.name, p.unitPrice FROM Product p";
	// Ürürünün ismini ve fiyatýný getir

	public static final String findGreatPrice = "SELECT p FROM Product p WHERE p.unitPrice > :unitPrice";
	// Belirlediðimiz fiyattan daha yüksek olanlarý getir

	public static final String findGreatLessPrice = "SELECT p FROM Product p WHERE p.unitPrice > :minUnitPrice AND p.unitPrice < :maxUnitPrice";
	// Ýki fiyat arasý ürünleri getir

	public static final String findBetweenPrice = "SELECT p FROM Product p WHERE p.unitPrice BETWEEN :minUnitPrice AND :maxUnitPrice";
	// Ýki fiyat arasý ürünleri getir daha kolay hali

	public static final String findLikeProductName = "SELECT p FROM Product p WHERE p.name LIKE :productName";
	// Bizim yazdýðýmýz aratmaya göre onlarý içeren ismi getir// örnek isminin ilk 2 harfini aratýp aramak gibi

	public static final String findCategoryName = "SELECT p FROM Product p LEFT JOIN p.category c WHERE c.name IN(:categoryName1, :categoryName2)";
	// 2 tane category ismi veriyoruz o categorydeki tüm ürünleri getir

	public static final String findAllProduct = "SELECT p FROM Product p WHERE p.unitPrice > ALL(SELECT p2.unitPrice FROM Product p2 WHERE "
			+ "p2.category.categoryId= :categoryId";// All kýsmýn içindeki verdiðimiz categorydeki tüm ürünlerin fiyatýný getirdi sonra gelen
	// fiyattan daha yüksek olan tüm ürünleri getir dedik

	public static final String findFunctionsPrice = "SELECT AVG(p.unitPrice), SUM(p.unitPrice), MAX(p.unitPrice), MIN(p.unitPrice), COUNT(p) FROM Product p";
	// Ürünlerin fiyatýn ortalamasý,toplamý,max olaný,min olaný getir

	public static final String findGroupByCategory = "SELECT p.category.categoryName, AVG(p.unitPrice) FROM Product p GROUP BY p.category.categoryName";
	// Category ismine göre grupla ve ortlama fiyat getir

	public static final String findGroupByHavingCategory = "SELECT p.category.categoryName, AVG(p.unitPrice) FROM Product p GROUP BY p.category.categoryName "
			+ "HAVING AVG(p.unitPrice)> :unitPrice";

	public static final String findOrderByPrice = "SELECT p FROM Product p ORDER BY p.unitPrice DESC";
	// Fiyat bilgilerine göre sýrala
}
