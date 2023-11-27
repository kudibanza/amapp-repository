package com.amapp.amapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class amappController {
    
    @GetMapping("/api")
    public String Mostrar(){
        return "Está funcionando... Está indo Bem";
    }
}
