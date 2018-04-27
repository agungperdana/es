package com.kratonsolution.es.hamming.zk.fitur;

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
import com.kratonsolution.es.hamming.application.FiturService;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class FiturGrid extends Vlayout {
    
    private Grid grid = new Grid();
    
    private Toolbar toolbar = new Toolbar();
    
    private Toolbarbutton add = new Toolbarbutton("Tambah Data");
    
    public FiturGrid(Component parent) {
        
        setHflex("1");
        setVflex("1");
        
        grid.setHflex("1");
        grid.setVflex("1");
        grid.appendChild(new Columns());
        grid.appendChild(new Rows());
        grid.getColumns().appendChild(new Column("", null, "75px"));
        grid.getColumns().appendChild(new Column("", null, "75px"));
        grid.getColumns().appendChild(new Column("Nama",null,null));
        grid.getColumns().appendChild(new Column("Keterangan",null,null));
        
        FiturService service = Springs.get(FiturService.class);
        
        service.getAllFitures().forEach(fitur -> {

            Row row = new Row();
            
            A ubah = new A("Ubah");
            ubah.addEventListener(Events.ON_CLICK, e -> {

                parent.removeChild(FiturGrid.this);
                parent.appendChild(new FiturEditPanel(fitur, parent));
            });
            
            A hapus = new A("Hapus");
            hapus.addEventListener(Events.ON_CLICK, e -> {
                
                service.delete(fitur.getId());
                grid.getRows().removeChild(row);
            });

            row.appendChild(ubah);
            row.appendChild(hapus);
            row.appendChild(new Label(fitur.getName()));
            row.appendChild(new Label(fitur.getNote()));
            
            grid.getRows().appendChild(row);
        });
        
        add.setIconSclass("z-icon-plus");
        add.addEventListener(Events.ON_CLICK, e -> {
            
            parent.removeChild(FiturGrid.this);
            parent.appendChild(new FiturAddPanel(parent));
        });
        
        toolbar.appendChild(add);
        toolbar.setHflex("1");
        
        appendChild(toolbar);
        appendChild(grid);
    }
}
