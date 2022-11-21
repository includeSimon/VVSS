package com.example.collectiveproject732.repository;

import com.example.collectiveproject732.model.Task;

import java.util.Optional;

public interface ITaskRepository {
        /**
         * Method for saving a Task
         * @param task: Task, the task to be saved
         * @return an {@code Optional}
         *          - null, if it was successfully saved
         *          - the entity (id already exists)
         * @throws IllegalArgumentException if task is null
         */
        Optional<Task> save(Task task);

}
