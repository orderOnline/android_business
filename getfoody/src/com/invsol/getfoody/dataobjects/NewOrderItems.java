package com.invsol.getfoody.dataobjects;

public class NewOrderItems {
	private String customer_name;
	private String customer_address;
	private String customer_phoneNumber;
	private String customer_extrainfo;
	private String order_status;
	private String order_deliverytimer;
	private String order_acceptancetimer;
	private OrderItems[] orderItems;
	private String orderBillAmount;

	public NewOrderItems() {
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCustomer_address() {
		return customer_address;
	}

	public void setCustomer_address(String customer_address) {
		this.customer_address = customer_address;
	}

	public String getCustomer_phoneNumber() {
		return customer_phoneNumber;
	}

	public void setCustomer_phoneNumber(String customer_phoneNumber) {
		this.customer_phoneNumber = customer_phoneNumber;
	}

	public String getCustomer_extrainfo() {
		return customer_extrainfo;
	}

	public void setCustomer_extrainfo(String customer_extrainfo) {
		this.customer_extrainfo = customer_extrainfo;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	public String getOrder_deliverytimer() {
		return order_deliverytimer;
	}

	public void setOrder_deliverytimer(String order_deliverytimer) {
		this.order_deliverytimer = order_deliverytimer;
	}

	public String getOrder_acceptancetimer() {
		return order_acceptancetimer;
	}

	public void setOrder_acceptancetimer(String order_acceptancetimer) {
		this.order_acceptancetimer = order_acceptancetimer;
	}

	public OrderItems[] getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(OrderItems[] orderItems) {
		this.orderItems = orderItems;
	}

	public String getOrderBillAmount() {
		return orderBillAmount;
	}

	public void setOrderBillAmount(String orderBillAmount) {
		this.orderBillAmount = orderBillAmount;
	}

}
