package com.kratonsolution.es.cbr.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Getter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Getter
@Entity(name="solution")
@Table(name="solution")
public class Solution {
    
    @Id
    private String id = UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn(name="fk_kasus")
    private Kasus parent;
    
    @Version
    private Long version;
}
