package com.kratonsolution.es.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.base.Preconditions;
import com.kratonsolution.es.cbr.application.CBREngine;
import com.kratonsolution.es.cbr.application.CBREngine.Algorithm;
import com.kratonsolution.es.cbr.application.KasusService;
import com.kratonsolution.es.cbr.application.ResultData;
import com.kratonsolution.es.cbr.application.SolutionService;
import com.kratonsolution.es.cbr.model.Kasus;
import com.kratonsolution.es.cbr.model.KasusSolusi;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Controller
public class KonsultasiController {
    
    @Autowired
    private CBREngine engine;
    
    @Autowired
    private KasusService service;
    
    @Autowired
    private SolutionService solutionService;
    
    @RequestMapping("/hasilkonsultasi.next/cosine")
    public String cosine(Model model, @RequestParam("g1")String g1, @RequestParam("g2")String g2,
            @RequestParam("g3")String g3, @RequestParam("g4")String g4, @RequestParam("g5")String g5,
            @RequestParam("g6")String g6, @RequestParam("g7")String g7, @RequestParam("g8")String g8,
            @RequestParam("g9")String g9, @RequestParam("g10")String g10,
            @RequestParam("g11")String g11, @RequestParam("g12")String g12, @RequestParam("g13")String g13,
            @RequestParam("g14")String g14, @RequestParam("g15")String g15, @RequestParam("g16")String g16,
            @RequestParam("g17")String g17, @RequestParam("g18")String g18, @RequestParam("g19")String g19,
            @RequestParam("g20")String g20, @RequestParam("g21")String g21, @RequestParam("g22")String g22,
            @RequestParam("g23")String g23, @RequestParam("g24")String g24, @RequestParam("g25")String g25,
            @RequestParam("g26")String g26, @RequestParam("g27")String g27, @RequestParam("g28")String g28,
            @RequestParam("g29")String g29, @RequestParam("g30")String g30, @RequestParam("g31")String g31,
            @RequestParam("g32")String g32, @RequestParam("g33")String g33, @RequestParam("g34")String g34,
            @RequestParam("g35")String g35) {
        
        StringBuilder builder = new StringBuilder();
        builder.append(g1);
        builder.append(g2);
        builder.append(g3);
        builder.append(g4);
        builder.append(g5);
        builder.append(g6);
        builder.append(g7);
        builder.append(g8);
        builder.append(g9);
        builder.append(g10);
        builder.append(g11);
        builder.append(g12);
        builder.append(g13);
        builder.append(g14);
        builder.append(g15);
        builder.append(g16);
        builder.append(g17);
        builder.append(g18);
        builder.append(g19);
        builder.append(g20);
        builder.append(g21);
        builder.append(g22);
        builder.append(g23);
        builder.append(g24);
        builder.append(g25);
        builder.append(g26);
        builder.append(g27);
        builder.append(g28);
        builder.append(g29);
        builder.append(g30);
        builder.append(g31);
        builder.append(g32);
        builder.append(g33);
        builder.append(g34);
        builder.append(g35);
        
        List<ResultData> hasil = engine.solve(Algorithm.COSINE, builder.toString());
        handleEmptyResult(hasil, builder.toString().toCharArray());
        model.addAttribute("hasil", hasil);
        
        return "hasil";
    }
    
    private void handleEmptyResult(@NonNull List<ResultData> list, @NonNull char[] binaries) {
        
        try {
            
            boolean match = list.stream().anyMatch(p -> p.match());
            if(!match) {
                
                Preconditions.checkArgument(binaries.length == 35, "Array length must be 35");
                
                Kasus kasus = new Kasus(toBoolean(binaries[0]), toBoolean(binaries[1]), toBoolean(binaries[2]), 
                        toBoolean(binaries[3]), toBoolean(binaries[4]), toBoolean(binaries[5]), 
                        toBoolean(binaries[6]), toBoolean(binaries[7]), toBoolean(binaries[8]), 
                        toBoolean(binaries[9]), toBoolean(binaries[10]), toBoolean(binaries[11]), toBoolean(binaries[12]), 
                        toBoolean(binaries[13]), toBoolean(binaries[14]), toBoolean(binaries[15]), toBoolean(binaries[16]), 
                        toBoolean(binaries[17]), toBoolean(binaries[18]), toBoolean(binaries[19]), 
                        toBoolean(binaries[20]), toBoolean(binaries[21]), toBoolean(binaries[22]), toBoolean(binaries[23]), 
                        toBoolean(binaries[24]), toBoolean(binaries[25]), toBoolean(binaries[26]), 
                        toBoolean(binaries[27]), toBoolean(binaries[28]), toBoolean(binaries[29]), toBoolean(binaries[30]), 
                        toBoolean(binaries[31]), toBoolean(binaries[32]), toBoolean(binaries[33]), toBoolean(binaries[34]));
                
                kasus.setIncubated(true);
             
                solutionService.getAllSolutiones().forEach(solution -> {
                    
                    KasusSolusi solusi = new KasusSolusi(kasus, solution.getId(), solution.getGejala(), 
                            solution.getJenis(), solution.getDescription(), false);
                    
                    kasus.getSolutions().add(solusi);
                });
                
                service.create(kasus);
            }
        } catch (Exception e) {

        }
    }
    
    private boolean toBoolean(char chr) {

        if(chr == '1') {
            return true;
        }
        else {
            return false;
        }
    }
}
