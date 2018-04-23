package com.kratonsolution.es.cbr.model;

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
@Table(name="kasus_solusi")
public class KasusSolusi {
    
    @Id
    private String id = UUID.randomUUID().toString();

    @NonNull
    @ManyToOne
    @JoinColumn(name="fk_kasus")
    private Kasus parent;

    @Setter
    @Column
    private boolean selected;
    
    @Column
    private String solusiID;
    
    @Column
    private String gejala;
    
    @Column
    private String jenis;
    
    @Setter
    @Column
    private String description;
    
    
    @Version
    private Long version;
    
    KasusSolusi() {}
    
    public KasusSolusi(@NonNull Kasus parent, @NonNull String solutionID, 
            @NonNull String gejala, @NonNull String jenis, String description, boolean selected) {

        this.parent = parent;
        this.solusiID = solutionID;
        this.gejala = gejala;
        this.jenis = jenis;
        this.description = description;
        this.selected = selected;
    }
}
