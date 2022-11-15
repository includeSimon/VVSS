package com.example.collectiveproject732.Controller;

import com.example.collectiveproject732.Model.Task;
import com.example.collectiveproject732.Service.Exceptions.ServiceException;
import com.example.collectiveproject732.Service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/task")
public class TaskController {
    @Autowired
    private ITaskService taskService;

    @PostMapping("/add")
    public ResponseEntity<?> saveTask(@RequestBody Task task){
        try{
            return new ResponseEntity<>(taskService.addTask(task), HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
