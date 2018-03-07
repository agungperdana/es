package com.kratonsolution.es.cbr.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Getter
@Entity
@Table(name="fitur")
public class Fitur {
    
    @Id
    private String id = UUID.randomUUID().toString();
    
    @Column
    private String name;
    
    @Setter
    @Column
    private String note;
    
    @Version
    private Long version;
    
    Fitur() {}
    
    public Fitur(@NonNull String name, String note) {
        
        this.name = name;
        this.note = note;
    }
}
