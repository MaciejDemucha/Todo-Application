package io.github.mat3e.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.net.ContentHandler;
import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    List<Task> findAll();

    Page<Task> findAll(Pageable page);

    //znajdowanie zadań wg. stanu ich wykonania
    List<Task> findByDone(@Param("state")boolean done);

    Optional<Task> findById(Integer id);

    boolean existsById(Integer id);

    Task save(Task entity);
}
