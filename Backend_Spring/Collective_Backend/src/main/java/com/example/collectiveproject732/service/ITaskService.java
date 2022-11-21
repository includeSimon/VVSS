package com.example.collectiveproject732.service;

import com.example.collectiveproject732.model.Task;
import com.example.collectiveproject732.model.viewmodel.TaskViewModel;
import com.example.collectiveproject732.service.exceptions.ServiceException;

public interface ITaskService {
    /**
     * Add a task
     * @param task a Task, the task to be added
     * @return a TaskViewModel corresponding to the task, if it was added successfully
     * @throws ServiceException if the task could not be added
     */
    TaskViewModel addTask (Task task) throws ServiceException;
}
