package com.kratonsolution.es.hamming.zk.user;

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
import com.kratonsolution.es.security.application.UserService;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class UserGrid extends Vlayout {
    
    private Grid grid = new Grid();
    
    private Toolbar toolbar = new Toolbar();
    
    private Toolbarbutton add = new Toolbarbutton("Tambah Data");
    
    public UserGrid(Component parent) {
        
        setHflex("1");
        setVflex("1");
        
        grid.setHflex("1");
        grid.setVflex("1");
        grid.appendChild(new Columns());
        grid.appendChild(new Rows());
        grid.getColumns().appendChild(new Column("", null, "75px"));
        grid.getColumns().appendChild(new Column("", null, "75px"));
        grid.getColumns().appendChild(new Column("Nama",null,null));
        grid.getColumns().appendChild(new Column("Status",null,null));
        
        UserService service = Springs.get(UserService.class);
        
        service.getAllUsers().forEach(user -> {

            Row row = new Row();
            
            A ubah = new A("Ubah");
            ubah.addEventListener(Events.ON_CLICK, e -> {

                parent.removeChild(UserGrid.this);
                parent.appendChild(new UserEditPanel(user, parent));
            });
            
            A hapus = new A("Hapus");
            hapus.addEventListener(Events.ON_CLICK, e -> {
                
                service.delete(user.getId());
                grid.getRows().removeChild(row);
            });

            row.appendChild(ubah);
            row.appendChild(hapus);
            row.appendChild(new Label(user.getUsername()));
            row.appendChild(new Label(user.isEnabled()?"Aktif":"Tidak Aktif"));
            
            grid.getRows().appendChild(row);
        });
        
        add.setIconSclass("z-icon-plus");
        add.addEventListener(Events.ON_CLICK, e -> {
            
            parent.removeChild(UserGrid.this);
            parent.appendChild(new UserAddPanel(parent));
        });
        
        toolbar.appendChild(add);
        toolbar.setHflex("1");
        
        appendChild(toolbar);
        appendChild(grid);
    }
}
