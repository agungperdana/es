package com.kratonsolution.es.hamming.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kratonsolution.es.hamming.application.CBREngine;
import com.kratonsolution.es.hamming.application.KasusService;
import com.kratonsolution.es.hamming.application.ResultData;
import com.kratonsolution.es.hamming.application.SolutionService;
import com.kratonsolution.es.hamming.model.Kasus;
import com.kratonsolution.es.hamming.model.KasusSolusi;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Slf4j
@Controller
public class LandingController {
    
    @Autowired
    private KasusService service;
    
    @Autowired
    private SolutionService solutionService;
    
    @Autowired
    private CBREngine engine;
    
    @RequestMapping(path={"/",""})
    public String index() {
        
        return "index";
    }
    
    @RequestMapping("/login")
    public String login() {
        
        return "redirect:/svc/login";
    }
    
    @RequestMapping("/konsultasi")
    public String consult(Model model, @RequestParam("fitur1") String fitur1, 
            @RequestParam("fitur2") String fitur2, 
            @RequestParam("fitur3") String fitur3, 
            @RequestParam("fitur4") String fitur4, 
            @RequestParam("fitur5") String fitur5, 
            @RequestParam("fitur6") String fitur6, 
            @RequestParam("fitur7") String fitur7) {
        
        StringBuilder builder = new StringBuilder();
        builder.append(fitur1);
        builder.append(fitur2);
        builder.append(fitur3);
        builder.append(fitur4);
        builder.append(fitur5);
        builder.append(fitur6);
        builder.append(fitur7);
        
        List<ResultData> results =  engine.solve(builder.toString());
        boolean match = results.stream().anyMatch(p -> p.isMatch());
        if(!match) {
            
            Kasus kasus = new Kasus(fitur1, fitur2, fitur3, fitur4, fitur5, fitur6, fitur7);
            kasus.setIncubated(true);
            
            solutionService.getAllSolutiones().forEach(sol -> {
                kasus.getSolutions().add(new KasusSolusi(kasus, sol.getId(), sol.getDescription(), false));
            });
            
            service.create(kasus);
        }

        log.info("result {} size {}", results, results.size());
        
        model.addAttribute("results", results);
        
        return "hasil";
    }
}
