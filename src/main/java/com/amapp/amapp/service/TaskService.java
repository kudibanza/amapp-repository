package com.amapp.amapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amapp.amapp.domain.Task;
import com.amapp.amapp.domain.User;
import com.amapp.amapp.repository.TaskRepository;

import jakarta.transaction.Transactional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserService userService;

    public Task findTaskById(Long id){
        Optional<Task> user=this.taskRepository.findById(id);
        return user.orElseThrow(()->new RuntimeException("Tarefa não encontrado Id: "+id+" , Tipo: "+Task.class.getName()));
   
    }

    public List<Task> findAllByUserId(Long userid){
        List<Task> tasks=this.taskRepository.findByUser_Id(userid);
        return tasks;
    }

    @Transactional
    public Task Create(Task obj){
        User user=this.userService.findUserById(obj.getUser().getId());
        obj.setId(null);//Garantido que seja auto
        obj.setUser(user);
        obj=this.taskRepository.save(obj);
         return obj;
    }

    @Transactional
    public Task Update(Task obj){
        Task newobj=findTaskById(obj.getId());
        newobj.setDescription(obj.getDescription());
        return this.taskRepository.save(newobj);
    }

    public void Delete(Long id){
        findTaskById(id);
        try {
            this.taskRepository.deleteById(id);
        } catch (Exception e) {
        throw new RuntimeException("Não é possível excluir o objecto, porque está relacionado.");
        }
    }



}//
