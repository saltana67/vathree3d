package com.ironapi.vaadin.threejs;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;

@JsModule("./three-test.js")
@NpmPackage(value = "three", version = "0.172.0")
@Tag("canvas")
public class Three extends Component {

    public Three() {
        getElement().executeJs("window.initThree($0)", this);
    }
}
