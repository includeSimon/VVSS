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


    /**
     * delete a task
     * @param taskId id of the task
     * @return a ServiceEmptyResponse with status:
     * 200 - success
     * 500 - internal server error
     * 404 - expense not found
     * @throws ServiceException if something goes wrong on the server
     */
    TaskViewModel deleteTask(Long taskId) throws ServiceException;


    /**
     * updates a task
     * @param task entity containing the data used to update the task
     * @param taskId the id of the task to be updated
     * @return the viewModel of the updated task
     * @throws ServiceException if the taskId is invalid or an error occurred while updating the task
     */
    TaskViewModel updateTask(Task task, Long taskId) throws ServiceException;
}
