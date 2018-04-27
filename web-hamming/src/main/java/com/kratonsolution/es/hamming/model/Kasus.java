package com.kratonsolution.es.hamming.model;

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

import com.google.common.base.MoreObjects;

import lombok.Getter;
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
    
    @Setter
    @Column
    private boolean incubated = false;
    
    @OneToMany(mappedBy="parent", cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)
    private Set<KasusSolusi> solutions = new HashSet<>();
    
    @Version
    private Long version;
    
    Kasus() {}
    
    public Kasus(String fitur1, String fitur2, String fitur3, String fitur4, String fitur5, String fitur6, String fitur7) {
        
        this.fitur1 = fitur1;
        this.fitur2 = fitur2;
        this.fitur3 = fitur3;
        this.fitur4 = fitur4;
        this.fitur5 = fitur5;
        this.fitur6 = fitur6;
        this.fitur7 = fitur7;
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
