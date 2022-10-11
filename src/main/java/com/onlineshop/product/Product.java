package com.onlineshop.product;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column (length = 36, nullable = false, unique = true) 
	private String name;
 
	@Column(name = "short_description", length = 512, nullable = false)
	private String shortDescription;

	@Column(name = "long_description", unique = true, length = 4096, nullable = true)
	private String fullDescription;

	@Column(name = "created_time")
	private Date createdTime;

	@Column(name = "updated_time")
	private Date updatedTime;

	private boolean enabled;

	@Column(name = "in_stock")
	private boolean inStock;

	private float price;

	@Column(name = "discount_percent")
	private float discountPercent;
	
	@Column(name = "main_image", nullable = true)
	private String mainImage;

	
	public Product() {
		super();
	}

	public Product(String name, String shortDescription, Date createdTime, boolean enabled, boolean inStock,
			float price) {
		super();
		this.name = name;
		this.shortDescription = shortDescription;
		this.createdTime = createdTime;
		this.enabled = enabled;
		this.inStock = inStock;
		this.price = price;
	}

	public Product(String name, String shortDescription, String fullDescription, Date createdTime, Date updatedTime,
			boolean enabled, boolean inStock, float price, float discountPercent) {
		super();
		this.name = name;
		this.shortDescription = shortDescription;
		this.fullDescription = fullDescription;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.enabled = enabled;
		this.inStock = inStock;
		this.price = price;
		this.discountPercent = discountPercent;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getFullDescription() {
		return fullDescription;
	}

	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(float discountPercent) {
		this.discountPercent = discountPercent;
	}

	public String getMainImage() {
		return mainImage;
	}

	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", shortDescription=" + shortDescription + ", fullDescription="
				+ fullDescription + ", createdTime=" + createdTime + ", updatedTime=" + updatedTime + ", enabled="
				+ enabled + ", inStock=" + inStock + ", price=" + price + ", discountPercent=" + discountPercent
				+ ", mainImage=" + mainImage + "]";
	}
	
	
}
