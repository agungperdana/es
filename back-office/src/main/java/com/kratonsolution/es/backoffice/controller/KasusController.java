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

import com.google.common.base.Preconditions;
import com.kratonsolution.es.cbr.application.KasusService;
import com.kratonsolution.es.cbr.application.SolutionService;
import com.kratonsolution.es.cbr.model.Kasus;
import com.kratonsolution.es.cbr.model.KasusSolusi;
import com.kratonsolution.es.cbr.model.Solution;
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
    private SolutionService solutionService;
    
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
            
            kasuses.addAll(service.getAllKasuses());
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
        
        model.addAttribute("solutions", solutionService.getAllSolutiones());
        
        return "/backoffice/kasus/add";
    }
    
    @RequestMapping("/backoffice/kasus/add/store")
    public String add(@RequestParam("fitur1")boolean fitur1, 
            @RequestParam("fitur2")boolean fitur2, 
            @RequestParam("fitur3")boolean fitur3, 
            @RequestParam("fitur4")boolean fitur4, 
            @RequestParam("fitur5")boolean fitur5, 
            @RequestParam("fitur6")boolean fitur6, 
            @RequestParam("fitur7")boolean fitur7, 
            @RequestParam("fitur8")boolean fitur8, 
            @RequestParam("fitur9")boolean fitur9, 
            @RequestParam("fitur10")boolean fitur10, 
            @RequestParam("fitur11")boolean fitur11, 
            @RequestParam("fitur12")boolean fitur12, 
            @RequestParam("fitur13")boolean fitur13, 
            @RequestParam("fitur14")boolean fitur14, 
            @RequestParam("fitur15")boolean fitur15, 
            @RequestParam("fitur16")boolean fitur16, 
            @RequestParam("fitur17")boolean fitur17,
            @RequestParam("fitur18")boolean fitur18,
            @RequestParam("fitur19")boolean fitur19,
            @RequestParam("fitur20")boolean fitur20,
            @RequestParam("fitur21")boolean fitur21,
            @RequestParam("fitur22")boolean fitur22,
            @RequestParam("fitur23")boolean fitur23,
            @RequestParam("fitur24")boolean fitur24,
            @RequestParam("fitur25")boolean fitur25,
            @RequestParam("fitur26")boolean fitur26,
            @RequestParam("fitur27")boolean fitur27,
            @RequestParam("fitur28")boolean fitur28,
            @RequestParam("fitur29")boolean fitur29,
            @RequestParam("fitur30")boolean fitur30,
            @RequestParam("fitur31")boolean fitur31,
            @RequestParam("fitur32")boolean fitur32,
            @RequestParam("fitur33")boolean fitur33,
            @RequestParam("fitur34")boolean fitur34,
            @RequestParam("fitur35")boolean fitur35,
            @RequestParam("selected")boolean[] selecteds,
            @RequestParam("solusionID")String[] solusionIDs) {
        
        Kasus kasus = new Kasus(fitur1, fitur2, fitur3, fitur4, fitur5, fitur6, fitur7, fitur8, fitur9, fitur10
                , fitur11, fitur12, fitur13, fitur14, fitur15, fitur16, fitur17, fitur18, fitur19, fitur20
                , fitur21, fitur22, fitur23, fitur24, fitur25, fitur26, fitur27, fitur28, fitur29, fitur30
                , fitur31, fitur32, fitur33, fitur34, fitur35);

        Preconditions.checkState(selecteds.length == solusionIDs.length, "Solution not match");
        
        for(int idx=0;idx<selecteds.length;idx++) {
            
            Optional<Solution> opt = solutionService.getById(solusionIDs[idx]);
            Preconditions.checkState(opt.isPresent(), "Solution with id [%s] does not exist", solusionIDs[idx]);
            
            KasusSolusi solusi = new KasusSolusi(kasus, solusionIDs[idx], opt.get().getGejala(), 
                    opt.get().getJenis(), opt.get().getDescription(), selecteds[idx]);
            
            kasus.getSolutions().add(solusi);
        }
        
        service.create(kasus);
        return "redirect:/backoffice/kasuses?page=0&size=50";
    }
    
    @RequestMapping("/backoffice/kasus/edit/pre/{id}")
    public String preedit(Model model, @PathVariable String id) {
        
        Optional<Kasus> opt = service.getById(id);
        if(opt.isPresent()) {
            model.addAttribute("kasus", opt.get());
        }
        
        return "/backoffice/kasus/edit";
    }
    
    @PostMapping("/backoffice/kasus/edit/store")
    public String edit(
            @RequestParam("id")String id,
            @RequestParam("fitur1")boolean fitur1, 
            @RequestParam("fitur2")boolean fitur2, 
            @RequestParam("fitur3")boolean fitur3, 
            @RequestParam("fitur4")boolean fitur4, 
            @RequestParam("fitur5")boolean fitur5, 
            @RequestParam("fitur6")boolean fitur6, 
            @RequestParam("fitur7")boolean fitur7, 
            @RequestParam("fitur8")boolean fitur8, 
            @RequestParam("fitur9")boolean fitur9, 
            @RequestParam("fitur10")boolean fitur10, 
            @RequestParam("fitur11")boolean fitur11, 
            @RequestParam("fitur12")boolean fitur12, 
            @RequestParam("fitur13")boolean fitur13, 
            @RequestParam("fitur14")boolean fitur14, 
            @RequestParam("fitur15")boolean fitur15, 
            @RequestParam("fitur16")boolean fitur16, 
            @RequestParam("fitur17")boolean fitur17,
            @RequestParam("fitur18")boolean fitur18,
            @RequestParam("fitur19")boolean fitur19,
            @RequestParam("fitur20")boolean fitur20,
            @RequestParam("fitur21")boolean fitur21,
            @RequestParam("fitur22")boolean fitur22,
            @RequestParam("fitur23")boolean fitur23,
            @RequestParam("fitur24")boolean fitur24,
            @RequestParam("fitur25")boolean fitur25,
            @RequestParam("fitur26")boolean fitur26,
            @RequestParam("fitur27")boolean fitur27,
            @RequestParam("fitur28")boolean fitur28,
            @RequestParam("fitur29")boolean fitur29,
            @RequestParam("fitur30")boolean fitur30,
            @RequestParam("fitur31")boolean fitur31,
            @RequestParam("fitur32")boolean fitur32,
            @RequestParam("fitur33")boolean fitur33,
            @RequestParam("fitur34")boolean fitur34,
            @RequestParam("fitur35")boolean fitur35,
            @RequestParam("selected")boolean[] selecteds,
            @RequestParam("solusionID")String[] solusionIDs) {
        
        service.update(id, fitur1, fitur2, fitur3, fitur4, fitur5, fitur6, fitur7, fitur8, fitur9, fitur10
                , fitur11, fitur12, fitur13, fitur14, fitur15, fitur16, fitur17, fitur18, fitur19, fitur20
                , fitur21, fitur22, fitur23, fitur24, fitur25, fitur26, fitur27, fitur28, fitur29, fitur30
                , fitur31, fitur32, fitur33, fitur34, fitur35, selecteds, solusionIDs);
        
        return "redirect:/backoffice/kasuses?page=0&size=50";
    }
    
    @RequestMapping("/backoffice/kasus/delete/{id}")
    public String delete(@PathVariable String id) {
        
        service.delete(id);
        return "redirect:/backoffice/kasuses?page=0&size=50";
    }
}
