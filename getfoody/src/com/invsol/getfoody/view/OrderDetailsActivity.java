package com.invsol.getfoody.view;

import org.json.JSONException;
import org.json.JSONObject;

import com.invsol.getfoody.R;
import com.invsol.getfoody.constants.Constants;
import com.invsol.getfoody.controllers.AppEventsController;
import com.invsol.getfoody.dataobjects.NewOrderItems;
import com.invsol.getfoody.dataobjects.OrderItems;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OrderDetailsActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_details);
		
		try {
			JSONObject orderItemData = new JSONObject(getIntent().getStringExtra("ORDER"));
			int order_id = orderItemData.getInt(Constants.JSON_ORDER_ID);
			SparseArray<NewOrderItems> orderItems = AppEventsController.getInstance().getModelFacade().getResModel().getOrderItems();
			NewOrderItems currentItem = orderItems.get(order_id); 
	    	TextView customer_name = (TextView)findViewById(R.id.textview_customer_name);
	    	customer_name.setText(currentItem.getCustomer_name());
	    	TextView customer_address = (TextView)findViewById(R.id.textview_customer_address);
	    	customer_address.setText(currentItem.getCustomer_address());
	    	TextView customer_phone = (TextView)findViewById(R.id.textview_customer_phone);
	    	customer_phone.setText(""+currentItem.getCustomer_phoneNumber());
	    	TextView customer_info = (TextView)findViewById(R.id.textview_customer_info);
	    	customer_info.setText(currentItem.getCustomer_extrainfo());
	    	TextView order_billamount = (TextView)findViewById(R.id.textview_bill_amount_value);
	    	order_billamount.setText(""+currentItem.getOrderBillAmount());
	    	if( currentItem.getOrder_status().equals(Constants.JSON_ORDER_STATUS_ACCEPTED) ){
	    		TextView order_status = (TextView)findViewById(R.id.textview_offer_acceptance_status);
		    	order_status.setText(currentItem.getOrder_status());
		    	order_status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.order_status_accepted, 0, 0, 0);
	    	}else if( currentItem.getOrder_status().equals(Constants.JSON_ORDER_STATUS_DECLINED) ){
	    		TextView order_status = (TextView)findViewById(R.id.textview_offer_acceptance_status);
		    	order_status.setText(currentItem.getOrder_status());
		    	order_status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.order_status_missed, 0, 0, 0);
			}	
	    	
	    	
	    	LinearLayout newOrder_layout = (LinearLayout)findViewById(R.id.layout_neworder);
	    	
	    	OrderItems[] dataItems = currentItem.getOrderItems();
	    	for (int current = 0; current < dataItems.length; current++) {
	 		   View view = getLayoutInflater().inflate(R.layout.item_neworder, newOrder_layout, false);
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
	}

}
