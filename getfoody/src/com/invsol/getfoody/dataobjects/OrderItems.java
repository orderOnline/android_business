package com.invsol.getfoody.dataobjects;

public class OrderItems {
	private String order_item_name;
	private String order_item_qty;

	public OrderItems(String order_item_name, String order_item_qty) {
		super();
		this.order_item_name = order_item_name;
		this.order_item_qty = order_item_qty;
	}

	public String getOrder_item_name() {
		return order_item_name;
	}

	public void setOrder_item_name(String order_item_name) {
		this.order_item_name = order_item_name;
	}

	public String getOrder_item_qty() {
		return order_item_qty;
	}

	public void setOrder_item_qty(String order_item_qty) {
		this.order_item_qty = order_item_qty;
	}

}
