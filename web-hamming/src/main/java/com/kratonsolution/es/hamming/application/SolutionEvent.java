package com.kratonsolution.es.hamming.application;

import com.kratonsolution.es.hamming.model.Solution;

import lombok.Getter;
import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Getter
public class SolutionEvent {
    
    public enum Type {ADD, EDIT, DELETE}
    
    private Type type = Type.ADD;
    
    private Solution solution;
    
    public SolutionEvent(@NonNull Type type, @NonNull Solution solution) {
        
        this.type = type;
        this.solution = solution;
    }
}
