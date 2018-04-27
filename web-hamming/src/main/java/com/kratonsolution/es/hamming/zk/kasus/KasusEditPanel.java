package com.kratonsolution.es.hamming.zk.kasus;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Label;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Vlayout;

import com.kratonsolution.es.Springs;
import com.kratonsolution.es.hamming.application.KasusService;
import com.kratonsolution.es.hamming.model.Kasus;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class KasusEditPanel extends Vlayout {
    
    private Label error = new Label();
    
    private Combobox fitur1 = new Combobox();
    
    private Combobox fitur2 = new Combobox();
    
    private Combobox fitur3 = new Combobox();
    
    private Combobox fitur4 = new Combobox();
    
    private Combobox fitur5 = new Combobox();
    
    private Combobox fitur6 = new Combobox();
    
    private Combobox fitur7 = new Combobox();
    
    private Toolbarbutton back = new Toolbarbutton("Batal");
    
    private Toolbarbutton submit = new Toolbarbutton("Simpan");
    
    private Vlayout layout1 = new Vlayout();
    
    private Tabbox tabbox = new Tabbox();
    
    private Vlayout layout2 = new Vlayout();
    
    private Toolbar toolbar = new Toolbar();
    
    public KasusEditPanel(@NonNull Component parent, @NonNull Kasus kasus) {
        
        setVflex("1");
        setHflex("1");
        
        tabbox.setHflex("1");
        tabbox.appendChild(new Tabs());
        tabbox.appendChild(new Tabpanels());
        tabbox.getTabs().appendChild(new Tab("Fitur"));
        tabbox.getTabs().appendChild(new Tab("Solusi"));
        tabbox.getTabpanels().appendChild(new Tabpanel());
        tabbox.getTabpanels().appendChild(new Tabpanel());
        
        toolbar.setHflex("1");
        toolbar.appendChild(back);
        toolbar.appendChild(submit);
        
        layout1.setHflex("1");
        layout1.setVflex("1");
        
        layout2.setHflex("1");
        layout2.setVflex("1");
        
        appendChild(toolbar);
        appendChild(tabbox);
        
        back.setIconSclass("z-icon-close");
        back.addEventListener(Events.ON_CLICK, e -> {
            
            parent.removeChild(KasusEditPanel.this);
            parent.appendChild(new KasusGrid(parent));
        });
        
        submit.setIconSclass("z-icon-save");
        submit.addEventListener(Events.ON_CLICK, ev -> {
            
            try {
                
                
                Springs.get(KasusService.class).create(kasus);
                
                parent.removeChild(KasusEditPanel.this);
                parent.appendChild(new KasusGrid(parent));
                
            } catch (Exception e) {
                
                e.printStackTrace();
            }
        });
        
        initFitur(kasus);
        initSolution(kasus);
    }
    
    private void initFitur(@NonNull Kasus kasus) {
        
        error.setStyle("color:red;");
        error.setHflex("1");
        
        fitur1.setHflex("1");
        fitur1.appendChild(createItem(" < 120/80 mm hg", "100"));
        fitur1.appendChild(createItem(" 120-129/80-89 mm hg", "010"));
        fitur1.appendChild(createItem(" > 140/90 mm hg", "001"));
        setSelected(fitur1, kasus.getFitur1());
        
        fitur2.setHflex("1");
        fitur2.appendChild(createItem(" < 100 mg/dl", "100"));
        fitur2.appendChild(createItem(" 100-200 mg/dl", "010"));
        fitur2.appendChild(createItem(" > 200 mg/dl", "001"));
        setSelected(fitur2, kasus.getFitur2());
        
        fitur3.setHflex("1");
        fitur3.appendChild(createItem(" < 200 mg/dl", "100"));
        fitur3.appendChild(createItem(" 200-239 mg/dl", "010"));
        fitur3.appendChild(createItem(" > 240 mg/dl", "001"));
        setSelected(fitur3, kasus.getFitur3());
        
        fitur4.setHflex("1");
        fitur4.appendChild(createItem("Tidak Merokok", "100"));
        fitur4.appendChild(createItem("Jarang Merokok", "010"));
        fitur4.appendChild(createItem("Pecandu Merokok", "001"));
        setSelected(fitur4, kasus.getFitur4());
        
        fitur5.setHflex("1");
        fitur5.appendChild(createItem("Olah raga teratur", "100"));
        fitur5.appendChild(createItem("Jarang Olah raga", "010"));
        fitur5.appendChild(createItem("Tidak Olah raga", "001"));
        setSelected(fitur5, kasus.getFitur5());
        
        fitur6.setHflex("1");
        fitur6.appendChild(createItem("Ideal", "100"));
        fitur6.appendChild(createItem("Overweight", "010"));
        fitur6.appendChild(createItem("Obesitas", "001"));
        setSelected(fitur6, kasus.getFitur6());
        
        fitur7.setHflex("1");
        fitur7.appendChild(createItem("Tidak ada", "100"));
        fitur7.appendChild(createItem("Tidak yakin", "010"));
        fitur7.appendChild(createItem("Ada", "001"));
        setSelected(fitur7, kasus.getFitur7());
        
        layout1.appendChild(new Label("Tekanan Darah ?"));
        layout1.appendChild(fitur1);
        layout1.appendChild(new Label("Gula Darah ?"));
        layout1.appendChild(fitur2);
        layout1.appendChild(new Label("Kolestrol ?"));
        layout1.appendChild(fitur3);
        layout1.appendChild(new Label("Merokok ?"));
        layout1.appendChild(fitur4);
        layout1.appendChild(new Label("Aktifitas Fisik ?"));
        layout1.appendChild(fitur5);
        layout1.appendChild(new Label("Berat Badan ?"));
        layout1.appendChild(fitur6);
        layout1.appendChild(new Label("Riwayat Stroke dalam keluarga ?"));
        layout1.appendChild(fitur7);
        
        tabbox.getTabpanels().getChildren().get(0).appendChild(layout1);
    }
    
    private void initSolution(@NonNull Kasus kasus) {
        
        kasus.getSolutions().forEach(sol -> {
            
            Checkbox checkbox = new Checkbox(sol.getGejala()+" - "+sol.getJenis()+" - "+sol.getDescription());
            checkbox.setValue(sol.getId());
            checkbox.setChecked(sol.isSelected());
            
            layout2.appendChild(checkbox);
        });
        
        tabbox.getTabpanels().getChildren().get(1).appendChild(layout2);
    }
    
    private Comboitem createItem(String label, String value) {
        
        Comboitem item = new Comboitem(label);
        item.setValue(value);
        
        return item;
    }
    
    private void setSelected(Combobox combobox, @NonNull String selected) {
        
        combobox.getItems().forEach(item -> {
            
            if(item.getValue().equals(selected)) {
                combobox.setSelectedItem(item);
            }
        });
    }
}
