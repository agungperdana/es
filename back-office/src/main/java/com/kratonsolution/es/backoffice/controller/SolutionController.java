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

import com.kratonsolution.es.cbr.application.SolutionService;
import com.kratonsolution.es.cbr.model.Solution;
import com.kratonsolution.es.web.util.Page;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Controller
public class SolutionController {
    
    @Autowired
    private SolutionService service;
    
    @RequestMapping("/backoffice/solutions")
    public String solutions(Model model,  
            @RequestParam("page")int page, @RequestParam("size")int size, 
            @RequestParam(value="key", required=false) Optional<String> key) {
        
        int count = 0;
        if(size < 0) {
            size = 50;
        }
        
        int total = Page.getPage(size, count);
        if(total == 1) {
            page = 0;
        }
        else if(page > total) {
            page = total;
        }
        
        Collection<Solution> solutions = new ArrayList<>();
        
        if(key.isPresent()) {
            
            solutions.addAll(service.getAllSolutiones(key.get(), page, size));
            count = service.count(key.get());
        }
        else {
            
            solutions.addAll(service.getAllSolutiones(page, size));
            count = service.count();
        }
        
        model.addAttribute("solutions", solutions);
        model.addAttribute("key", key.orElse(""));
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPage", total);
        
        return "/backoffice/solution/solutions";
    }
    
    @RequestMapping("/backoffice/solution/add/pre")
    public String preadd() {

        return "/backoffice/solution/add";
    }
    
    @RequestMapping("/backoffice/solution/add/store")
    public String add(@RequestParam("gejala") String gejala, @RequestParam("jenis") String jenis,
            @RequestParam("description")String note) {

        service.create(new Solution(gejala, jenis, note));
        return "redirect:/backoffice/solutions?page=0&size=50";
    }
    
    @RequestMapping("/backoffice/solution/edit/pre/{id}")
    public String preedit(Model model, @PathVariable String id) {

        model.addAttribute("solution", service.getById(id).orElse(null));
        
        return "/backoffice/solution/edit";
    }
    
    @PostMapping("/backoffice/solution/edit/store/{id}")
    public String edit(@PathVariable String id, @RequestParam("description")Optional<String> note) {

        service.update(id, note);
        return "redirect:/backoffice/solutions?page=0&size=50";
    }

    @RequestMapping("/backoffice/solution/delete/{id}")
    public String delete(@PathVariable String id) {
        
        service.delete(id);
        return "redirect:/backoffice/solutions?page=0&size=50";
    }
}
