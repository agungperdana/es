package com.kratonsolution.es.cbr.model;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    @Column
    private String gejala;
    
    @Column
    private String jenis;
    
    @Setter
    @Column
    private String description;
    
    @ManyToOne
    @JoinColumn(name="fk_kasus")
    private Kasus parent;
    
    @Column(name="created_date")
    private Timestamp createdDate;
        
    @Version
    private Long version;
    
    Solution() {
    }
    
    public Solution(@NonNull Kasus parent, @NonNull String gejala, @NonNull String jenis, @NonNull String description) {
        
        this.parent = parent;
        this.gejala = gejala;
        this.jenis = jenis;
        this.description = description;
        this.createdDate = new Timestamp(System.currentTimeMillis());
    }
    
    public Solution(@NonNull String id, @NonNull Kasus parent, @NonNull String gejala, @NonNull String jenis, @NonNull String description) {
        
        this.id = id;
        this.parent = parent;
        this.gejala = gejala;
        this.jenis = jenis;
        this.description = description;
        this.createdDate = new Timestamp(System.currentTimeMillis());
    }
}
