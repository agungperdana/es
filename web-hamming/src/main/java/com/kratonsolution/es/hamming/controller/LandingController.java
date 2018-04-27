package com.kratonsolution.es.hamming.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Controller
public class LandingController {
    
    @RequestMapping(path={"/",""})
    public String index() {
        
        return "index";
    }
    
    @RequestMapping("/login")
    public String login() {
        
        return "redirect:/svc/login";
    }
}
