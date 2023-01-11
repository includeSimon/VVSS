package com.example.collectiveproject.Service;

import com.example.collectiveproject.Model.Task;
import com.example.collectiveproject.Model.User;
import com.example.collectiveproject.Model.UserTask;
import com.example.collectiveproject.Repository.TaskRepository;
import com.example.collectiveproject.Repository.UserRepository;
import com.example.collectiveproject.Repository.UserTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


@Service
public class AssignationService {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTaskRepository userTaskRepository;


    public boolean assignTaskToUser(String taskName, String username) {
        List<Task> tasks = taskRepository.findAll().stream()
                .filter(task1 -> task1.getName().equals(taskName)).toList();
        Task task;
        try {
            task = tasks.get(0);
        } catch (Exception e){
            return false;
        }

        User user = userService.findByUsername(username);
        if (user == null) return false;

        List<Long> taskIds = userTaskRepository.findAll().stream().filter(
                userTask -> Objects.equals(userTask.getUser().getId(), user.getId()) &&
                        userTask.getActualDate() == null
        ).map(userTask -> userTask.getTask().getId()).toList();

        // Task already exists and it is not marked as done
        if (taskIds.contains(task.getId())) return false;

        // Calculate deadline
        LocalDate deadline = LocalDate.now().plusDays(task.getDaysToCompleteTask());

        UserTask entry = UserTask.builder()
                                    .id(null)
                                    .user(user)
                                    .task(task)
                                    .deadline(deadline)
                                    .actualDate(null)
                                    .build();

        userTaskRepository.save(entry);
        task.getUsersTasks().add(entry);
        taskRepository.save(task);
        user.getUserTasks().add(entry);
        userRepository.save(user);
        return true;
    }

    public boolean unAssignTaskFromUser(String taskName, String username) {
        List<Task> tasks = taskRepository.findAll().stream()
                .filter(task1 -> task1.getName().equals(taskName)).toList();
        Task task;
        try {
            task = tasks.get(0);
        } catch (Exception e){
            return false;
        }

        User user = userService.findByUsername(username);
        if (user == null) return false;

        List<Long> userTaskIds = userTaskRepository.findAll().stream().filter(
                userTask -> Objects.equals(userTask.getUser().getId(), user.getId()) &&
                        Objects.equals(userTask.getTask().getId(), task.getId()) &&
                        userTask.getActualDate() == null
        ).map(UserTask::getId).toList();

        for (Long id:
                userTaskIds) {
            userTaskRepository.deleteById(id);
        }
        return true;
    }


}
