package com.kratonsolution.es.hamming.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.kratonsolution.es.hamming.application.SolutionEvent.Type;
import com.kratonsolution.es.hamming.model.KasusSolusi;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Slf4j
@Service
public class KasusSolutionListener {
    
    @Autowired
    private KasusService service;
    
    @EventListener
    public void handle(@NonNull SolutionEvent event) {
        
        log.info("Kasus event ["+event.getType()+"]");
        if(event.getType().equals(Type.ADD)) {
            
            service.getAllKasuses().forEach(kasus -> {
                
                KasusSolusi solusi = new KasusSolusi(kasus, event.getSolution().getId(), event.getSolution().getDescription(), false);
                kasus.getSolutions().add(solusi);
                
                service.update(kasus);
            });
        }
        else if(event.getType().equals(Type.DELETE)) {
            
            service.getAllKasuses().forEach(kasus -> {
                
                kasus.getSolutions().removeIf(p -> p.getSolusiID().equals(event.getSolution().getId()));
                service.update(kasus);
            });
        }
    }
}
