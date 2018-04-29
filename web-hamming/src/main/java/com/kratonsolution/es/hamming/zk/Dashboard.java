package com.kratonsolution.es.hamming.zk;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.GenericRichlet;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menubar;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Menuseparator;
import org.zkoss.zul.Vlayout;
import org.zkoss.zul.Window;

import com.kratonsolution.es.hamming.zk.fitur.FiturWindow;
import com.kratonsolution.es.hamming.zk.kasus.KasusWindow;
import com.kratonsolution.es.hamming.zk.kasusbaru.KasusBaruWindow;
import com.kratonsolution.es.hamming.zk.solusi.SolusiWindow;
import com.kratonsolution.es.hamming.zk.user.UserWindow;

import lombok.Getter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class Dashboard extends GenericRichlet {

    @Override
    public void service(Page page) throws Exception {
        
        new Layout().setPage(page);
    }
    
    @Getter
    private class Layout extends Vlayout {

        Menubar menubar = new Menubar();
        
        Menu menu = new Menu();
        
        Menupopup popup = new Menupopup();
        
        Menuitem users = new Menuitem("Daftar Admin");
        Menuitem basis = new Menuitem("Basis Kasus");
        Menuitem kasusbaru = new Menuitem("Kasus Baru");
        Menuitem fitur = new Menuitem("Fitur");
        Menuitem solusi = new Menuitem("Solusi");
        Menuitem signout = new Menuitem("Keluar");
        
        Vlayout content = new Vlayout();
        
        public Layout() {
            
            setHflex("1");
            setVflex("1");
            
            content.setVflex("1");
            content.setHflex("1");
            
            menubar.setHflex("1");
            menubar.appendChild(menu);
            
            users.setIconSclass("z-icon-user");
            users.addEventListener(Events.ON_CLICK, e -> {

                Window window = new UserWindow();
                window.setPage(Layout.this.getPage());
                window.doOverlapped();
            });
            
            basis.setIconSclass("z-icon-cogs");
            basis.addEventListener(Events.ON_CLICK, e -> {

                Window window = new KasusWindow();
                window.setPage(Layout.this.getPage());
                window.doOverlapped();
            });
            
            kasusbaru.setIconSclass("z-icon-bell");
            kasusbaru.addEventListener(Events.ON_CLICK, e -> {

                Window window = new KasusBaruWindow();
                window.setPage(Layout.this.getPage());
                window.doOverlapped();
            });
            
            fitur.setIconSclass("z-icon-bullhorn");
            fitur.addEventListener(Events.ON_CLICK, e -> {

                Window window = new FiturWindow();
                window.setPage(Layout.this.getPage());
                window.doOverlapped();
            });
            
            solusi.setIconSclass("z-icon-bug");
            solusi.addEventListener(Events.ON_CLICK, e -> {

                Window window = new SolusiWindow();
                window.setPage(Layout.this.getPage());
                window.doOverlapped();
            });
            
            signout.setIconSclass("z-icon-sign-out");
            signout.addEventListener(Events.ON_CLICK, e -> {

                Executions.sendRedirect("/login");
            });
            
            popup.appendChild(users);
            popup.appendChild(new Menuseparator());
            popup.appendChild(basis);
            popup.appendChild(kasusbaru);
            popup.appendChild(fitur);
            popup.appendChild(solusi);
            popup.appendChild(new Menuseparator());
            popup.appendChild(signout);
            
            menu.setIconSclass("z-icon-navicon");
            menu.appendChild(popup);
            
            appendChild(menubar);
            appendChild(content);
        }
    }
    
}
