package com.kratonsolution.es.hamming.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import com.google.common.base.MoreObjects;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Getter
@Setter
@Entity
@Table(name="kasus")
public class Kasus {
    
    @Id
    private String id = UUID.randomUUID().toString();
    
    @Column
    private String fitur1;
    
    @Column
    private String fitur2;
    
    @Column
    private String fitur3;
    
    @Column
    private String fitur4;
    
    @Column
    private String fitur5;
    
    @Column
    private String fitur6;
    
    @Column
    private String fitur7;
    
    @Column
    @Enumerated(EnumType.STRING)
    private RiskType type = RiskType.RENDAH;
    
    @Setter
    @Column
    private boolean incubated = false;
    
    @Transient
    private double percent;
    
    @OneToMany(mappedBy="parent", cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)
    private Set<KasusSolusi> solutions = new HashSet<>();
    
    @Version
    private Long version;
    
    Kasus() {}
    
    public Kasus(@NonNull String fitur1, 
            @NonNull String fitur2, 
            @NonNull String fitur3, 
            @NonNull String fitur4, 
            @NonNull String fitur5, 
            @NonNull String fitur6, 
            @NonNull String fitur7) {
        
        this(fitur1, fitur2, fitur3, fitur4, fitur5, fitur6, fitur7, RiskType.RENDAH);
    }
    
    public Kasus(@NonNull String fitur1, 
            @NonNull String fitur2, 
            @NonNull String fitur3, 
            @NonNull String fitur4, 
            @NonNull String fitur5, 
            @NonNull String fitur6, 
            @NonNull String fitur7, 
            @NonNull RiskType riskType) {
        
        this.fitur1 = fitur1;
        this.fitur2 = fitur2;
        this.fitur3 = fitur3;
        this.fitur4 = fitur4;
        this.fitur5 = fitur5;
        this.fitur6 = fitur6;
        this.fitur7 = fitur7;
        this.type = riskType;
    }
    
    public String binaries() {
        
        StringBuilder builder = new StringBuilder();
        
        for(String fitur:toArray()) {
            builder.append(fitur);
        }
        
        return builder.toString();
    }
    
    public String[] toArray() {
        
        return new String[]{fitur1, fitur2, fitur3, fitur4, fitur5, fitur6, fitur7};
    }
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("fitur1", fitur1)
                .add("fitur2", fitur2)
                .add("fitur3", fitur3)
                .add("fitur4", fitur4)
                .add("fitur5", fitur5)
                .add("fitur6", fitur6)
                .add("fitur7", fitur7)
                .toString();
    }
}
