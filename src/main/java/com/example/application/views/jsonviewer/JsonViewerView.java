package com.example.application.views.jsonviewer;

import com.ironapi.vaadin.jsonviewer.JsonViewer;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Test json-viewer")
@Route("jsonviewer")
@Menu(order = 2) 
@AnonymousAllowed
public class JsonViewerView extends VerticalLayout {

	TabSheet tabSheet = null;
	TextArea ta = null;
	JsonViewer jsonViewer = null;
	
	public JsonViewerView() {
		setMargin(true);
		
		ta = new TextArea();
		ta.setSizeFull();
		
		
		jsonViewer = new JsonViewer();

		tabSheet = new TabSheet();
		tabSheet.setSizeFull();
		
		tabSheet.add("Text",ta);
		tabSheet.add("Json",jsonViewer);

		add(tabSheet);
		
		setHorizontalComponentAlignment(Alignment.CENTER,jsonViewer);
		
		ta.addValueChangeListener(event -> {
		    System.out.println("Value changed to: " + event.getValue());
		    jsonViewer.setJsonString(event.getValue());
		});
	}
}

