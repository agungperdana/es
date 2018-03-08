package com.kratonsolution.es.backoffice.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kratonsolution.es.cbr.application.FiturService;
import com.kratonsolution.es.cbr.application.KasusService;
import com.kratonsolution.es.cbr.model.Kasus;
import com.kratonsolution.es.web.util.Page;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Controller
public class KasusController {
    
    @Autowired
    private KasusService service;
    
    @Autowired
    private FiturService fiturService;
    
    @RequestMapping("/backoffice/kasuses")
    public String kasuses(Model model,  
            @RequestParam("page")int page, @RequestParam("size")int size, 
            @RequestParam(value="key", required=false) Optional<String> key) {
        
        Collection<Kasus> kasuses = new ArrayList<>();
        
        if(key.isPresent()) {
            kasuses.addAll(service.getAllKasuses(key.get(), page, size));
        }
        else {
            kasuses.addAll(service.getAllKasuses(page, size));
        }
        
        model.addAttribute("kasuses", kasuses);
        model.addAttribute("page", page+1);
        model.addAttribute("totalPage", Page.getPage(size, kasuses.size()));
        
        return "/backoffice/kasus/kasuses";
    }
    
    @RequestMapping("/backoffice/kasus/add/pre")
    public String preadd(Model model) {

        model.addAttribute("fitures", fiturService.getAllFitures());
        
        return "/backoffice/kasus/add";
    }
    
    @RequestMapping("/backoffice/kasus/add/store")
    public String add(@RequestParam("date") String name, @RequestParam(value="note", required=false)Optional<String> note) {

//        service.create(new Kasus(name, note.orElse(null)));
        return "redirect:/backoffice/kasuses?page=0&size=50";
    }
    
    @RequestMapping("/backoffice/kasus/edit/pre/{id}")
    public String preedit(Model model, @PathVariable String id) {

        model.addAttribute("kasus", service.getById(id).orElse(null));
        
        return "/backoffice/kasus/edit";
    }
    
    @PostMapping("/backoffice/kasus/edit/store")
    public String edit(@RequestParam("name") String name, @RequestParam(value="note", required=false)Optional<String> note) {

//        service.update(new Kasus(name, note.orElse(null)));
        return "redirect:/backoffice/kasuses?page=0&size=50";
    }

    @RequestMapping("/backoffice/kasus/delete/{id}")
    public String delete(@PathVariable String id) {
        
        service.delete(id);
        return "redirect:/backoffice/kasuses?page=0&size=50";
    }
}
