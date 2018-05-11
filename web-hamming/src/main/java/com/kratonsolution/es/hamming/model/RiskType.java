package com.kratonsolution.es.hamming.model;

import lombok.Getter;

public enum RiskType {
    RENDAH(1), SEDANG(2), TINGGI(3);

    @Getter
    private int order;
    
    RiskType(int order) {
        
        this.order = order;
    }
}