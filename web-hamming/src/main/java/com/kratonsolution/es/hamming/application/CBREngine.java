package com.kratonsolution.es.hamming.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Slf4j
@Service
public class CBREngine {
    
    @Autowired
    public KasusService service;
    
    public enum Algorithm {HAMMING, COSINE}
    
    public List<ResultData> solve(@NonNull Algorithm algorithm, @NonNull String input) {
        
        List<ResultData> results = new ArrayList<ResultData>();
        
        try {
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        
        return results;
    }
}
