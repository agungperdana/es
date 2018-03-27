package com.kratonsolution.es.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Controller
public class LandingController {
    
    @RequestMapping("/")
    public String index() {
        return "index";
    }
    
    @RequestMapping("/konsultasi.next")
    public String konsultasi() {
        return "konsultasi";
    }
    
    @RequestMapping("/bantuan.next")
    public String bantuan() {
        return "bantuan";
    }
    
    @RequestMapping("/kontak.next")
    public String kontak() {
        return "kontak";
    }
}
