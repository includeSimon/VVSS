package com.example.collectiveproject.Controller;

import com.example.collectiveproject.Model.DTO.TaskDTO;
import com.example.collectiveproject.Model.Task;
import com.example.collectiveproject.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/allTasks")
    public List<TaskDTO> getAllTasks(){return taskService.getTasks(); }

    @RequestMapping(value="/addTask", method = RequestMethod.POST)
    public TaskDTO addTask(@RequestBody TaskDTO taskDTO) throws Exception {
        Task task = this.taskService.convertDtoToEntity(taskDTO);
        Task taskCreated = this.taskService.addTask(task);
        return taskService.convertEntityToDto(taskCreated);
    }

}
