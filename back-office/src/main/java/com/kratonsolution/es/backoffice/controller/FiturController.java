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
import com.kratonsolution.es.cbr.model.Fitur;
import com.kratonsolution.es.web.util.Page;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Controller
public class FiturController {
    
    @Autowired
    private FiturService service;
    
    @RequestMapping("/backoffice/fitures")
    public String fitures(Model model,  
            @RequestParam("page")int page, @RequestParam("size")int size, 
            @RequestParam(value="key", required=false) Optional<String> key) {
        
        Collection<Fitur> fitures = new ArrayList<>();
        
        if(key.isPresent()) {
            fitures.addAll(service.getAllFitures(key.get(), page, size));
        }
        else {
            fitures.addAll(service.getAllFitures(page, size));
        }
        
        model.addAttribute("fitures", fitures);
        model.addAttribute("page", page+1);
        model.addAttribute("totalPage", Page.getPage(size, fitures.size()));
        
        return "/backoffice/fitur/fitures";
    }
    
    @RequestMapping("/backoffice/fitur/add/pre")
    public String preadd() {

        return "/backoffice/fitur/add";
    }
    
    @RequestMapping("/backoffice/fitur/add/store")
    public String add(@RequestParam("name") String name, @RequestParam(value="note", required=false)Optional<String> note) {

        service.create(new Fitur(name, note.orElse(null)));
        return "redirect:/backoffice/fitures?page=0&size=50";
    }
    
    @RequestMapping("/backoffice/fitur/edit/pre/{id}")
    public String preedit(Model model, @PathVariable String id) {

        model.addAttribute("fitur", service.getById(id).orElse(null));
        
        return "/backoffice/fitur/edit";
    }
    
    @PostMapping("/backoffice/fitur/edit/store")
    public String edit(@RequestParam("name") String name, @RequestParam(value="note", required=false)Optional<String> note) {

        service.update(new Fitur(name, note.orElse(null)));
        return "redirect:/backoffice/fitures?page=0&size=50";
    }

    @RequestMapping("/backoffice/fitur/delete/{id}")
    public String delete(@PathVariable String id) {
        
        service.delete(id);
        return "redirect:/backoffice/fitures?page=0&size=50";
    }
}
