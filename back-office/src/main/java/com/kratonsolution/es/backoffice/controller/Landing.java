package com.kratonsolution.es.backoffice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Controller
public class Landing {

    @RequestMapping("/backoffice")
    public String land() {
        
        return "redirect:/backoffice/login";
    }
}
