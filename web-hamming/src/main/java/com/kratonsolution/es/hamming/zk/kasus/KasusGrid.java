package com.kratonsolution.es.hamming.zk.kasus;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.A;
import org.zkoss.zul.Column;
import org.zkoss.zul.Columns;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Vlayout;

import com.kratonsolution.es.Springs;
import com.kratonsolution.es.hamming.application.KasusService;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class KasusGrid extends Vlayout {
    
    private Grid grid = new Grid();
    
    private Toolbar toolbar = new Toolbar();
    
    private Toolbarbutton add = new Toolbarbutton("Tambah Data");
    
    public KasusGrid(Component parent) {
        
        setHflex("1");
        setVflex("1");
        
        grid.setHflex("1");
        grid.setVflex("1");
        grid.appendChild(new Columns());
        grid.appendChild(new Rows());
        grid.getColumns().appendChild(new Column("", null, "75px"));
        grid.getColumns().appendChild(new Column("", null, "75px"));
        grid.getColumns().appendChild(new Column("Binaries", null, null));
        grid.getColumns().appendChild(new Column("Fit 1", null, "70px"));
        grid.getColumns().appendChild(new Column("Fit 2", null, "70px"));
        grid.getColumns().appendChild(new Column("Fit 3", null, "70px"));
        grid.getColumns().appendChild(new Column("Fit 4", null, "70px"));
        grid.getColumns().appendChild(new Column("Fit 5", null, "70px"));
        grid.getColumns().appendChild(new Column("Fit 6", null, "70px"));
        grid.getColumns().appendChild(new Column("Fit 7", null, "70px"));
        grid.setSpan("2");
        
        KasusService service = Springs.get(KasusService.class);
        
        service.getAllKasuses().forEach(kasus -> {

            Row row = new Row();
            
            A ubah = new A("Ubah");
            ubah.addEventListener(Events.ON_CLICK, e -> {

                parent.removeChild(KasusGrid.this);
                parent.appendChild(new KasusEditPanel(parent, kasus));
            });
            
            A hapus = new A("Hapus");
            hapus.addEventListener(Events.ON_CLICK, e -> {
                
                service.delete(kasus.getId());
                grid.getRows().removeChild(row);
            });

            row.appendChild(ubah);
            row.appendChild(hapus);
            row.appendChild(new Label(kasus.binaries()));
            row.appendChild(new Label(kasus.getFitur1()));
            row.appendChild(new Label(kasus.getFitur2()));
            row.appendChild(new Label(kasus.getFitur3()));
            row.appendChild(new Label(kasus.getFitur4()));
            row.appendChild(new Label(kasus.getFitur5()));
            row.appendChild(new Label(kasus.getFitur6()));
            row.appendChild(new Label(kasus.getFitur7()));
            
            grid.getRows().appendChild(row);
        });
        
        add.setIconSclass("z-icon-plus");
        add.addEventListener(Events.ON_CLICK, e -> {
            
            parent.removeChild(KasusGrid.this);
            parent.appendChild(new KasusAddPanel(parent));
        });
        
        toolbar.appendChild(add);
        toolbar.setHflex("1");
        
        appendChild(toolbar);
        appendChild(grid);
    }
}
