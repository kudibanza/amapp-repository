package com.amapp.amapp.controller;

import java.net.URI;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.amapp.amapp.domain.Task;
import com.amapp.amapp.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/task")
@Validated
public class TaskController {
    @Autowired
    private TaskService tService;

    @GetMapping("/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id){
        Task obj=this.tService.findTaskById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    @Validated
    public ResponseEntity<Void> Create(@Valid @RequestBody Task obj){
        this.tService.Create(obj);
        URI url=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(url).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> Upadate(@Valid @RequestBody Task obj, @PathVariable Long id){
        obj.setId(id);
        this.tService.Update(obj);
        return ResponseEntity.noContent().build();
    }
   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> Delete(@PathVariable Long id){
        this.tService.Delete(id);
        return ResponseEntity.noContent().build();
    }
 
    @GetMapping("/user/{userid}")
    public ResponseEntity<List<Task>> findAllByUserId(@PathVariable Long userid){
        List<Task> objs=this.tService.findAllByUserId(userid);
        return ResponseEntity.ok().body(objs);
    }
    
}
