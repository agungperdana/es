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

import com.kratonsolution.es.Springs;
import com.kratonsolution.es.security.application.UserService;
import com.kratonsolution.es.security.model.User;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class UserEditPanel extends Panel {
    
    private Panelchildren panelchildren = new Panelchildren();
    
    private Label error = new Label();
    
    private Textbox username = new Textbox();
    
    private Textbox password = new Textbox();
    
    private Checkbox checkbox = new Checkbox("Aktif");
    
    private Button submit = new Button("Simpan");

    private Vlayout layout = new Vlayout();
    
    public UserEditPanel(@NonNull User user, @NonNull Component parent) {
        
        setVflex("1");
        setHflex("1");
        
        error.setStyle("color:red;");
        username.setPlaceholder("Masukan nama pengguna");
        username.setValue(user.getUsername());
        password.setPlaceholder("Masukan kata sandi");
        checkbox.setChecked(user.isEnabled());
        
        layout.setHflex("1");
        layout.setVflex("1");
        layout.appendChild(error);
        layout.appendChild(username);
        layout.appendChild(password);
        layout.appendChild(checkbox);
        layout.appendChild(submit);
        
        panelchildren.appendChild(layout);
        
        submit.addEventListener(Events.ON_CLICK, ev -> {
            
            try {
                
                user.setEnabled(checkbox.isChecked());
                Springs.get(UserService.class).update(user);
                
                parent.removeChild(UserEditPanel.this);
                parent.appendChild(new UserGrid(parent));
                
            } catch (Exception e) {
                
                error.setValue(e.getMessage());
            }
        });
        
        appendChild(panelchildren);
    }
}
