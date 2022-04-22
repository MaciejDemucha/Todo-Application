package io.github.mat3e.controller;

import io.github.mat3e.model.Task;
import io.github.mat3e.model.TaskRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
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

    //Pobranie pojedyńczego taska
    @GetMapping("/tasks/{id}")
    ResponseEntity<Task> readTask(@PathVariable("id") int id){
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Mapowanie dla PUT - Valid zapewnia, że nie będzie tworzona nowa encja
    @PutMapping("/tasks/{id}")
    ResponseEntity<?> updateTask(@PathVariable("id") int id, @RequestBody @Valid Task toUpdate){
        if(!repository.existsById(id)){
           return ResponseEntity.notFound().build();
        }
        toUpdate.setId(id);
        repository.save(toUpdate);
        return ResponseEntity.noContent().build();
    }

    //Mapowanie dla POST
    @PostMapping("/tasks")
    ResponseEntity<Task> addTask(@RequestBody @Valid Task toAdd){
        Task newTask = repository.save(toAdd);
        //if (newTask == null) return ResponseEntity.noContent().build();
        //else return new ResponseEntity<>(newTask, HttpStatus.CREATED);
        return ResponseEntity.created(URI.create("/" + newTask.getId())).body(newTask);
    }
}
