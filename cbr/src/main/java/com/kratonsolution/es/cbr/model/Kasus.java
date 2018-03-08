package com.kratonsolution.es.cbr.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Getter
@Entity
@Table(name="kasus")
public class Kasus {
    
    @Id
    private String id = UUID.randomUUID().toString();
    
    @Column
    private String code;
    
    @Column(name="created_date")
    private Timestamp date;
    
    @Setter
    @Column
    private boolean deleted;
    
    @Version
    private Long version;
    
    @OneToMany(mappedBy="parent", cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)
    private Set<KasusFitur> fitures = new HashSet<>();
    
    @OneToMany(mappedBy="parent", cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)
    private Set<Solution> solutions = new HashSet<>();
}
