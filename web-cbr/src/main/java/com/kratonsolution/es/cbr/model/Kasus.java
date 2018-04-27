package com.kratonsolution.es.cbr.model;

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
@Setter
@Entity
@Table(name="kasus")
public class Kasus {
    
    @Id
    private String id = UUID.randomUUID().toString();
    
    @Column
    private boolean fitur1;
    
    @Column
    private boolean fitur2;
    
    @Column
    private boolean fitur3;
    
    @Column
    private boolean fitur4;
    
    @Column
    private boolean fitur5;
    
    @Column
    private boolean fitur6;
    
    @Column
    private boolean fitur7;
    
    @Column
    private boolean fitur8;
    
    @Column
    private boolean fitur9;
    
    @Column
    private boolean fitur10;
    
    @Column
    private boolean fitur11;
    
    @Column
    private boolean fitur12;
    
    @Column
    private boolean fitur13;
    
    @Column
    private boolean fitur14;
    
    @Column
    private boolean fitur15;
    
    @Column
    private boolean fitur16;
    
    @Column
    private boolean fitur17;
    
    @Column
    private boolean fitur18;
    
    @Column
    private boolean fitur19;
    
    @Column
    private boolean fitur20;
    
    @Column
    private boolean fitur21;
    
    @Column
    private boolean fitur22;
    
    @Column
    private boolean fitur23;
    
    @Column
    private boolean fitur24;
    
    @Column
    private boolean fitur25;
    
    @Column
    private boolean fitur26;
    
    @Column
    private boolean fitur27;
    
    @Column
    private boolean fitur28;
    
    @Column
    private boolean fitur29;
    
    @Column
    private boolean fitur30;
    
    @Column
    private boolean fitur31;
    
    @Column
    private boolean fitur32;
    
    @Column
    private boolean fitur33;
    
    @Column
    private boolean fitur34;
    
    @Column
    private boolean fitur35;
    
    @Setter
    @Column
    private boolean incubated;
    
    @OneToMany(mappedBy="parent", cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)
    private Set<KasusSolusi> solutions = new HashSet<>();
    
    @Version
    private Long version;
    
    Kasus() {}
    
    public Kasus(boolean fitur1, boolean fitur2, boolean fitur3, boolean fitur4, boolean fitur5, boolean fitur6, 
            boolean fitur7, boolean fitur8, boolean fitur9, boolean fitur10, boolean fitur11, boolean fitur12, 
            boolean fitur13, boolean fitur14, boolean fitur15, boolean fitur16, boolean fitur17, boolean fitur18, 
            boolean fitur19, boolean fitur20, boolean fitur21, boolean fitur22, boolean fitur23, boolean fitur24, 
            boolean fitur25, boolean fitur26, boolean fitur27, boolean fitur28, boolean fitur29, boolean fitur30, 
            boolean fitur31, boolean fitur32, boolean fitur33, boolean fitur34, boolean fitur35) {
        
        this.fitur1 = fitur1;
        this.fitur2 = fitur2;
        this.fitur3 = fitur3;
        this.fitur4 = fitur4;
        this.fitur5 = fitur5;
        this.fitur6 = fitur6;
        this.fitur7 = fitur7;
        this.fitur8 = fitur8;
        this.fitur9 = fitur9;
        this.fitur10 = fitur10;
        this.fitur11 = fitur11;
        this.fitur12 = fitur12;
        this.fitur13 = fitur13;
        this.fitur14 = fitur14;
        this.fitur15 = fitur15;
        this.fitur16 = fitur16;
        this.fitur17 = fitur17;
        this.fitur18 = fitur18;
        this.fitur19 = fitur19;
        this.fitur20 = fitur20;
        this.fitur21 = fitur21;
        this.fitur22 = fitur22;
        this.fitur23 = fitur23;
        this.fitur24 = fitur24;
        this.fitur25 = fitur25;
        this.fitur26 = fitur26;
        this.fitur27 = fitur27;
        this.fitur28 = fitur28;
        this.fitur29 = fitur29;
        this.fitur30 = fitur30;
        this.fitur31 = fitur31;
        this.fitur32 = fitur32;
        this.fitur33 = fitur33;
        this.fitur34 = fitur34;
        this.fitur35 = fitur35;
    }
    
    public String binaries() {
        
        StringBuilder builder = new StringBuilder();
        
        for(boolean fitur:toArray()) {
            
            if(fitur) {
                builder.append("1");
            }
            else {
                builder.append("0");
            }
        }
        
        return builder.toString();
    }
    
    public boolean[] toArray() {
        
        return new boolean[]{fitur1, fitur2, fitur3, fitur4, fitur5, fitur6, fitur7, fitur8, fitur9, fitur10, 
                fitur11, fitur12, fitur13, fitur14, fitur15, fitur16, fitur17, fitur18, fitur19, fitur20, fitur21,
                fitur22, fitur23, fitur24, fitur25, fitur26, fitur27, fitur28, fitur29, fitur30, fitur31, fitur32,
                fitur33, fitur34, fitur35};
    }
}
