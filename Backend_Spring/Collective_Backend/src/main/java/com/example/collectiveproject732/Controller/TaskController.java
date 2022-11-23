package com.example.collectiveproject732.Controller;

import com.example.collectiveproject732.DTO.TaskDTO;
import com.example.collectiveproject732.Model.Task;
import com.example.collectiveproject732.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
