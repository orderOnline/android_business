package com.invsol.getfoody.dataobjects;

public class ReviewsItem {
	private String review_postedby_user;
	private String review_posted_timeago;
	private String review_text;
	
	
	
	public ReviewsItem(String review_postedby_user,
			String review_posted_timeago, String review_text) {
		super();
		this.review_postedby_user = review_postedby_user;
		this.review_posted_timeago = review_posted_timeago;
		this.review_text = review_text;
	}
	public String getReview_postedby_user() {
		return review_postedby_user;
	}
	public void setReview_postedby_user(String review_postedby_user) {
		this.review_postedby_user = review_postedby_user;
	}
	public String getReview_posted_timeago() {
		return review_posted_timeago;
	}
	public void setReview_posted_timeago(String review_posted_timeago) {
		this.review_posted_timeago = review_posted_timeago;
	}
	public String getReview_text() {
		return review_text;
	}
	public void setReview_text(String review_text) {
		this.review_text = review_text;
	}
	
	
}
