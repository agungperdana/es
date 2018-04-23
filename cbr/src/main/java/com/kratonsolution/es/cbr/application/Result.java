package com.kratonsolution.es.cbr.application;

import java.util.Vector;

import com.kratonsolution.es.cbr.model.Kasus;

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
