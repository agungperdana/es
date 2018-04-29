package com.kratonsolution.es.hamming.zk.kasusbaru;

import org.zkoss.zul.Window;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class KasusBaruWindow extends Window {
    
    public KasusBaruWindow() {

        setWidth("925px");
        setHeight("650px");
        setBorder("normal");
        setSizable(true);
        setMinimizable(true);
        setMaximizable(true);
        setClosable(true);
        setPosition("center");
        setTitle("Management Data Basis Kasus Baru");

        appendChild(new KasusBaruGrid(this));
    }
}
