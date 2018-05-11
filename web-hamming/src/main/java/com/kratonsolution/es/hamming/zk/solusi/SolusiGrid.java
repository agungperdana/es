package com.kratonsolution.es.hamming.zk.solusi;

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
import com.kratonsolution.es.hamming.application.SolutionService;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class SolusiGrid extends Vlayout {
    
    private Grid grid = new Grid();
    
    private Toolbar toolbar = new Toolbar();
    
    private Toolbarbutton add = new Toolbarbutton("Tambah Data");
    
    public SolusiGrid(Component parent) {
        
        setHflex("1");
        setVflex("1");
        
        grid.setHflex("1");
        grid.setVflex("1");
        grid.appendChild(new Columns());
        grid.appendChild(new Rows());
        grid.getColumns().appendChild(new Column("", null, "75px"));
        grid.getColumns().appendChild(new Column("", null, "75px"));
        grid.getColumns().appendChild(new Column("Solusi",null,null));
        
        SolutionService service = Springs.get(SolutionService.class);
        
        service.getAllSolutiones().forEach(solution -> {

            Row row = new Row();
            
            A ubah = new A("Ubah");
            ubah.addEventListener(Events.ON_CLICK, e -> {

                parent.removeChild(SolusiGrid.this);
                parent.appendChild(new SolusiEditPanel(solution, parent));
            });
            
            A hapus = new A("Hapus");
            hapus.addEventListener(Events.ON_CLICK, e -> {
                
                service.delete(solution.getId());
                grid.getRows().removeChild(row);
            });

            row.appendChild(ubah);
            row.appendChild(hapus);
            row.appendChild(new Label(solution.getDescription()));
            
            grid.getRows().appendChild(row);
        });
        
        add.setIconSclass("z-icon-plus");
        add.addEventListener(Events.ON_CLICK, e -> {
            
            parent.removeChild(SolusiGrid.this);
            parent.appendChild(new SolusiAddPanel(parent));
        });
        
        toolbar.appendChild(add);
        toolbar.setHflex("1");
        
        appendChild(toolbar);
        appendChild(grid);
    }
}
