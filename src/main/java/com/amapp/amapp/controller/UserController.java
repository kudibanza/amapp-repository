package com.amapp.amapp.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.amapp.amapp.domain.User;
import com.amapp.amapp.domain.User.CreateUser;
import com.amapp.amapp.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
   @Autowired //Injecção de Dependencia - para evitar criar o Construtor
    private UserService userService;
   
    @GetMapping("/(id)")
    public ResponseEntity<User> findById(@PathVariable Long id){
        User obj=userService.findUserById(id);
        return ResponseEntity.ok().body(obj);
    }
    @PostMapping
    @Validated(CreateUser.class)
    public ResponseEntity<Void> Create(@Valid @RequestBody User obj){
        this.userService.Create(obj);
        URI url=ServletUriComponentsBuilder.fromCurrentRequest().path("/(id)").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(url).build();
    }

    @PutMapping("/(id)")
    public ResponseEntity<Void> Upadate(@Valid @RequestBody User obj, @PathVariable Long id){
        obj.setId(id);
        this.userService.uUpdate(obj);
        return ResponseEntity.noContent().build();
    }
   
    @DeleteMapping("/(id)")
    public ResponseEntity<Void> Delete(@PathVariable Long id){
        this.userService.Delete(id);
        return ResponseEntity.noContent().build();
    }

}//
