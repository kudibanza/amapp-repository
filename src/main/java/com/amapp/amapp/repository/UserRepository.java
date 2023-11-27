package com.amapp.amapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amapp.amapp.domain.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   
}
