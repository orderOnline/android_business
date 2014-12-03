package com.invsol.getfoody.dataobjects;

public class MenuItem {

	private int item_id, price, category_id;
	private String name, type;
	public MenuItem(int item_id, int price, int category_id, String name, String type) {
		super();
		this.item_id = item_id;
		this.price = price;
		this.category_id = category_id;
		this.name = name;
		this.type = type;
	}
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
