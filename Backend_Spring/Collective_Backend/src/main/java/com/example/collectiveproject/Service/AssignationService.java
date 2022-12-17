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
        List<Task> tasks = taskService.findAll().stream()
                .filter(task1 -> task1.getName().equals(taskName)).toList();
        Task task;
        try {
            task = tasks.get(0);
        } catch (Exception e){
            return false;
        }

        User user = userService.findByUsername(username);
        if (user == null) return false;

        // Calculate deadline
        LocalDate deadline = LocalDate.now().plusDays(task.getDaysToCompleteTask());

        UserTask entry = UserTask.builder()
                                    .id(null)
                                    .user(user)
                                    .task(task)
                                    .deadline(deadline)
                                    .build();

        userTaskRepository.save(entry);
        task.getUsersTasks().add(entry);
        taskRepository.save(task);
        user.getUserTasks().add(entry);
        userRepository.save(user);
        return true;
    }


}
