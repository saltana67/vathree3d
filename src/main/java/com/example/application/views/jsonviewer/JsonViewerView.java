package com.example.application.views.jsonviewer;

import com.ironapi.vaadin.jsonviewer.JsonViewer;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
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

	public JsonViewerView() {
		setMargin(true);
		
		JsonViewer jsonViewer = new JsonViewer();
		add(jsonViewer);
		setHorizontalComponentAlignment(Alignment.CENTER,jsonViewer);
		
	}
}
