package io.github.mat3e.controller;

import io.github.mat3e.model.Task;
import io.github.mat3e.model.TaskRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RestController
class TaskController {
    private final static Logger logger = LoggerFactory.getLogger(TaskController.class);
    private final TaskRepository repository;

    TaskController(final TaskRepository repository){
        this.repository = repository;
    }

    //Mapowanie na controller (GET /tasks)
    //Ostrzeżenie informujące o wyświetleniu wszystkich zadań
    //Nie korzystamy z mappingu, gdy używamy wymienionych parametrów
    @GetMapping(value = "/tasks", params = {"!sort", "!page", "!size"})
    ResponseEntity<List<Task>> readAllTasks(){
        logger.warn("Exposing all the tasks!");
        return ResponseEntity.ok(repository.findAll());
    }

    //Wersja dla m.in. sortowania
    @GetMapping("/tasks")
    ResponseEntity<List<Task>> readAllTasks(Pageable page){
        logger.info("Custom pageable");
        return ResponseEntity.ok(repository.findAll(page).getContent());
    }
    //Mapowanie dla PUT - Valid zapewnia, że nie będzie tworzona nowa encja
    @PutMapping("/tasks/{id}")
    ResponseEntity<?> updateTask(@RequestBody @Valid Task toUpdate){
        repository.save(toUpdate);
        return ResponseEntity.noContent().build();
    }
}
