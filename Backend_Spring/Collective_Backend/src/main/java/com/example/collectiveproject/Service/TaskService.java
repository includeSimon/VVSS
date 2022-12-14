package com.example.collectiveproject.Service;

import com.example.collectiveproject.Model.Category;
import com.example.collectiveproject.Model.DTO.TaskDTO;
import com.example.collectiveproject.Model.Task;
import com.example.collectiveproject.Repository.TaskRepository;
import com.example.collectiveproject.Utility.TaskValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CategoryService categoryService;

    public List<Task> findAll(){
        return this.taskRepository.findAll();
    }

    public List<Task> findAllByUsername(String username) {
        return taskRepository.findAll().stream().filter(task ->
                task.getUsersTasks().stream().filter(userTask ->
                        Objects.equals(userTask.getUser().getUserName(), username)
                ).toList().size() > 0
        ).toList();
    }

    public List<TaskDTO> getTasks(){
        return (taskRepository.findAll())
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

        if(!TaskValidator.isValidDaysToCompleteTask(task.getDaysToCompleteTask())){
            throw new Exception("Days to complete task should be higher than 0!");
        }

        if(!TaskValidator.isValidStatus(task.getStatus().toString())){
            throw new Exception("Status is not valid!");
        }

        if(!TaskValidator.isValidRewardPoints(task.getRewardPoints())){
            throw new Exception("Reward points are a number between 0 and 10!");
        }

        Category category = this.categoryService.findCategoryByCategoryName(task.category.getNameCategory());

        if(category == null)
            throw new Exception("Category was not found!");

        task.setCategory(category);

        return this.taskRepository.save(task);

    }

    public TaskDTO convertEntityToDto(Task task) {
        return TaskDTO.builder()
                .id(task.getId())
                .name(task.getName())
                .description(task.getDescription())
                .daysToCompleteTask(task.getDaysToCompleteTask())
                .status(task.getStatus())
                .category(task.getCategory())
                .rewardPoints(task.getRewardPoints())
                .build();
    }

    public Task convertDtoToEntity(TaskDTO taskDTO) {
        return Task.builder()
                .id(taskDTO.getId())
                .name(taskDTO.getName())
                .description(taskDTO.getDescription())
                .daysToCompleteTask(taskDTO.getDaysToCompleteTask())
                .category(this.categoryService.findCategoryByCategoryName(taskDTO.getCategory().getNameCategory()))
                .status(taskDTO.getStatus())
                .rewardPoints(taskDTO.getRewardPoints())
                .build();
    }

}
