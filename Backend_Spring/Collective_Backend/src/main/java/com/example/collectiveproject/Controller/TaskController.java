package com.example.collectiveproject.Controller;

import com.example.collectiveproject.Model.Task;
import com.example.collectiveproject.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/find-by-username/{username}")
    public List<Task> findAllTasksByUsername(@PathVariable(value="username") String username){
        return this.taskService.findAllByUsername(username);
    }

    @PostMapping("/add")
    public boolean addTask(@RequestBody Task task){
        try {
            taskService.addTask(task);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
