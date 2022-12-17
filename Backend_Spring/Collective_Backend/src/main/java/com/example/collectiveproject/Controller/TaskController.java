package com.example.collectiveproject.Controller;

import com.example.collectiveproject.Model.DTO.TaskDTO;
import com.example.collectiveproject.Model.Task;
import com.example.collectiveproject.Service.TaskService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/allTasks")
    public List<TaskDTO> getAllTasks(){return taskService.getTasks(); }

    @RequestMapping(value="/addTask", method = RequestMethod.POST)
    public TaskDTO addTask(@RequestBody TaskDTO taskDTO) throws Exception {
        Task task = this.taskService.convertDtoToEntity(taskDTO);
        Task taskCreated = this.taskService.addTask(task);
        return taskService.convertEntityToDto(taskCreated);
    }

    @GetMapping("/find-by-username/{username}")
    public List<Task> findAllTasksByUsername(@PathVariable(value="username") String username){
        return this.taskService.findAllByUsername(username);
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
//    @PostMapping("/update/{taskId}")
//    public ResponseEntity<?> update(@RequestBody Task task, @PathVariable Long taskId) {
//        try {
//            task.setId(taskId);
//            return new ResponseEntity<>(taskService.updateTask(task, taskId), HttpStatus.OK);
//        } catch (ServiceException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }

}
