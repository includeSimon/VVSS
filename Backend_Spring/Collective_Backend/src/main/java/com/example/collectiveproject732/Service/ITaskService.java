package com.example.collectiveproject732.Service;

import com.example.collectiveproject732.Model.Task;
import com.example.collectiveproject732.Model.viewmodel.TaskViewModel;
import com.example.collectiveproject732.Service.Exceptions.ServiceException;

public interface ITaskService {
    /**
     * Add a task
     * @param task a Task, the task to be added
     * @return a TaskViewModel corresponding to the task, if it was added successfully
     * @throws ServiceException if the task could not be added
     */
    TaskViewModel addTask (Task task) throws ServiceException;
}
