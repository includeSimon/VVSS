package com.example.collectiveproject.Controller;

import com.example.collectiveproject.Model.Task;
import com.example.collectiveproject.Service.TaskService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Controller
@CrossOrigin("localhost:4200")
@RequestMapping("api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/find-all")
    public ResponseEntity<List<Task>> findAllTasks(){
        return ResponseEntity.ok(this.taskService.findAll());
    }
}
