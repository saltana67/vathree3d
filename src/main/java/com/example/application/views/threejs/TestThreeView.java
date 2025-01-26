package com.example.application.views.threejs;

import com.ironapi.vaadin.threejs.Three;
import com.ironapi.vaadin.threejs.Three2;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Test Three.js")
@Route("three")
@Menu(order = 1, icon = LineAwesomeIconUrl.VR_CARDBOARD_SOLID)
@AnonymousAllowed
public class TestThreeView extends VerticalLayout {

    public TestThreeView() {

        setMargin(true);
        //setVerticalComponentAlignment(Alignment.END, name, sayHello);
        Three2 three2 = new Three2();
        add(three2);
        setHorizontalComponentAlignment(Alignment.CENTER, three2);

        for (int i = 0; i < 5; i++) {
            Three three = new Three();
            add(three);
            setHorizontalComponentAlignment(Alignment.CENTER, three);
        }
    }

}
