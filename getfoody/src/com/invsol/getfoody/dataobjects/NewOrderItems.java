package com.invsol.getfoody.dataobjects;

public class NewOrderItems {
	private int order_id;
	private String customer_name;
	private String customer_address;
	private long customer_phoneNumber;
	private String customer_extrainfo;
	private String order_status;
	private String order_deliverytimer;
	private String order_acceptancetimer;
	private OrderItems[] orderItems;
	private int orderBillAmount;
	private String timestamp;
	private String orderJson;
	private int deliveryTime;
	private boolean newMessageArrival;

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

	public long getCustomer_phoneNumber() {
		return customer_phoneNumber;
	}

	public void setCustomer_phoneNumber(long customer_phoneNumber) {
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

	public int getOrderBillAmount() {
		return orderBillAmount;
	}

	public void setOrderBillAmount(int orderBillAmount) {
		this.orderBillAmount = orderBillAmount;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public String getOrderJson() {
		return orderJson;
	}

	public void setOrderJson(String orderJson) {
		this.orderJson = orderJson;
	}

	public int getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(int deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public boolean isNewMessageArrival() {
		return newMessageArrival;
	}

	public void setNewMessageArrival(boolean newMessageArrival) {
		this.newMessageArrival = newMessageArrival;
	}
	
	
}
