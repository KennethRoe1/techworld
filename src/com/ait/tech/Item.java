package com.ait.tech;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Item {
	
	public Item() {
		
	}
	
	public Item(int id, String name, String description, String category, double price, int stock, String pic) {
		
		if (name==null)
			throw new IllegalArgumentException("Name cannot be null");
		
		if (description==null)
			throw new IllegalArgumentException("description cannot be null");
		
		if (category==null)
			throw new IllegalArgumentException("category cannot be null");
		
		if (pic==null)
			throw new IllegalArgumentException("picture cannot be null");
		
		
		if (name.trim().length()==0)
			throw new IllegalArgumentException("Name cannot be empty");
		
		if (description.trim().length()==0)
			throw new IllegalArgumentException("description cannot be empty");
		
		if (category.trim().length()==0)
			throw new IllegalArgumentException("category cannot be empty");
		
		if (pic.trim().length()==0)
			throw new IllegalArgumentException("pic cannot be empty");
		
		if (id<=0)
			throw new IllegalArgumentException("ID has to be a positive number");
		
		if (price<=0)
			throw new IllegalArgumentException("ID has to be a positive number");
		
		if (stock<=0)
			throw new IllegalArgumentException("ID has to be a positive number");
				
		this.name = name;
		this.id = id;
		this.description = description;
		this.category = category;
		this.pic = pic;
		this.price = price;
		this.stock = stock;
		
	}
	
	private int id;
	private String name;
	private String description;
	private String category;
	private double price;
	private int stock;
	private String pic;
	
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
}
