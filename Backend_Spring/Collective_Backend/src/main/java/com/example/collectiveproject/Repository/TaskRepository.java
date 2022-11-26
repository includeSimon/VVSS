package com.example.collectiveproject.Repository;

import com.example.collectiveproject.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {

    Task findAllById(Long id);
}
