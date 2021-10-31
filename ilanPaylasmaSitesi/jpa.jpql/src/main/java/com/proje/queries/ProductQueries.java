package com.proje.queries;

public class ProductQueries {
	/*
	 * Bu normal bir sql sorgu =Select * from Product 
	 * Bu sorguyu biz hql de =Select * p from Product p �eklinde 
	 * olu�turuyoruz sanki o tablonun bi de�i�kenini yarat�yor gibi
	 * 
	 * �r/ Select * from Product Where productId=2 
	 * HQL/ Select p from Product p Where p.productId=2
	 * 
	 */

	public static final String findProducts = "SELECT p FROM Product p";
	// T�m productlar�n listesini al

	public static final String findProductById = "SELECT p FROM Product p WHERE p.productId=:productId";
	// Id ismi verilen product� bul

	public static final String findProductDetails = "SELECT new com.proje.model.ProductDetails(p.name, p.unitPrice, p.avaible, b.name, c.name) "
			+ "FROM Product p LEFT JOIN p.brand b LEFT JOIN p.category c";
	// productDetails in i�ini doldurduk ve onun tamam�n� getir dedik

	public static final String findProductDetailById = "SELECT new com.proje.model.ProductDetails(p.name, p.unitPrice, p.avaible, b.name, c.name) "
			+ "FROM Product p LEFT JOIN p.brand b LEFT JOIN p.category c WHERE p.productId=: productId";
	// productDetails in i�ini doldurduk ve id si verileni getir dedik

	public static final String findProductNames = "SELECT p.name FROM Product p";
	// B�t�n �r�nlerin ismini getirir

	public static final String findProductNamePrice = "SELECT p.name, p.unitPrice FROM Product p";
	// �r�r�n�n ismini ve fiyat�n� getir

	public static final String findGreatPrice = "SELECT p FROM Product p WHERE p.unitPrice > :unitPrice";
	// Belirledi�imiz fiyattan daha y�ksek olanlar� getir

	public static final String findGreatLessPrice = "SELECT p FROM Product p WHERE p.unitPrice > :minUnitPrice AND p.unitPrice < :maxUnitPrice";
	// �ki fiyat aras� �r�nleri getir

	public static final String findBetweenPrice = "SELECT p FROM Product p WHERE p.unitPrice BETWEEN :minUnitPrice AND :maxUnitPrice";
	// �ki fiyat aras� �r�nleri getir daha kolay hali

	public static final String findLikeProductName = "SELECT p FROM Product p WHERE p.name LIKE :productName";
	// Bizim yazd���m�z aratmaya g�re onlar� i�eren ismi getir// �rnek isminin ilk 2 harfini arat�p aramak gibi

	public static final String findCategoryName = "SELECT p FROM Product p LEFT JOIN p.category c WHERE c.name IN(:categoryName1, :categoryName2)";
	// 2 tane category ismi veriyoruz o categorydeki t�m �r�nleri getir

	public static final String findAllProduct = "SELECT p FROM Product p WHERE p.unitPrice > ALL(SELECT p2.unitPrice FROM Product p2 WHERE "
			+ "p2.category.categoryId= :categoryId";// All k�sm�n i�indeki verdi�imiz categorydeki t�m �r�nlerin fiyat�n� getirdi sonra gelen
	// fiyattan daha y�ksek olan t�m �r�nleri getir dedik

	public static final String findFunctionsPrice = "SELECT AVG(p.unitPrice), SUM(p.unitPrice), MAX(p.unitPrice), MIN(p.unitPrice), COUNT(p) FROM Product p";
	// �r�nlerin fiyat�n ortalamas�,toplam�,max olan�,min olan� getir

	public static final String findGroupByCategory = "SELECT p.category.categoryName, AVG(p.unitPrice) FROM Product p GROUP BY p.category.categoryName";
	// Category ismine g�re grupla ve ortlama fiyat getir

	public static final String findGroupByHavingCategory = "SELECT p.category.categoryName, AVG(p.unitPrice) FROM Product p GROUP BY p.category.categoryName "
			+ "HAVING AVG(p.unitPrice)> :unitPrice";

	public static final String findOrderByPrice = "SELECT p FROM Product p ORDER BY p.unitPrice DESC";
	// Fiyat bilgilerine g�re s�rala
}
