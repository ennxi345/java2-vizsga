package hu.mik.java2.exam.vaadin;

import org.springframework.beans.factory.annotation.Autowired;


import com.vaadin.event.ShortcutAction;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;



@SpringUI(path = "")
public class LoginUI extends UI{

	private VerticalLayout pageLayout;
	@Autowired
	//az eddigi név nem irta le, hogy mi ez.
	private UserLoginLayout studentLoginLayout;
	
	
	@Override
	protected void init(VaadinRequest request) {
		Page.getCurrent().setTitle("Bejelentkezés");
		this.pageLayout = new VerticalLayout();
		this.pageLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		this.pageLayout.setSizeFull();
		this.setContent(this.pageLayout);	
		
		//mivel komponens, ezt is lehet autowireölni
//		this.diakloglay = new StudentLoginLayout();
		
		this.pageLayout.addComponent(studentLoginLayout);
	}

	
	
}
