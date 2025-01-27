package com.ironapi.vaadin.jsonviewer;

import java.util.concurrent.atomic.AtomicInteger;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;

@JsModule("./json-viewer.js")
@CssImport("./json-viewer.css")
@Tag("pre")
public class JsonViewer extends Component {

	private static AtomicInteger nextIndex = new AtomicInteger(1);
	
	private static String getNextID() {
		return "json-viewer-" + nextIndex.getAndIncrement();
	}

	String elementId = null;
	String jsonString = "";
	
	public JsonViewer() {
		//elementId = getNextID();
		//setId(elementId);
		getElement().executeJs("window.initJsonViewer($0)", this);
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
		getElement().executeJs("window.JsonViewerSetString($0,$1)", this, this.jsonString);
	}
}