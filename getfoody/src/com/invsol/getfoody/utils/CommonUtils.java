package com.invsol.getfoody.utils;

import java.util.Iterator;
import java.util.Set;

import android.os.Bundle;

public class CommonUtils {
	public static final String createPostdata( Bundle params ){
		Set<String> keySet = params.keySet();
		Iterator<String> keyIterator = keySet.iterator();
		String keyVal = null;
		String requestData = new String();
		while (keyIterator.hasNext()) {
			keyVal = keyIterator.next();
			requestData += keyVal;
			requestData += "=";
			requestData += params.getString(keyVal);
			if( keyIterator.hasNext() )
				requestData += "&";
		}
		return requestData;
	}
}
