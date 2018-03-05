package com.kratonsolution.es.backoffice.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kratonsolution.es.security.application.UserService;
import com.kratonsolution.es.security.model.User;
import com.kratonsolution.es.web.util.Page;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Controller
public class UserController {
    
    @Autowired
    private UserService service;
    
    @RequestMapping("/backoffice/users")
    public String users(Model model,  
            @RequestParam("page")int page, @RequestParam("size")int size, 
            @RequestParam(value="key", required=false) Optional<String> key) {
        
        Collection<User> users = new ArrayList<>();
        
        if(key.isPresent()) {
            users.addAll(service.getAllUsers(key.get(), page, size));
        }
        else {
            users.addAll(service.getAllUsers(page, size));
        }
        
        model.addAttribute("users", users);
        model.addAttribute("page", page+1);
        model.addAttribute("totalPage", Page.getPage(size, users.size()));
        
        return "/backoffice/users";
    }
}
