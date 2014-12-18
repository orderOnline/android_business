package com.invsol.getfoody.adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.Context;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.invsol.getfoody.R;
import com.invsol.getfoody.constants.Constants;
import com.invsol.getfoody.dataobjects.NewOrderItems;

public class NewOrdersAdapter extends ArrayAdapter<NewOrderItems> {

	Context context;
	int layoutResourceId;
	//NewOrderItems[] newOrderItems = null;
	ArrayList<NewOrderItems> newOrderItems = null;
	ViewHolder holder = null;
	private HashMap<TextView,CountDownTimer> counters;

	public NewOrdersAdapter(Context context, int layoutResourceId,
			ArrayList<NewOrderItems> objects) {
		super(context, layoutResourceId, objects);
		this.context = context;
		this.layoutResourceId = layoutResourceId;
		this.newOrderItems = objects;
		this.counters = new HashMap<TextView, CountDownTimer>();
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		//ViewHolder holder = null;

		if (convertView == null) {
			holder = new ViewHolder();
			LayoutInflater layout_inflator = ((Activity) context)
					.getLayoutInflater();
			convertView = layout_inflator.inflate(R.layout.item_order, null);
			holder.dataCell_offer_location = (TextView) convertView.findViewById(R.id.textview_offer_address);
			holder.dataCell_offer_phoneNumber = (TextView) convertView.findViewById(R.id.textview_offer_phonenumber);
			holder.dataCell_offer_status = (TextView) convertView.findViewById(R.id.textview_offer_acceptance_status);
			holder.dataCell_offer_timer = (TextView) convertView.findViewById(R.id.textview_offer_timer);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
			
		}
		
		CountDownTimer cdt = counters.get(holder.dataCell_offer_timer);
        if(cdt!=null)
        {
            cdt.cancel();
            cdt=null;
        }

		NewOrderItems item = newOrderItems.get(position);
		holder.dataCell_offer_location.setText(item.getCustomer_address());
		holder.dataCell_offer_phoneNumber.setText(""+item.getCustomer_phoneNumber());
		if( item.getOrder_status().equals(Constants.JSON_ORDER_STATUS_ACCEPTED) ){
			holder.dataCell_offer_status.setText(item.getOrder_status());
			holder.dataCell_offer_status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.order_status_accepted, 0, 0, 0);
			
			long millis = (item.getDeliveryTime() * 60L);
			long result = TimeUnit.SECONDS.toMillis(millis);
			cdt = new CountDownTimer(result, 1000) {
				public void onTick(long millisUntilFinished) {
					int sec  = (int)(millisUntilFinished/ 1000) % 60 ;
					int min  = (int)((millisUntilFinished/ (1000) / 60));
					holder.dataCell_offer_timer.setText(""+min+":"+sec);
				}

				public void onFinish() {
					
					holder.dataCell_offer_timer.setText("Done");
				}
			};
			counters.put(holder.dataCell_offer_timer, cdt);
	        cdt.start();
				
		}else if( item.getOrder_status().equals(Constants.JSON_ORDER_STATUS_DECLINED) ){
			holder.dataCell_offer_status.setText(item.getOrder_status());
			holder.dataCell_offer_status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.order_status_missed, 0, 0, 0);
		}		

		return convertView;
	}

	/**
	 * A class defining the view holder
	 */
	static class ViewHolder {
		private TextView dataCell_offer_location;
		private TextView dataCell_offer_phoneNumber;
		private TextView dataCell_offer_status;
		private TextView dataCell_offer_timer;
	}

	public ArrayList<NewOrderItems> getNewOrderItems() {
		return newOrderItems;
	}

	
}
