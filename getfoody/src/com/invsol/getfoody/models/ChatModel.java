package com.invsol.getfoody.models;

import org.json.JSONException;
import org.json.JSONObject;

import com.invsol.getfoody.constants.Constants;
import com.invsol.getfoody.dataobjects.ChatItem;

import android.util.SparseArray;

public class ChatModel {

	private JSONObject newChatMessage;
	private SparseArray<ChatItem> chatItems;
	
	public ChatModel(){
		chatItems = new SparseArray<ChatItem>();
	}
	
	public void addOrderChat( JSONObject chatJson ){
		try {
			newChatMessage = chatJson;
			int order_id = chatJson.getInt(Constants.JSON_CHAT_ORDER_ID);
			int owner_id = chatJson.getInt(Constants.JSON_CHAT_OWNER_ID);

			ChatItem item = null;
			if( chatItems.get(order_id) != null){
				item = chatItems.get(order_id);
			}else{
				item = new ChatItem(order_id, owner_id);
				chatItems.put(order_id, item);
			}
			item.addChatMessage(chatJson.getString(Constants.JSON_CHAT_MESSAGE), chatJson.getString(Constants.JSON_CHAT_OWNER_TYPE));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public SparseArray<ChatItem> getChatItems() {
		return chatItems;
	}

	public JSONObject getNewChatMessage() {
		return newChatMessage;
	}
	
	
}
