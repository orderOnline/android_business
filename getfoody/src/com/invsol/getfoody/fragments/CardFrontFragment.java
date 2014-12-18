package com.invsol.getfoody.fragments;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.invsol.getfoody.R;
import com.invsol.getfoody.constants.Constants;
import com.invsol.getfoody.controllers.AppEventsController;
import com.invsol.getfoody.dataobjects.NewOrderItems;
import com.invsol.getfoody.dataobjects.OrderItems;

public class CardFrontFragment  extends Fragment {
	
	OnFrontCardOrderSelectedListener mListener;
	Activity activity;
	private NewOrderItems currentItem;
	private int order_id;
	private SparseArray<NewOrderItems> newItems;
	private String orderData;
	
	// Container Activity must implement this interface
    public interface OnFrontCardOrderSelectedListener {
        public void onFrontCardOrderSelected(String orderStatus, View view);
    }
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
        try {
            mListener = (OnFrontCardOrderSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFrontCardOrderSelectedListener");
        }
    }
    
    public CardFrontFragment( String orderData ) {
    	this.orderData = orderData;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	View mainview = inflater.inflate(R.layout.fragment_card_front, container, false);
    	
		JSONObject json;
		try {
			json = new JSONObject(orderData);
			//AppEventsController.getInstance().getModelFacade().getResModel().addOrderItem(json);
			newItems = AppEventsController.getInstance().getModelFacade().getResModel().getPendingOrderItems();
			order_id = json.getInt(Constants.JSON_ORDER_ID);
			currentItem = newItems.get(order_id); 
	    	TextView customer_name = (TextView)mainview.findViewById(R.id.textview_customer_name);
	    	customer_name.setText(currentItem.getCustomer_name());
	    	TextView customer_address = (TextView)mainview.findViewById(R.id.textview_customer_address);
	    	customer_address.setText(currentItem.getCustomer_address());
	    	TextView customer_phone = (TextView)mainview.findViewById(R.id.textview_customer_phone);
	    	customer_phone.setText(""+currentItem.getCustomer_phoneNumber());
	    	TextView customer_info = (TextView)mainview.findViewById(R.id.textview_customer_info);
	    	customer_info.setText(currentItem.getCustomer_extrainfo());
	    	TextView order_billamount = (TextView)mainview.findViewById(R.id.textview_bill_amount_value);
	    	order_billamount.setText(""+currentItem.getOrderBillAmount());
	    	
	    	LinearLayout newOrder_layout = (LinearLayout) mainview.findViewById(R.id.layout_neworder);
	    	
	    	OrderItems[] dataItems = currentItem.getOrderItems();
	    	for (int current = 0; current < dataItems.length; current++) {
	 		   View view = inflater.inflate(R.layout.item_neworder, newOrder_layout, false);
	 		   TextView item_name = (TextView)view.findViewById(R.id.textview_item_name);
	 		   item_name.setText(dataItems[current].getOrder_item_name());
	 		   TextView item_qty = (TextView)view.findViewById(R.id.textview_item_qty);
	 		   item_qty.setText(""+dataItems[current].getOrder_item_qty());
	 		   newOrder_layout.addView(view);
	 		}
	    	
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TextView btn_accept = (TextView)mainview.findViewById(R.id.btn_accept_order);
		btn_accept.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				mListener.onFrontCardOrderSelected(Constants.JSON_ORDER_STATUS_ACCEPTED, view);
				/*AppEventsController.getInstance().getModelFacade().getResModel().getPendingOrderItems().remove(order_id);
				currentItem.setOrder_status(Constants.JSON_ORDER_STATUS_ACCEPTED);
				AppEventsController.getInstance().getModelFacade().getResModel().addOrders(currentItem);
				if( newItems.size() > 0 ){
					int key = newItems.keyAt(0);
					Intent intent = getIntent();
					intent.putExtra("ORDER", newItems.get(key).getOrderJson());
					finish();
					startActivity(intent);
				}else{
					Intent screenChangeIntent = null;
					screenChangeIntent = new Intent(OrdersActivity.this,
							HomeActivity.class);
					OrdersActivity.this.startActivity(screenChangeIntent);
					OrdersActivity.this.finish();
				}*/
			}
		});
		
		TextView btn_decline = (TextView)mainview.findViewById(R.id.btn_reject_order);
		btn_decline.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				AppEventsController.getInstance().getModelFacade().getResModel().getPendingOrderItems().remove(order_id);
				currentItem.setOrder_status(Constants.JSON_ORDER_STATUS_DECLINED);
				AppEventsController.getInstance().getModelFacade().getResModel().addOrders(currentItem);
				mListener.onFrontCardOrderSelected(Constants.JSON_ORDER_STATUS_DECLINED, view);
				/*AppEventsController.getInstance().getModelFacade().getResModel().getPendingOrderItems().remove(order_id);
				currentItem.setOrder_status(Constants.JSON_ORDER_STATUS_DECLINED);
				AppEventsController.getInstance().getModelFacade().getResModel().addOrders(currentItem);
				if( newItems.size() > 0 ){
					int key = newItems.keyAt(0);
					Intent intent = getIntent();
					intent.putExtra("ORDER", newItems.get(key).getOrderJson());
					finish();
					startActivity(intent);
				}else{
					Intent screenChangeIntent = null;
					screenChangeIntent = new Intent(OrdersActivity.this,
							HomeActivity.class);
					OrdersActivity.this.startActivity(screenChangeIntent);
					OrdersActivity.this.finish();
				}*/
			}
		});
        return mainview;
    }

	public void setOrderData(String orderData) {
		this.orderData = orderData;
	}
    
    
    
    
}