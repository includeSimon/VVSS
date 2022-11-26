package com.example.collectiveproject.Service;

import com.example.collectiveproject.Model.DTO.TaskDTO;
import com.example.collectiveproject.Model.Task;
import com.example.collectiveproject.Model.UserTask;
import com.example.collectiveproject.Repository.TaskRepository;
import com.example.collectiveproject.Utility.TaskValidator;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.tags.HtmlEscapingAwareTag;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll(){
        return this.taskRepository.findAll();
    }

    public List<TaskDTO> getTasks(){
        return ((List<Task>) taskRepository.findAll())
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public Task addTask(Task task) throws Exception {

        if(task == null){
            throw new Exception("Task should not be null!");
        }

        if(!TaskValidator.isValidName(task.getName())){
            throw new Exception("Name is not valid!");
        }

        if(!TaskValidator.isValidDescription(task.getDescription())){
            throw new Exception("Description is not valid!");
        }

        if(TaskValidator.isValidDate(task.getTargetDate().toString())){
            throw new Exception("Date is not valid!");
        }

        if(!TaskValidator.isValidStatus(task.getStatus().toString())){
            throw new Exception("Status is not valid!");
        }

        if(!TaskValidator.isValidCategory(task.getCategory().toString())){
            throw new Exception("Category is not valid!");
        }

        return this.taskRepository.save(task);

    }

    public TaskDTO convertEntityToDto(Task task) {
        return new TaskDTO(
                task.getId(),
                task.getName(),
                task.getDescription(),
                task.getTargetDate(),
                task.getStatus(),
                task.getCategory(),
                task.getReward()
        );
    }

    public Task convertDtoToEntity(TaskDTO taskDTO) {
        return new Task(
                taskDTO.getId(),
                taskDTO.getCategory(),
                taskDTO.getName(),
                taskDTO.getStatus(),
                taskDTO.getTargetDate(),
                taskDTO.getReward(),
                taskDTO.getDescription(),
                new ArrayList<>()
        );
    }
}
