package com.augmentum.client.uibind;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class LoginForm extends Composite{
    
    interface MyBinder extends UiBinder<Widget, LoginForm>{}
    
    private static MyBinder uiBinder = GWT.create(MyBinder.class);
    
    @UiField(provided = true) TextBox email;
    
    public LoginForm() {
        email = new TextBox();
        email.getElement().setAttribute("placeholder", "Please input email address");
        initWidget(uiBinder.createAndBindUi(this));
    }
}
