package com.kratonsolution.es.hamming.zk.solusi;

import org.zkoss.zul.Window;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class SolusiWindow extends Window {
    
    public SolusiWindow() {

        setWidth("900px");
        setHeight("500px");
        setBorder("normal");
        setSizable(true);
        setMinimizable(true);
        setMaximizable(true);
        setClosable(true);
        setPosition("center");
        setTitle("Management Data Solusi");

        appendChild(new SolusiGrid(this));
    }
}
