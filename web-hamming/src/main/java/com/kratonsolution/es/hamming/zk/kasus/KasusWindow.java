package com.kratonsolution.es.hamming.zk.kasus;

import org.zkoss.zul.Window;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class KasusWindow extends Window {
    
    public KasusWindow() {

        setWidth("925px");
        setHeight("650px");
        setBorder("normal");
        setSizable(true);
        setMinimizable(true);
        setMaximizable(true);
        setClosable(true);
        setPosition("center");
        setTitle("Management Data Basis Kasus");

        appendChild(new KasusGrid(this));
    }
}
