package com.kratonsolution.es.hamming.application;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.text.similarity.HammingDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kratonsolution.es.hamming.model.Kasus;
import com.kratonsolution.es.hamming.model.RiskType;

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
            Optional<Kasus> target = kasuses.stream().filter(p -> p.binaries().equals(input)).findFirst();
            if(target.isPresent()) {
                
                ResultData data = new ResultData();
                data.setPercentMatch(1d);
                data.setKasusID(target.get().getId());
                data.setMatch(true);
                data.setType(target.get().getType());
                
                target.get().getSolutions().forEach(sol -> {
                    
                    if(sol.isSelected()) {
                        data.getSolutions().add(sol);
                    }
                });
                
                results.add(data);
            }
            else {    
                
                Map<RiskType, Kasus> map = new HashMap<>();
                
                int MAX = 21;
                
                for(Kasus kasus: kasuses) {
                    
                    HammingDistance hamming = new HammingDistance();
                    int distance = hamming.apply(input, kasus.binaries());
                    kasus.setPercent(getPercent(distance).doubleValue());
                    
                    if(distance < MAX) {
                        
                        log.info("change max distance {}", distance);
                        
                        MAX = distance;
                        map.clear(); //reset the map
                    }

                    log.info("contains {}", map.containsKey(kasus.getType()));
                    
                    if(distance == MAX && !map.containsKey(kasus.getType())) {
                        map.put(kasus.getType(), kasus);
                    }
                }
                
                map.values().forEach(k -> {
                    
                    ResultData data = new ResultData();
                    data.setPercentMatch(k.getPercent());
                    data.setKasusID(k.getId());
                    data.setMatch(false);
                    data.setType(k.getType());
                    
                    k.getSolutions().forEach(sol -> {
                        
                        if(sol.isSelected()) {
                            data.getSolutions().add(sol);
                        }
                    });
                    
                    results.add(data);
                });
                
                results.sort(new Comparator<ResultData>() {

                    @Override
                    public int compare(ResultData o1, ResultData o2) {
                        
                        return o1.getType().getOrder() - o2.getType().getOrder(); 
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return results;
    }
    
    public BigDecimal getPercent(int distance) {
        
        return (BigDecimal.valueOf(21).subtract(BigDecimal.valueOf(distance))).divide(BigDecimal.valueOf(21), 1, RoundingMode.HALF_UP);
    }
    
    public static void main(String[] args) {
        
        String s1 = "000000";
        String s2 = "100000";
        
        HammingDistance hamming = new HammingDistance();
        System.out.println(hamming.apply(s1, s2));
    }
}
