package com.kratonsolution.es.hamming.zk.kasus;

import org.zkoss.zul.Caption;
import org.zkoss.zul.Window;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class KasusWindow extends Window {
    
    public KasusWindow() {

        setWidth("925px");
        setHeight("650px");
        setSizable(true);
        setMinimizable(true);
        setMaximizable(true);
        setClosable(true);
        setPosition("center");

        Caption caption = new Caption("Management Data Basis Kasus");
        caption.setIconSclass("z-icon-cogs");
        
        appendChild(caption);
        appendChild(new KasusGrid(this));
    }
}
