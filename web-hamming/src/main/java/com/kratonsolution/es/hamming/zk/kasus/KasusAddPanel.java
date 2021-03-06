package com.kratonsolution.es.hamming.zk.kasus;

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
import com.kratonsolution.es.hamming.application.SolutionService;
import com.kratonsolution.es.hamming.model.Kasus;
import com.kratonsolution.es.hamming.model.KasusSolusi;
import com.kratonsolution.es.hamming.model.RiskType;
import com.kratonsolution.es.hamming.model.Solution;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Slf4j
public class KasusAddPanel extends Vlayout {
    
    private Label error = new Label();
    
    private Combobox fitur1 = new Combobox();
    
    private Combobox fitur2 = new Combobox();
    
    private Combobox fitur3 = new Combobox();
    
    private Combobox fitur4 = new Combobox();
    
    private Combobox fitur5 = new Combobox();
    
    private Combobox fitur6 = new Combobox();
    
    private Combobox fitur7 = new Combobox();
    
    private Combobox type = new Combobox();
    
    private Toolbarbutton submit = new Toolbarbutton("Simpan");

    private Vlayout layout1 = new Vlayout();
    
    private Tabbox tabbox = new Tabbox();
    
    private Vlayout layout2 = new Vlayout();
    
    private Toolbar toolbar = new Toolbar();
    
    public KasusAddPanel(@NonNull Component parent) {
        
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
        toolbar.appendChild(submit);
        
        layout1.setHflex("1");
        layout1.setVflex("1");
        
        layout2.setHflex("1");
        layout2.setVflex("1");
        
        appendChild(toolbar);
        appendChild(tabbox);
        
        submit.setIconSclass("z-icon-save");
        submit.addEventListener(Events.ON_CLICK, ev -> {
            
            try {

                Kasus kasus = new Kasus(fitur1.getSelectedItem().getValue(), 
                                    fitur2.getSelectedItem().getValue(), 
                                    fitur3.getSelectedItem().getValue(), 
                                    fitur4.getSelectedItem().getValue(), 
                                    fitur5.getSelectedItem().getValue(), 
                                    fitur6.getSelectedItem().getValue(), 
                                    fitur7.getSelectedItem().getValue(), 
                                    type.getSelectedItem().getValue());

                log.info("kasus..{}", kasus);
                
                layout2.getChildren().forEach(com -> {
                    
                    Checkbox check = (Checkbox)com;
                    
                    Optional<Solution> opt = Springs.get(SolutionService.class).getById(check.getValue());
                    if(opt.isPresent()) {
                        
                        KasusSolusi solusi = new KasusSolusi(kasus, opt.get().getId(), opt.get().getDescription(), check.isChecked());
                        kasus.getSolutions().add(solusi);
                    }

                });
                
                Springs.get(KasusService.class).create(kasus);
                
                KasusAddPanel.this.getParent();
                
                parent.removeChild(KasusAddPanel.this);
                parent.appendChild(new KasusGrid(parent));
                
            } catch (Exception e) {
                
                e.printStackTrace();
            }
        });
        
        initFitur();
        initSolution();
    }
    
    private void initFitur() {
        
        error.setStyle("color:red;");
        error.setHflex("1");

        for(RiskType risk:RiskType.values()) {
            type.appendChild(createItem(risk.name(), risk));
        }
        type.setWidth("70%");
        type.setSelectedIndex(0);
        
        fitur1.setWidth("70%");
        fitur1.appendChild(createItem(" < 120/80 mm hg", "100"));
        fitur1.appendChild(createItem(" 120-129/80-89 mm hg", "010"));
        fitur1.appendChild(createItem(" > 140/90 mm hg", "001"));
        fitur1.setSelectedIndex(0);
        
        fitur2.setWidth("70%");
        fitur2.appendChild(createItem(" < 100 mg/dl", "100"));
        fitur2.appendChild(createItem(" 100-200 mg/dl", "010"));
        fitur2.appendChild(createItem(" > 200 mg/dl", "001"));
        fitur2.setSelectedIndex(0);
        
        fitur3.setWidth("70%");
        fitur3.appendChild(createItem(" < 200 mg/dl", "100"));
        fitur3.appendChild(createItem(" 200-239 mg/dl", "010"));
        fitur3.appendChild(createItem(" > 240 mg/dl", "001"));
        fitur3.setSelectedIndex(0);
        
        fitur4.setWidth("70%");
        fitur4.appendChild(createItem("Tidak Merokok", "100"));
        fitur4.appendChild(createItem("Jarang Merokok", "010"));
        fitur4.appendChild(createItem("Pecandu Merokok", "001"));
        fitur4.setSelectedIndex(0);
        
        fitur5.setWidth("70%");
        fitur5.appendChild(createItem("Olah raga teratur", "100"));
        fitur5.appendChild(createItem("Jarang Olah raga", "010"));
        fitur5.appendChild(createItem("Tidak Olah raga", "001"));
        fitur5.setSelectedIndex(0);
        
        fitur6.setWidth("70%");
        fitur6.appendChild(createItem("Ideal", "100"));
        fitur6.appendChild(createItem("Overweight", "010"));
        fitur6.appendChild(createItem("Obesitas", "001"));
        fitur6.setSelectedIndex(0);
        
        fitur7.setWidth("70%");
        fitur7.appendChild(createItem("Tidak ada", "100"));
        fitur7.appendChild(createItem("Tidak yakin", "010"));
        fitur7.appendChild(createItem("Ada", "001"));
        fitur7.setSelectedIndex(0);
        
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
    
    private void initSolution() {
        
        Springs.get(SolutionService.class).getAllSolutiones().forEach(sol -> {
            
            Checkbox checkbox = new Checkbox(sol.getDescription());
            checkbox.setValue(sol.getId());
            
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
}
