package hu.mik.java2.exam.vaadin;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI(path = ("/studentUI"))
public class StudentUI extends UI {

	@Override
	protected void init(VaadinRequest request) {
		Label studentLabel = new Label("Tanulói felület");
		
		VerticalLayout verticallayout = new VerticalLayout();
		verticallayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		verticallayout.addComponent(studentLabel);
		
		setContent(verticallayout);
	}

}
