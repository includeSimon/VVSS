package com.example.collectiveproject732.Service;

import com.example.collectiveproject732.Model.Task;
import com.example.collectiveproject732.Model.viewmodel.TaskViewModel;
import com.example.collectiveproject732.Repository.ITaskRepository;
import com.example.collectiveproject732.Service.Exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
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

    @Override
    public TaskViewModel deleteTask(Long taskId) throws ServiceException {

        Optional<Task> task = taskRepository.findOne(Math.toIntExact(taskId));

        if (task.isPresent()) {
            Optional<Task> taskToDelete = taskRepository.delete(taskId);

            if (taskToDelete.isEmpty()) {
                throw new ServiceException("Internal server error");
            } else {
                return taskToDelete.get().toTaskViewModel();
            }
        } else {
            throw new ServiceException("This task doesn't exist");
        }
    }

    @Override
    public TaskViewModel updateTask(Task task, Long taskId) throws ServiceException {
        Optional<Task> oldTask = taskRepository.findOne(Math.toIntExact(taskId));

        if (oldTask.isEmpty()) {
            throw new ServiceException("Invalid expenseID");
        }

        if (!Objects.equals(task.getId(), oldTask.get().getId())) {
            throw new ServiceException("The id does not correspond to old id");
        }

        task.setId(taskId);
        Optional<Task> updatedTask = taskRepository.update(task);

        if (updatedTask.isPresent()) {
            throw new ServiceException("An error occurred while updating the expense.");
        }

        return TaskViewModel.fromTask(task);
    }
}
