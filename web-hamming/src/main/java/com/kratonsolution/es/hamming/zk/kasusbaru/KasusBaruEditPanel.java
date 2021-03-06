package com.kratonsolution.es.hamming.zk.kasusbaru;

import java.util.Optional;

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
import com.kratonsolution.es.hamming.model.KasusSolusi;
import com.kratonsolution.es.hamming.model.RiskType;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class KasusBaruEditPanel extends Vlayout {
    
    private Label error = new Label();
    
    private Combobox fitur1 = new Combobox();
    
    private Combobox fitur2 = new Combobox();
    
    private Combobox fitur3 = new Combobox();
    
    private Combobox fitur4 = new Combobox();
    
    private Combobox fitur5 = new Combobox();
    
    private Combobox fitur6 = new Combobox();
    
    private Combobox fitur7 = new Combobox();
    
    private Combobox type = new Combobox();
    
    private Toolbarbutton back = new Toolbarbutton("Batal");
    
    private Toolbarbutton submit = new Toolbarbutton("Simpan ke Basis Kasus");
    
    private Vlayout layout1 = new Vlayout();
    
    private Tabbox tabbox = new Tabbox();
    
    private Vlayout layout2 = new Vlayout();
    
    private Toolbar toolbar = new Toolbar();
    
    public KasusBaruEditPanel(@NonNull Component parent, @NonNull Kasus kasus) {
        
        setSpacing("3px");
        setWidth("100%");
        setHeight("100%");
        setStyle("overflow:auto");
        
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
            
            parent.removeChild(KasusBaruEditPanel.this);
            parent.appendChild(new KasusBaruGrid(parent));
        });
        
        submit.setIconSclass("z-icon-save");
        submit.addEventListener(Events.ON_CLICK, ev -> {
            
            try {
                
                Optional<Kasus> on = Springs.get(KasusService.class).getById(kasus.getId());
                if(on.isPresent()) {
                    
                    on.get().setType(type.getSelectedItem().getValue());
                    
                    layout2.getChildren().forEach(com -> {
                        
                        Checkbox box = (Checkbox)com;
                        Optional<KasusSolusi> opt = on.get().getSolutions().stream().filter(p -> 
                        p.getId().equals(box.getValue())).findFirst();
                        if(opt.isPresent()) {
                            opt.get().setSelected(box.isChecked());
                        }
                    });
                    
                    Springs.get(KasusService.class).acceptKasus(on.get());
                }
                
                parent.removeChild(KasusBaruEditPanel.this);
                parent.appendChild(new KasusBaruGrid(parent));
                
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
        
        for(RiskType risk:RiskType.values()) {
            type.appendChild(createItem(risk.name(), risk));
        }
        type.setWidth("70%");
        type.setSelectedIndex(0);
        setSelected(type, kasus.getType());
        
        fitur1.setReadonly(true);
        fitur1.setWidth("70%");
        fitur1.appendChild(createItem(" < 120/80 mm hg", "100"));
        fitur1.appendChild(createItem(" 120-129/80-89 mm hg", "010"));
        fitur1.appendChild(createItem(" > 140/90 mm hg", "001"));
        setSelected(fitur1, kasus.getFitur1());
        
        fitur2.setReadonly(true);
        fitur2.setWidth("70%");
        fitur2.appendChild(createItem(" < 100 mg/dl", "100"));
        fitur2.appendChild(createItem(" 100-200 mg/dl", "010"));
        fitur2.appendChild(createItem(" > 200 mg/dl", "001"));
        setSelected(fitur2, kasus.getFitur2());
        
        fitur3.setReadonly(true);
        fitur3.setWidth("70%");
        fitur3.appendChild(createItem(" < 200 mg/dl", "100"));
        fitur3.appendChild(createItem(" 200-239 mg/dl", "010"));
        fitur3.appendChild(createItem(" > 240 mg/dl", "001"));
        setSelected(fitur3, kasus.getFitur3());
        
        fitur4.setReadonly(true);
        fitur4.setWidth("70%");
        fitur4.appendChild(createItem("Tidak Merokok", "100"));
        fitur4.appendChild(createItem("Jarang Merokok", "010"));
        fitur4.appendChild(createItem("Pecandu Merokok", "001"));
        setSelected(fitur4, kasus.getFitur4());
        
        fitur5.setReadonly(true);
        fitur5.setWidth("70%");
        fitur5.appendChild(createItem("Olah raga teratur", "100"));
        fitur5.appendChild(createItem("Jarang Olah raga", "010"));
        fitur5.appendChild(createItem("Tidak Olah raga", "001"));
        setSelected(fitur5, kasus.getFitur5());
        
        fitur6.setReadonly(true);
        fitur6.setWidth("70%");
        fitur6.appendChild(createItem("Ideal", "100"));
        fitur6.appendChild(createItem("Overweight", "010"));
        fitur6.appendChild(createItem("Obesitas", "001"));
        setSelected(fitur6, kasus.getFitur6());
        
        fitur7.setReadonly(true);
        fitur7.setWidth("70%");
        fitur7.appendChild(createItem("Tidak ada", "100"));
        fitur7.appendChild(createItem("Tidak yakin", "010"));
        fitur7.appendChild(createItem("Ada", "001"));
        setSelected(fitur7, kasus.getFitur7());
        
        layout1.appendChild(new Label("Tingkat Resiko"));
        layout1.appendChild(type);
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
            
            Checkbox checkbox = new Checkbox(sol.getDescription());
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
    
    private Comboitem createItem(String label, RiskType type) {
        
        Comboitem item = new Comboitem(label);
        item.setValue(type);
        
        return item;
    }
    
    private void setSelected(Combobox combobox, @NonNull String selected) {
        
        combobox.getItems().forEach(item -> {
            
            if(item.getValue().equals(selected)) {
                combobox.setSelectedItem(item);
            }
        });
    }
    
    private void setSelected(Combobox combobox, @NonNull RiskType selected) {
        
        combobox.getItems().forEach(item -> {
            
            if(item.getValue().equals(selected)) {
                combobox.setSelectedItem(item);
            }
        });
    }
}
