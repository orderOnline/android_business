package com.invsol.getfoody.adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.invsol.getfoody.R;
import com.invsol.getfoody.dataobjects.ChatMessage;

public class ChatAdapter extends ArrayAdapter<ChatMessage>{

	Context context;
	int layoutResourceId;
	ArrayList<ChatMessage> chatItems = null;
	ViewHolder holder = null;

	public ChatAdapter(Context context, int layoutResourceId,
			ArrayList<ChatMessage> objects) {
		super(context, layoutResourceId, objects);
		this.context = context;
		this.layoutResourceId = layoutResourceId;
		this.chatItems = objects;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		ChatMessage item = chatItems.get(position);
		if (convertView == null) {
			holder = new ViewHolder();
			LayoutInflater layout_inflator = ((Activity) context)
					.getLayoutInflater();
			if( item.getOwner_type().equals("CONSUMER") ){
				convertView = layout_inflator.inflate(R.layout.chatlist_rowlayout_even, null);
				holder.dataCell_chatmsg = (TextView) convertView.findViewById(R.id.textview_chat_even);
				convertView.setTag(holder);
			}else if(item.getOwner_type().equals("RESTAURANT") ){
				convertView = layout_inflator.inflate(R.layout.chatlist_rowlayout_odd, null);
				holder.dataCell_chatmsg = (TextView) convertView.findViewById(R.id.textview_chat_odd);
				convertView.setTag(holder);
			}
		} else {
			holder = (ViewHolder) convertView.getTag();
			
		}
		holder.dataCell_chatmsg.setText(item.getMessage());
		return convertView;
	}

	/**
	 * A class defining the view holder
	 */
	static class ViewHolder {
		private TextView dataCell_chatmsg;
	}
}
