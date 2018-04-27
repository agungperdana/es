package com.kratonsolution.es.hamming.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Getter
@Entity
@Table(name="fitur_kasus")
public class KasusFitur {
    
    @Id
    private String id = UUID.randomUUID().toString();

    @Column
    private String fitur;
    
    @Setter
    @Column
    private boolean value;
    
    @ManyToOne
    @JoinColumn(name="fk_kasus")
    private Kasus parent;
    
    KasusFitur() {
    }
    
    public KasusFitur(@NonNull Kasus kasus, @NonNull String fitur, boolean value) {
        
        this.parent = kasus;
        this.fitur = fitur;
        this.value = value;
    }
}
