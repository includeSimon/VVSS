package com.example.collectiveproject732.Repository;

import com.example.collectiveproject732.Model.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

    @Query(value = "select * from Task t where t.id=?", nativeQuery = true)
    Task findByID(@Param("id") Long id);
}
