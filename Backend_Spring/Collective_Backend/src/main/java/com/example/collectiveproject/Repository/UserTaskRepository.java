package com.example.collectiveproject.Repository;

import com.example.collectiveproject.Model.UserTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTaskRepository extends JpaRepository<UserTask,Long> {
}