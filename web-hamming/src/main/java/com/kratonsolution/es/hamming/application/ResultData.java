package com.kratonsolution.es.hamming.application;

import java.util.ArrayList;
import java.util.List;

import com.kratonsolution.es.hamming.model.KasusSolusi;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Getter
@Setter
public class ResultData {
 
    private double percentMatch;
    
    private String kasusID;
    
    private List<KasusSolusi> solutions = new ArrayList<>();
    
    public String percent() {
        return (percentMatch*100)+"%";
    }
    
    public boolean match() {
        
        return percentMatch == 1d;
    }
}
