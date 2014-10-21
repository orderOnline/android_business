package com.invsol.getfoody.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.invsol.getfoody.R;
import com.invsol.getfoody.dataobjects.OrderItems;

public class OrdersActivity extends ActionBarActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);
		
		LinearLayout newOrder_layout = (LinearLayout) findViewById(R.id.layout_neworder);
		
		OrderItems[] orderDataItems = new OrderItems[2];
		for (int i = 0; i < 2; i++) {
			orderDataItems[i] = new OrderItems("dummy text", "X2");
		}
		
		
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		for (int current = 0; current < orderDataItems.length; current++) {
		   View view = inflater.inflate(R.layout.item_neworder, newOrder_layout, false);
		   TextView item_name = (TextView)view.findViewById(R.id.textview_item_name);
		   item_name.setText(orderDataItems[current].getOrder_item_name());
		   TextView item_qty = (TextView)view.findViewById(R.id.textview_item_qty);
		   item_qty.setText(orderDataItems[current].getOrder_item_qty());
		   newOrder_layout.addView(view);
		}
	}
}
