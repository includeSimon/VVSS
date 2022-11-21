package com.example.collectiveproject732.Controller;

import com.example.collectiveproject732.Model.Task;
import com.example.collectiveproject732.Service.Exceptions.ServiceException;
import com.example.collectiveproject732.Service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/task")
public class TaskController {
    @Autowired
    private ITaskService taskService;

    @PostMapping("/add")
    public ResponseEntity<?> saveTask(@RequestBody Task task){
        try{
            return new ResponseEntity<>(taskService.addTask(task), HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Endpoint for deleting a task
     * Method: DELETE
     * @param taskId the id of the expense to delete
     * @return a ResponseEntity with the TaskViewModel corresponding to the added task
     *         or with an error message if the task could not be added
     */
    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<?> delete(@PathVariable Long taskId) {
        try {
            return new ResponseEntity<>(taskService.deleteTask(taskId), HttpStatus.OK);
        }
        catch (Exception e ){
            String message = e.getMessage();
            if ("Internal server error".equals(message)) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Endpoint for updating a task
     * Method: POST
     * Requires Authorization header
     * @param task the new data for the task
     * @param taskId the id of the task to be updated
     * @return the viewModel of the updated expense
     */
    @PostMapping("/update/{taskId}")
    public ResponseEntity<?> update(@RequestBody Task task, @PathVariable Long taskId) {
        try {
            task.setId(taskId);
            return new ResponseEntity<>(taskService.updateTask(task, taskId), HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
