package com.kratonsolution.es.hamming.zk.solusi;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vlayout;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.kratonsolution.es.Springs;
import com.kratonsolution.es.hamming.application.SolutionService;
import com.kratonsolution.es.hamming.model.Solution;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class SolusiAddPanel extends Vlayout {
    
    private Label error = new Label();
    
    private Textbox gejala = new Textbox();
    
    private Textbox jenis = new Textbox();
    
    private Textbox note = new Textbox();
    
    private Button submit = new Button("Simpan");
    
    public SolusiAddPanel(@NonNull Component parent) {
        
        setVflex("1");
        setHflex("1");
        
        error.setStyle("color:red;");
        error.setHflex("1");
        
        gejala.setPlaceholder("Masukan Gejala Penyakit");
        gejala.setHflex("1");
        
        jenis.setPlaceholder("Masukan Jenis Penyakit");
        jenis.setHflex("1");

        note.setPlaceholder("Masukan solusi");
        note.setHflex("1");
        
        appendChild(error);
        appendChild(gejala);
        appendChild(jenis);
        appendChild(note);
        appendChild(submit);
        
        submit.setHflex("1");
        submit.addEventListener(Events.ON_CLICK, ev -> {
            
            try {
                
                Preconditions.checkState(!Strings.isNullOrEmpty(gejala.getValue()), "Gejala penyakit tidak boleh kosong");
                Preconditions.checkState(!Strings.isNullOrEmpty(jenis.getValue()), "Jenis penyakit tidak boleh kosong");
                Preconditions.checkState(!Strings.isNullOrEmpty(note.getValue()), "Solusi penyakit tidak boleh kosong");
                
                Springs.get(SolutionService.class).create(new Solution(gejala.getValue(), jenis.getValue(), note.getValue()));
                
                SolusiAddPanel.this.getParent();
                
                parent.removeChild(SolusiAddPanel.this);
                parent.appendChild(new SolusiGrid(parent));
                
            } catch (Exception e) {
                
                error.setValue(e.getMessage());
            }
        });
    }
}
