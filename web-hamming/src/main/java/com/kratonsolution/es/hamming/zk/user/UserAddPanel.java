package com.kratonsolution.es.hamming.zk.user;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Panelchildren;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vlayout;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.kratonsolution.es.Springs;
import com.kratonsolution.es.security.application.UserService;
import com.kratonsolution.es.security.model.User;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class UserAddPanel extends Panel {
    
    private Panelchildren panelchildren = new Panelchildren();
    
    private Label error = new Label();
    
    private Textbox username = new Textbox();
    
    private Textbox password = new Textbox();
    
    private Checkbox checkbox = new Checkbox("Aktif");
    
    private Button submit = new Button("Simpan");

    private Vlayout layout = new Vlayout();
    
    public UserAddPanel(@NonNull Component parent) {
        
        setVflex("1");
        setHflex("1");
        
        error.setStyle("color:red;");
        error.setHflex("1");
        
        username.setPlaceholder("Masukan nama pengguna");
        username.setHflex("1");
        
        password.setPlaceholder("Masukan kata sandi");
        password.setHflex("1");
        
        layout.setHflex("1");
        layout.setVflex("1");
        layout.appendChild(error);
        layout.appendChild(username);
        layout.appendChild(password);
        layout.appendChild(checkbox);
        layout.appendChild(submit);
        
        panelchildren.appendChild(layout);
        
        submit.setHflex("1");
        submit.addEventListener(Events.ON_CLICK, ev -> {
            
            try {
                
                Preconditions.checkState(!Strings.isNullOrEmpty(username.getValue()), "Nama tidak boleh kosong");
                Preconditions.checkState(!Strings.isNullOrEmpty(password.getValue()), "Password tidak boleh kosong");
                
                User user = new User(username.getValue(), password.getValue(), checkbox.isChecked());
                Springs.get(UserService.class).create(user);
                
                UserAddPanel.this.getParent();
                
                parent.removeChild(UserAddPanel.this);
                parent.appendChild(new UserGrid(parent));
                
            } catch (Exception e) {
                
                error.setValue(e.getMessage());
            }
        });
        
        appendChild(panelchildren);
    }
}
