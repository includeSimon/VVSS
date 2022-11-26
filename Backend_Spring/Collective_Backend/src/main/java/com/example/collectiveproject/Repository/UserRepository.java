package com.example.collectiveproject.Repository;

import com.example.collectiveproject.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
