package com.kratonsolution.es.hamming.zk.solusi;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vlayout;

import com.kratonsolution.es.Springs;
import com.kratonsolution.es.hamming.application.SolutionService;
import com.kratonsolution.es.hamming.model.Solution;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class SolusiEditPanel extends Vlayout {
    
    private Label error = new Label();
    
    private Textbox note = new Textbox();
    
    private Button submit = new Button("Simpan");
    
    public SolusiEditPanel(@NonNull Solution solution, @NonNull Component parent) {
        
        setVflex("1");
        setHflex("1");
        
        error.setStyle("color:red;");
        
        note.setValue(solution.getDescription());
        note.setHflex("1");
        
        appendChild(error);
        appendChild(note);
        appendChild(submit);
        
        submit.addEventListener(Events.ON_CLICK, ev -> {
            
            try {
                
                solution.setDescription(note.getValue());
                Springs.get(SolutionService.class).update(solution);
                
                parent.removeChild(SolusiEditPanel.this);
                parent.appendChild(new SolusiGrid(parent));
                
            } catch (Exception e) {
                
                error.setValue(e.getMessage());
            }
        });
    }
}
