package com.kratonsolution.es.hamming.zk;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.GenericRichlet;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vlayout;
import org.zkoss.zul.Window;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.kratonsolution.es.Springs;
import com.kratonsolution.es.security.application.UserService;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class Login extends GenericRichlet {
    
    @Override
    public void service(Page page) throws Exception {
        
        Textbox username = new Textbox();
        username.setHflex("1");
        username.setPlaceholder("Masukan nama user");
        username.setHeight("45px");
        
        Textbox password = new Textbox();
        password.setType("password");
        password.setHflex("1");
        password.setPlaceholder("Masukan kata sandi");
        password.setHeight("45px");
        
        Label error = new Label();
        error.setHflex("1");
        error.setStyle("color:red;font-weight:bolder;");
        
        Button submit = new Button("Login...");
        submit.setHflex("1");
        submit.setHeight("45px");
        submit.addEventListener(Events.ON_CLICK, e -> {
            
            try {
                
                Preconditions.checkState(!Strings.isNullOrEmpty(username.getValue()), "Nama / Kata sandi tidak cocok");
                Preconditions.checkState(!Strings.isNullOrEmpty(password.getValue()), "Nama / Kata sandi tidak cocok");

                UserService service = Springs.get(UserService.class);
                service.signIn(username.getValue(), password.getValue());
                
                Executions.sendRedirect("/backoffice/dashboard");
                
            } catch (Exception e2) {

                error.setValue(e2.getMessage());
            } 
        });
        
        Vlayout layout = new Vlayout();
        layout.appendChild(username);
        layout.appendChild(password);
        layout.appendChild(submit);
        layout.appendChild(error);
        layout.setVflex("1");
        layout.setHflex("1");
        
        Window window = new Window();
        window.setTitle("Login Pengguna");
        window.setWidth("400px");
        window.setHeight("275px");
        window.setPage(page);
        window.setMaximizable(false);
        window.setMinimizable(false);
        window.setSizable(false);
        window.setClosable(false);
        window.appendChild(layout);
        window.setPage(page);
        window.doModal();
    }
    
}
