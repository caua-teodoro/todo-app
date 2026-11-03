package br.edu.todo.repository;

import br.edu.todo.model.Task;
import br.edu.todo.model.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findByTodoListId(UUID todoListId);
    List<Task> findByTodoListIdAndStatus(UUID todoListId, TaskStatus status);
    Optional<Task> findByIdAndTodoListId(UUID id, UUID todoListId);
}
