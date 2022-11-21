package com.example.collectiveproject732.Repository;

import com.example.collectiveproject732.Model.Task;

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

        /**
         * Method for deleting an entity by id
         * @param id: Long, the id of the entity to be deleted
         * @return an {@code Optional}
         *          - null, if there is no entity with that id
         *          - the removed entity, otherwise
         * @throws IllegalArgumentException if id is null
         */
        Optional<Task> delete(Long id);

        /**
         * Method for finding an entity
         * @param id: ID, id of the desired entity
         * @return an {@code Optional}
         *          - null, if there is no entity with id equal to the id
         *          - the entity, otherwise
         * @throws IllegalArgumentException if id is null
         */
        Optional<Task> findOne(Integer id);

        /**
         * Method for updating an entity
         * @param entity: Entity, the entity to be updated
         * @return an {@code Optional}
         *          - null, if the entity was successfully updated
         *          - the entity, otherwise
         * @throws IllegalArgumentException if entity is null
         */
        Optional<Task> update(Task entity);




}
