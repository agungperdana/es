package com.kratonsolution.es.backoffice.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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
import com.kratonsolution.es.cbr.model.Fitur;
import com.kratonsolution.es.cbr.model.Kasus;
import com.kratonsolution.es.cbr.model.KasusFitur;
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
    
    private Comparator<KasusFitur> comparator = new Comparator<KasusFitur>() {
        
        @Override
        public int compare(KasusFitur o1, KasusFitur o2) {
            
            Optional<Fitur> f1 = fiturService.getByName(o1.getFitur());
            Optional<Fitur> f2 = fiturService.getByName(o2.getFitur());
            
            if(f1.isPresent() && f2.isPresent()) {
                return f1.get().getSequence() - f2.get().getSequence();
            }
            
            return 0;
        };
    };
    
    @RequestMapping("/backoffice/kasuses")
    public String kasuses(Model model,  
            @RequestParam("page")int page, @RequestParam("size")int size, 
            @RequestParam(value="key", required=false) Optional<String> key) {
        
        int count = 0;
        
        if(size <= 0) {
            size = 50;
        }
        
        int total = Page.getPage(size, count);
        if(total == 1) {
            page = 0;
        }
        else if(page > total) {
            page = total;
        }
        
        Collection<Kasus> kasuses = new ArrayList<>();
        
        if(key.isPresent()) {
            
            kasuses.addAll(service.getAllKasuses(key.get(), page, size));
            count = service.count(key.get());
        }
        else {
            
            kasuses.addAll(service.getAllKasuses(page, size));
            count = service.count();
        }
        
        model.addAttribute("kasuses", kasuses);
        model.addAttribute("key", key.orElse(""));
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPage", total);
        
        return "/backoffice/kasus/kasuses";
    }
    
    @RequestMapping("/backoffice/kasus/add/pre")
    public String preadd(Model model) {
        
        model.addAttribute("fitures", fiturService.getAllFitures());
        
        return "/backoffice/kasus/add";
    }
    
    @RequestMapping("/backoffice/kasus/add/store")
    public String add(@RequestParam("fitur")String[] fitur, 
            @RequestParam("fiturevalue")boolean[] fiturevalue,
            @RequestParam("gejala")String[] gejala,
            @RequestParam("jenis")String[] jenis,
            @RequestParam("solusi")String[] solusi,
            @RequestParam("note")String note) {
        
        Kasus kasus = new Kasus("init", note);
        
        for(int idx=0; idx<fitur.length; idx++) {
            kasus.addFitur(fitur[idx], fiturevalue[idx]);
        }
        
        for(int idx=0; idx<gejala.length; idx++) {
            kasus.addSolution(gejala[idx], jenis[idx], solusi[idx]);
        }
        
        List<KasusFitur> sorted = new ArrayList<>(kasus.getFitures());
        
        Collections.sort(sorted, comparator);
        
        StringBuilder builder = new StringBuilder();
        
        sorted.stream().forEach(fit -> builder.append(fit.isValue()?"1":"0"));
        kasus.setCode(builder.toString());
        
        service.create(kasus);
        
        return "redirect:/backoffice/kasuses?page=0&size=50";
    }
    
    @RequestMapping("/backoffice/kasus/edit/pre/{id}")
    public String preedit(Model model, @PathVariable String id) {
        
        Optional<Kasus> opt = service.getById(id);
        if(opt.isPresent()) {
            
            List<KasusFitur> fiturs = opt.get().getFiturAsList();
            fiturs.sort(comparator);
            
            model.addAttribute("kasus", opt.get());
            model.addAttribute("fiturs", fiturs);
        }
        
        return "/backoffice/kasus/edit";
    }
    
    @PostMapping("/backoffice/kasus/edit/store")
    public String edit(@RequestParam("code")String code,
                       @RequestParam("solusiID")String[] solusiID,
                       @RequestParam("gejala")String[] gejala,
                       @RequestParam("jenis")String[] jenis,
                       @RequestParam("solusi")String[] solusi,
                       @RequestParam("note")String note) {
        
        Kasus kasus = new Kasus(code, note);
        
        for(int idx=0; idx<gejala.length; idx++) {
            kasus.addSolution(solusiID[idx], gejala[idx], jenis[idx], solusi[idx]);
        }
        
        service.update(kasus);
        
        return "redirect:/backoffice/kasuses?page=0&size=50";
    }
    
    @RequestMapping("/backoffice/kasus/delete/{id}")
    public String delete(@PathVariable String id) {
        
        service.delete(id);
        return "redirect:/backoffice/kasuses?page=0&size=50";
    }
}
