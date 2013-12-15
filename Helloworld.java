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

		// Add login button in the login page
		Button loginBtn = new Button("Login");
		RootPanel.get().add(loginBtn);
	}

	private void setUpGui() {
		
	}
	
	private void startHistroyHandling() {
		
	}
	
	private void setUpEventHandling() {
		
	}
}
