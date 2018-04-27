package com.kratonsolution.es.hamming.zk.fitur;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Panelchildren;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vlayout;

import com.kratonsolution.es.Springs;
import com.kratonsolution.es.hamming.application.FiturService;
import com.kratonsolution.es.hamming.model.Fitur;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class FiturEditPanel extends Panel {
    
    private Panelchildren panelchildren = new Panelchildren();
    
    private Label error = new Label();
    
    private Textbox name = new Textbox();
    
    private Textbox note = new Textbox();
        
    private Button submit = new Button("Simpan");

    private Vlayout layout = new Vlayout();
    
    public FiturEditPanel(@NonNull Fitur fitur, @NonNull Component parent) {
        
        setVflex("1");
        setHflex("1");
        
        error.setStyle("color:red;");
        name.setValue(fitur.getName());
        note.setValue(fitur.getNote());
        
        layout.setHflex("1");
        layout.setVflex("1");
        layout.appendChild(error);
        layout.appendChild(name);
        layout.appendChild(note);
        layout.appendChild(submit);
        
        panelchildren.appendChild(layout);
        
        submit.addEventListener(Events.ON_CLICK, ev -> {
            
            try {
                
                fitur.setNote(note.getValue());
                Springs.get(FiturService.class).update(fitur);
                
                parent.removeChild(FiturEditPanel.this);
                parent.appendChild(new FiturGrid(parent));
                
            } catch (Exception e) {
                
                error.setValue(e.getMessage());
            }
        });
        
        appendChild(panelchildren);
    }
}
