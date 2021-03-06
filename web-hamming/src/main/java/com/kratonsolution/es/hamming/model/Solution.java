package com.kratonsolution.es.hamming.model;

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
@Table(name="solution")
public class Solution {
    
    @Id
    private String id = UUID.randomUUID().toString();
    
    @Setter
    @Column
    private String description;
        
    @Version
    private Long version;
    
    Solution() {}
    
    public Solution(@NonNull String description) {
        
        this.description = description;
    }
    
    @Override
    public String toString() {

        return this.description;
    }
}
