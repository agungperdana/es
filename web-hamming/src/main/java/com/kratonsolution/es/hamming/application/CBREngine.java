package com.kratonsolution.es.hamming.application;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.text.similarity.HammingDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kratonsolution.es.hamming.model.Kasus;

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
    
    public List<ResultData> solve(@NonNull String input) {
        
        List<ResultData> results = new ArrayList<ResultData>();
        
        try {
            
            List<Kasus> kasuses = service.getAllKasuses();
            if(!kasuses.isEmpty()) {
                
                for(Kasus kasus: kasuses) {
                    
                    HammingDistance hamming = new HammingDistance();
                    int distance = hamming.apply(input, kasus.binaries());
                    
                    BigDecimal similaritys = (BigDecimal.valueOf(21).subtract(BigDecimal.valueOf(distance))).divide(BigDecimal.valueOf(21), 15, RoundingMode.HALF_UP);
                    
                    log.info("input...{}", input);
                    log.info("binaries.. {}", kasus.binaries());
                    log.info("equals....{} ", input.equals(kasus.binaries()));
                    log.info("distance....{} ", distance);
                    log.info("Similaritys....{}% ", similaritys.doubleValue());
                    
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
                    else if(similaritys.doubleValue() > 0.8d) {
                        
                        ResultData data = new ResultData();
                        data.setPercentMatch(similaritys.doubleValue());
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return results;
    }
}
