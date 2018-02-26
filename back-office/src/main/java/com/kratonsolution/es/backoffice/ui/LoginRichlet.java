package com.kratonsolution.es.backoffice.ui;

import org.zkoss.zk.ui.GenericRichlet;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vlayout;
import org.zkoss.zul.Window;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class LoginRichlet extends GenericRichlet {

    @Override
    public void service(Page page) throws Exception {
        
        Window window = new Window();
        window.setWidth("450px");
        window.setHeight("265px");
        window.setClosable(false);
        window.setMinimizable(false);
        window.setMaximizable(false);
        window.setSizable(false);
        window.setPage(page);
        window.setTitle("Please Login");
        
        Textbox username = new Textbox();
        username.setPlaceholder("Type your username");
        username.setHflex("1");
        username.setHeight("45px");
        
        Textbox passaword = new Textbox();
        passaword.setType("password");
        passaword.setPlaceholder("Type your password");
        passaword.setHflex("1");
        passaword.setHeight("45px");
        
        Button submit = new Button("Sing In");
        submit.setHflex("1");
        submit.setHeight("45px");
        submit.setIconSclass("z-icon-sign-in");
        submit.addEventListener(Events.ON_CLICK, evt ->{
            
            
        });
        
        Vlayout form = new Vlayout();
        form.setHflex("1");
        form.setVflex("1");
        form.appendChild(username);
        form.appendChild(passaword);
        form.appendChild(submit);
        
        window.appendChild(form);
        
        window.doModal();
    }
}
