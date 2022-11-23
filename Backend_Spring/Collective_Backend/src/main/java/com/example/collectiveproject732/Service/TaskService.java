package com.example.collectiveproject732.Service;

import com.example.collectiveproject732.DTO.TaskDTO;
import com.example.collectiveproject732.Model.Task;
import com.example.collectiveproject732.Repository.TaskRepository;
import com.example.collectiveproject732.Utility.TaskValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

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
        return TaskDTO.builder()
                .id(task.getId())
                .name(task.getName())
                .description(task.getDescription())
                .targetDate(task.getTargetDate())
                .status(task.getStatus())
                .category(task.getCategory())
                .build();
    }

    public Task convertDtoToEntity(TaskDTO taskDTO) {
        return Task.builder()
                .id(taskDTO.getId())
                .name(taskDTO.getName())
                .description(taskDTO.getDescription())
                .targetDate(taskDTO.getTargetDate())
                .category(taskDTO.getCategory())
                .status(taskDTO.getStatus())
                .build();
    }

}
