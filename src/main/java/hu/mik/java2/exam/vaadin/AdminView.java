package hu.mik.java2.exam.vaadin;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.UI;


@SpringView(name = AdminView.ADMIN_UI)
public class AdminView extends HorizontalLayout implements View{

	protected static final String ADMIN_UI = "admin";
	
	@Override
	public void enter(ViewChangeEvent event) {
		HorizontalLayout Horizonmenubar = new HorizontalLayout();
		MenuBar barmenu = new MenuBar();
		Horizonmenubar.addComponent(barmenu);
		
		this.addComponent(Horizonmenubar);
		
	}

}
