package com.example.collectiveproject732.service;

import com.example.collectiveproject732.model.Task;
import com.example.collectiveproject732.model.viewmodel.TaskViewModel;
import com.example.collectiveproject732.repository.ITaskRepository;
import com.example.collectiveproject732.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TaskService implements ITaskService{
    public TaskService(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Autowired
    private final ITaskRepository taskRepository;

    @Override
    public TaskViewModel addTask(Task task) throws ServiceException {
        Optional<Task> savedTask = taskRepository.save(task);

        if (savedTask.isPresent()) {
            throw new ServiceException("An error occurred while saving the Task.");
        }

        return TaskViewModel.fromTask(task);
    }
}
