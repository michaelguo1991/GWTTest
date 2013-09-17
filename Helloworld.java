package com.augmentum.client;

import com.augmentum.client.uibind.LoginForm;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Helloworld implements EntryPoint {
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
	    LoginForm login = new LoginForm();
		RootPanel.get().add(login);
	}

	private void setUpGui() {
		
	}
	
	private void startHistroyHandling() {
		
	}
	
	private void setUpEventHandling() {
		
	}
}
