package com.kratonsolution.es.cbr.application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.text.similarity.CosineSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kratonsolution.es.cbr.model.Kasus;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Slf4j
@Service
public class CBREngine {
    
    @Autowired
    public KasusService service;
    
    public enum Algorithm {HAMMING, COSINE}
    
    public List<ResultData> solve(@NonNull Algorithm algorithm, @NonNull String input) {
        
        List<ResultData> results = new ArrayList<ResultData>();
        
        try {
            
            List<Kasus> kasuses = service.getAllKasuses();
            if(!kasuses.isEmpty()) {
                
                CosineSimilarity cosine = new CosineSimilarity();
                
                if(algorithm.equals(Algorithm.COSINE)) {
                    
                    for(Kasus kasus: kasuses) {
                        
                        Map<CharSequence, Integer> leftVector = Arrays.stream(input.split(""))
                                .collect(Collectors.toMap(c -> c, c -> 1, Integer::sum));
                        
                        Map<CharSequence, Integer> rightVector = Arrays.stream(kasus.binaries().split(""))
                                .collect(Collectors.toMap(c -> c, c -> 1, Integer::sum));
                        
                        double similarity = cosine.cosineSimilarity(leftVector, rightVector);
                        
                        log.info("input...{}", input);
                        log.info("binaries.. {}", kasus.binaries());
                        log.info("equals....{}% ", input.equals(kasus.binaries()));
                        log.info("Similaritys....{}% ", similarity*100);
                        
                        if(input.equals(kasus.binaries())) {
                            
                            ResultData data = new ResultData();
                            data.setPercentMatch(1d);
                            data.setKasusID(kasus.getId());
                            
                            kasus.getSolutions().forEach(sol -> {
                                
                                if(sol.isSelected()) {
                                    data.getSolutions().add(sol);
                                }
                            });
                            
                            
                            
                            results.add(data);
                            break;
                        }
                        else if(similarity > 0.8d) {
                            
                            ResultData data = new ResultData();
                            data.setPercentMatch(similarity);
                            data.setKasusID(kasus.getId());
                            
                            kasus.getSolutions().forEach(sol -> {
                                
                                if(sol.isSelected()) {
                                    data.getSolutions().add(sol);
                                }
                            });
                            
                            results.add(data);
                        }
                    }
                }
                else if(algorithm.equals(Algorithm.HAMMING)) {
                    
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        
        return results;
    }
}
