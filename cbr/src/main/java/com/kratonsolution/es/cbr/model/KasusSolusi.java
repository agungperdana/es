package com.kratonsolution.es.cbr.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Getter;
import lombok.NonNull;

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

    @NonNull
    @ManyToOne
    @JoinColumn(name="fk_solution")
    private Solution solution;
    
    @Version
    private Long version;
    
    KasusSolusi() {}
    
    public KasusSolusi(@NonNull Kasus parent, @NonNull Solution solution) {

        this.parent = parent;
        this.solution = solution;
    }
}
