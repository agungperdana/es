package com.kratonsolution.es.hamming.zk.fitur;

import org.zkoss.zul.Window;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class FiturWindow extends Window {
    
    public FiturWindow() {

        setWidth("900px");
        setHeight("500px");
        setBorder("normal");
        setSizable(true);
        setMinimizable(true);
        setMaximizable(true);
        setClosable(true);
        setPosition("center");
        setTitle("Management Data Fitur");

        appendChild(new FiturGrid(this));
    }
}
