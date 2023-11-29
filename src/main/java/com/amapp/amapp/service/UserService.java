package com.amapp.amapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amapp.amapp.domain.User;
import com.amapp.amapp.repository.UserRepository;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findUserById(Long id){
        Optional<User> user=this.userRepository.findById(id);
        return user.orElseThrow(()->new RuntimeException("Usuário não encontrado Id: "+id+" , Tipo: "+User.class.getName()));
    }

    @Transactional
    public User Create(User obj){
       obj.setId(null);//Garantido que seja auto
       obj=this.userRepository.save(obj);
        return obj;
    }

    @Transactional
    public User Update(User obj){
        obj.setId(null);//Garantido que seja auto
        User newobj=findUserById(obj.getId());
        newobj.setPassword(obj.getPassword());
        newobj=this.userRepository.save(newobj);
         return obj;
     }

     public void Delete(Long id){
findUserById(id);
try {
    this.userRepository.deleteById(id);
} catch (Exception e) {
throw new RuntimeException("Não é possível excluir, pois a Entidade está relacionada");
}
     }
    
} //