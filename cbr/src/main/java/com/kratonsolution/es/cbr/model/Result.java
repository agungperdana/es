package com.kratonsolution.es.cbr.model;

import java.util.Vector;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Getter
@Setter
public class Result {
 
    private double percentMatch = 0d;
    
    private Vector<Kasus> cases = new Vector<>();
}
