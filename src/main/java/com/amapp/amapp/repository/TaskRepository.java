package com.amapp.amapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

import com.amapp.amapp.domain.Task;

import java.util.List;


public interface TaskRepository extends JpaRepository<Task,Long>{
   List<Task> findByUser_Id(Long user_id);
/*   @Query(value = "select t from Task t where t.user.id=:userid")
   List<Task> findByUser_Id(@Param("userid") Long user_id); */

/*   @Query(value = "select * from task t where t.user_id=:userid")
   List<Task> findByUser_Id(@Param("userid") Long user_id); */

}
