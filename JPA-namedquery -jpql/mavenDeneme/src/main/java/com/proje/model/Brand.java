package com.proje.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import javassist.SerialVersionUID;

@Entity
@NamedQuery(name="Brand.findById",query = "SELECT b FROM Brand b WHERE b.brandId= :brandId")
public class Brand implements Serializable {// Brand= marka demek

	// Verileri okurken seri olmasýný saðlýyor
	private static final long SerialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int brandId;

	private String name;

	@OneToMany(mappedBy = "brand")
	private List<Product> products;

	public Brand() {
	}

	public Brand(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Brand [brandId=" + brandId + ", name=" + name + "]";
	}

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
