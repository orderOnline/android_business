package com.invsol.getfoody.constants;

public class Constants {
	
	// Required URLs
		public static final String BASE_URL = "http://www.medoco.in/";
		public static final String URL_POST_REGISTER_REQUEST = "register/business.json";
		public static final String URL_POST_LOGIN_REQUEST = "login/business.json";
		public static final String URL_POST_VALIDATEOTP_REQUEST = "register/business/validateotp.json";
		public static final String URL_POST_PROFILE_REQUEST = "profile/business/";
		// ----------------------------------------------------------------------------------
		
		/**
		 * WhizAPI Details
		 */
		public static final String APP_KEY = "u58lckoymhdvgw5zkysli5t4";
		public static final String WHIZ_API_BASE_URL = "https://www.WhizAPI.com/api/v2/util/ui/in/";
		public static final String WHIZ_API_GET_STATES_URL = "indian-states-list";
		public static final String WHIZ_API_GET_CITIES_URL = "indian-city-by-state";
		public static final String WHIZ_JSON_STATE_ID = "stateid";
		public static final String WHIZ_JSON_APPKEY = "appkey";
		public static final String WHIZ_JSON_DATA = "Data";
		public static final String WHIZ_JSON_RESPONSE_CODE = "ResponseCode";
		// ----------------------------------------------------------------------------------
		
		/**
	     * Shared Preference Name
	     */
	    public static final String DATABASE_PREF_NAME = "offonbLoginPrefName";
	    public static final String TEXT_DATABASE_ACCESS_VALUE_DEFAULT = "DatabaseKeyDoesNotExist";
	    //--------------------------------------------------------------------
	    
	    /**
	     * GCM Requirements
	     */
	    public static final String GCM_SENDER_ID = "244460346317";
	  //--------------------------------------------------------------------
		
		// Response Handling Constants
		public static final int SUCCESSFUL_RESPONSE = 0;
		public static final int ERROR = 1;
		public static final int EXCEPTION = 2;
		// ----------------------------------------------------------------------------------
		
		public static final String JSON_POST_DATA = "jsonpostdata";
		public static final String JSON_PHONENUMBER = "phonenumber";
		public static final String JSON_PASSWORD = "password";
		public static final String JSON_GCM_KEY = "gcm_key";
		public static final String JSON_RESTAURANT_ID = "restaurant_id";
		public static final String JSON_RESPONSE = "response";
		public static final String JSON_RESULT = "result";
		public static final String JSON_OTPCODE = "otpcode";
		public static final String JSON_VALID_OTP_CODE = "valid otp";
		public static final String JSON_NAME = "name";
		public static final String JSON_EMAIL = "email";
		public static final String JSON_STARTTIME = "service_start_time";
		public static final String JSON_ENDTIME = "service_end_time";
		public static final String JSON_CLOSEDON = "closedon";
		public static final String JSON_ADDRESS = "address";
		public static final String JSON_CITY = "city";
		public static final String JSON_STATE = "state";
		public static final String JSON_ZIPCODE = "zipcode";
		public static final String JSON_CUISINES = "cuisines";
		// ----------------------------------------------------------------------------------
}
