package com.invsol.getfoody.constants;

public class Constants {
	
	// Required URLs
		public static final String BASE_URL = "http://api.adspitcher.com/";
		public static final String URL_GET_BRANDS_REQUEST = "v1/brands.json";
		public static final String URL_GET_CATEGORIES_REQUEST = "v1/categories.json";
		public static final String URL_GET_ACCESSTOKEN_REQUEST = "oauth/token";
		// ----------------------------------------------------------------------------------
		
		//Oauth Details
		public static final String OAUTH_CLIENT_ID = "f321a4ba2008c3d7dec4dafe8a4f9f0b8dd631cea839434d514e278a2eb96b8b";
		public static final String OAUTH_CLIENT_SECRET = "f0834b7f5eca772ad848ee40b9ca71a7b11ad3d24fe44988feeca10d33eb4a48";
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
	    
	    //Google Spreadsheets Details
	    public static final String GOOGLE_ACCOUNT_USERNAME = "rachnakhokhar@gmail.com";
	    public static final String GOOGLE_ACCOUNT_PASSWORD = "rach18febprash";
	    public static final String SPREADSHEET_REGISTER_BUSINESSUSER = "datamanager_offonb";
	    public static final String SPREADSHEET_REGISTER_STORE_NAME = "Name";
	    public static final String SPREADSHEET_REGISTER_STORE_ADDRESS = "Address";
	    public static final String SPREADSHEET_REGISTER_STORE_COUNTRY = "Country";
	    public static final String SPREADSHEET_REGISTER_STORE_STATE = "State";
	    public static final String SPREADSHEET_REGISTER_STORE_CITY = "City";
	    public static final String SPREADSHEET_REGISTER_BUSINESSUSER_NAME = "Username";
	    public static final String SPREADSHEET_REGISTER_BUSINESSUSER_EMAIL = "Useremail";
	    public static final String SPREADSHEET_REGISTER_BUSINESSUSER_PHONENUMBER = "Userphonenumber";
	  //--------------------------------------------------------------------
		
		// Response Handling Constants
		public static final int SUCCESSFUL_RESPONSE = 0;
		public static final int ERROR = 1;
		public static final int EXCEPTION = 2;
		// ----------------------------------------------------------------------------------
		
		// Error Messages
		public static String ERROR_NETWORK_PROBLEM;
		public static String ERROR_READING_DATA_FROM_SERVER_PROBLEM;
		// ----------------------------------------------------------------------------------
		
		public static final String TEXT_NAME = "name";
		public static final String TEXT_ID = "id";
		public static final String TEXT_ACCESSTOKEN = "access_token";
		public static final String TEXT_TOKEN_TYPE = "token_type";
		public static final String TEXT_EXPIRES_IN = "expires_in";
		public static final String TEXT_GRANT_TYPE = "grant_type";
		public static final String TEXT_USERNAME = "username";
		public static final String TEXT_PASSWORD = "password";
		public static final String TEXT_CLIENT_ID = "client_id";
		public static final String TEXT_CLIENT_SECRET = "client_secret"; 
}
