package com.invsol.getfoody.models;

public class ModelFacade {
	public static final String TAG = "Model Facade";
	/**
	 * Remote Model Reference to handle Network data and function calls
	 */
	private RemoteModel remoteModel;
	private ConnectionModel connModel;
	private LocalModel localModel;
	
	private RestaurantModel resModel;
	// ---------------------------------------------------------------------------------

	/**
	 * Constructor
	 */
	public ModelFacade() {
		// Initializing Remote Model
		remoteModel = new RemoteModel();
		connModel = new ConnectionModel();
		localModel = new LocalModel();
		resModel = new RestaurantModel();
	}

	// ---------------------------------------------------------------------------------

	/**
	 * Returns Remote Model Reference
	 * 
	 * @return RemoteModel
	 */
	public RemoteModel getRemoteModel() {
		return remoteModel;
	}

	public ConnectionModel getConnModel() {
		return connModel;
	}

	public LocalModel getLocalModel() {
		return localModel;
	}

	public RestaurantModel getResModel() {
		return resModel;
	}
	
	
	// ---------------------------------------------------------------------------------

}