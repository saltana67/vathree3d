package com.ironapi.vaadin.threejs;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;

@JsModule("./three-test2.js")
@NpmPackage(value = "three", version = "0.172.0")
@Tag("canvas")
public class Three2 extends Component {

    public Three2() {
        getElement().executeJs("window.initThree2($0)", this);
    }
}
