package br.edu.todo.repository;

import br.edu.todo.model.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TodoListRepository extends JpaRepository<TodoList, UUID> {

    List<TodoList> findByOwnerId(UUID ownerId);

    Optional<TodoList> findByIdAndOwnerId(UUID id, UUID ownerId);
}
