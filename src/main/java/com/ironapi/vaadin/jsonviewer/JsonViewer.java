package com.ironapi.vaadin.jsonviewer;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;

@JsModule("./json-viewer.js")
@CssImport("./json-viewer.css")
@Tag("pre")
public class JsonViewer extends Component {

	public JsonViewer() {
		getElement().executeJs("window.initJsonViewer($0)", this);
	}

}
