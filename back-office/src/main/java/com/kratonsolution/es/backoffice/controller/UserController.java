package com.kratonsolution.es.backoffice.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Controller
public class UserController {
    
    @RequestMapping("/backoffice/users")
    public String users(@AuthenticationPrincipal Optional<Principal> opt) {
        
        return "/backoffice/users";
    }
}
