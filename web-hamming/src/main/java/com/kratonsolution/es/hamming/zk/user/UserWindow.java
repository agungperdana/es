package com.kratonsolution.es.hamming.zk.user;

import org.zkoss.zul.Window;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class UserWindow extends Window {
    
    public UserWindow() {

        setWidth("900px");
        setHeight("500px");
        setBorder("normal");
        setSizable(true);
        setMinimizable(true);
        setMaximizable(true);
        setClosable(true);
        setPosition("center");
        setTitle("Management Data Admin");

        appendChild(new UserGrid(this));
    }
}
