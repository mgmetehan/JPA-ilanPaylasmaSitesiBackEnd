package com.proje.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({ @NamedQuery(name = "Product.findProducts", query = "SELECT p FROM Product p"),
		@NamedQuery(name = "Product.findById", query = "SELECT p FROM Product p WHERE p.productId= :productId"),
		@NamedQuery(name = "Product.findByCategoryName", query = "SELECT p FROM Product p WHERE p.category.name= :name"),
		@NamedQuery(name = "Product.findCount", query = "SELECT Count(p) FROM Product p"),
		@NamedQuery(name = "Product.findAddDateLater", query = "SELECT p FROM Product p WHERE p.addDate > :addDate"),

		@NamedQuery(name = "Product.findProductDetails", query = "SELECT new com.proje.model.ProductDetails(p.name, p.unitPrice,"
				+ "p.avaible, p.brand.name, p.category.name) FROM Product p") })
public class Product implements Serializable {

	private static final long SerialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;

	private String name;

	private double unitPrice;

	private int avaible;

	@ManyToOne
	@JoinColumn(name = "brandId")
	private Brand brand;

	@ManyToOne
	@JoinColumn(name = "categoryId")
	private Category category;

	@Temporal(TemporalType.TIMESTAMP)
	private Date addDate;

	private Date updateDate;

	public Product() {
	}

	public Product(String name, double unitPrice, int avaible, Brand brand, Category category, Date addDate) {
		this.name = name;
		this.unitPrice = unitPrice;
		this.avaible = avaible;
		this.brand = brand;
		this.category = category;
		this.addDate = addDate;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", unitPrice=" + unitPrice + ", avaible="
				+ avaible + ", brand=" + brand + ", category=" + category + ", addDate=" + addDate + ", updateDate="
				+ updateDate + "]";
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getAvaible() {
		return avaible;
	}

	public void setAvaible(int avaible) {
		this.avaible = avaible;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
