package com.kratonsolution.es.backoffice.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kratonsolution.es.cbr.application.KasusService;
import com.kratonsolution.es.cbr.model.Kasus;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Controller
public class KasusBaruController {
    
    @Autowired
    private KasusService service;
    
    @RequestMapping("/backoffice/kasusesbaru")
    public String kasusesbaru(Model model) {
        
        model.addAttribute("kasusesbaru", service.getAllKasusBaru());
        return "/backoffice/kasusbaru/kasusesbaru";
    }
    
    @RequestMapping("/backoffice/kasusbaru/edit/pre/{id}")
    public String preedit(Model model, @PathVariable String id) {
        
        Optional<Kasus> opt = service.getById(id);
        if(opt.isPresent()) {
            model.addAttribute("kasusbaru", opt.get());
        }
        
        return "/backoffice/kasusbaru/edit";
    }
    
    @PostMapping("/backoffice/kasusbaru/edit/store")
    public String edit(
            @RequestParam("id")String id,
            @RequestParam("selected")boolean[] selecteds,
            @RequestParam("solusionID")String[] solutionIDs) {
        
        service.acceptKasus(id, selecteds, solutionIDs);
        
        return "redirect:/backoffice/kasusesbaru";
    }
    
    @RequestMapping("/backoffice/kasusbaru/delete/{id}")
    public String delete(@PathVariable String id) {
        
        service.delete(id);
        return "redirect:/backoffice/kasusesbaru";
    }
}
