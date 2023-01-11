package com.example.collectiveproject.Service;

import com.example.collectiveproject.Model.Category;
import com.example.collectiveproject.Model.DTO.TaskDTO;
import com.example.collectiveproject.Model.Task;
import com.example.collectiveproject.Model.User;
import com.example.collectiveproject.Model.UserTask;
import com.example.collectiveproject.Repository.TaskRepository;
import com.example.collectiveproject.Repository.UserRepository;
import com.example.collectiveproject.Repository.UserTaskRepository;
import com.example.collectiveproject.Utility.TaskValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTaskRepository userTaskRepository;

    @Autowired
    private CategoryService categoryService;


    public List<TaskDTO> getTasks(){
        return (taskRepository.findAll())
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }


    public List<TaskDTO> findAllByUsername(String username) {
            List<User> users = userRepository.findAll().stream()
                    .filter(user1 -> user1.getUserName().equals(username)).toList();
            User user;
            try {
                user = users.get(0);
            } catch (Exception e){
                return null;
            }

            List<Long> taskIds = userTaskRepository.findAll().stream().filter(
                    userTask -> Objects.equals(userTask.getUser().getId(), user.getId()) &&
                            userTask.getActualDate() == null
            ).map(userTask -> userTask.getTask().getId()).toList();

            List<Task> tasks = new ArrayList<Task>();
            for (Long taskid : taskIds) {
                    tasks.add(taskRepository.findById(taskid).get());
            }
            return tasks.stream().map(this::convertEntityToDto).collect(Collectors.toList());
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

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    public Task updateTask(Task object) {
        return taskRepository.save(object);
    }

    public TaskDTO convertEntityToDto(Task task) {
        return TaskDTO.builder()
                .id(task.getId())
                .name(task.getName())
                .description(task.getDescription())
                .daysToCompleteTask(task.getDaysToCompleteTask())
                .status(task.getStatus())
                .category(task.getCategory().getNameCategory())
                .rewardPoints(task.getRewardPoints())
                .build();
    }

    public Task convertDtoToEntity(TaskDTO taskDTO) {
        return Task.builder()
                .id(taskDTO.getId())
                .name(taskDTO.getName())
                .description(taskDTO.getDescription())
                .daysToCompleteTask(taskDTO.getDaysToCompleteTask())
                .category(this.categoryService.findCategoryByCategoryName(taskDTO.getCategory()))
                .status(taskDTO.getStatus())
                .rewardPoints(taskDTO.getRewardPoints())
                .build();
    }

    public boolean markDone(String taskName, String username) {
        List<Task> tasks = taskRepository.findAll().stream()
                .filter(task1 -> task1.getName().equals(taskName)).toList();
        Task task;
        try {
            task = tasks.get(0);
        } catch (Exception e){
            return false;
        }

        List<User> users = userRepository.findAll().stream()
                .filter(user1 -> user1.getUserName().equals(username)).toList();
        User user;
        try {
            user = users.get(0);
        } catch (Exception e){
            return false;
        }

        List<UserTask> userTaskList = userTaskRepository.findAll().stream().filter(
                userTask -> Objects.equals(userTask.getUser().getId(), user.getId()) &&
                        Objects.equals(userTask.getTask().getId(), task.getId()) &&
                        userTask.getActualDate() == null
        ).toList();
        if (userTaskList.size() == 0) return false;
        UserTask mark = userTaskList.get(0);
        mark.setActualDate(LocalDate.now());
        userTaskRepository.save(mark);
        return true;
    }

    public boolean markUnDone(String taskName, String username) {
        List<Task> tasks = taskRepository.findAll().stream()
                .filter(task1 -> task1.getName().equals(taskName)).toList();
        Task task;
        try {
            task = tasks.get(0);
        } catch (Exception e){
            return false;
        }

        List<User> users = userRepository.findAll().stream()
                .filter(user1 -> user1.getUserName().equals(username)).toList();
        User user;
        try {
            user = users.get(0);
        } catch (Exception e){
            return false;
        }

        List<UserTask> userTaskList = userTaskRepository.findAll().stream().filter(
                userTask -> Objects.equals(userTask.getUser().getId(), user.getId()) &&
                        Objects.equals(userTask.getTask().getId(), task.getId()) &&
                        userTask.getActualDate() != null
        ).toList();
        if (userTaskList.size() == 0) return false;
        UserTask mark = userTaskList.get(0);
        mark.setActualDate(null);
        userTaskRepository.save(mark);
        return true;
    }
}
