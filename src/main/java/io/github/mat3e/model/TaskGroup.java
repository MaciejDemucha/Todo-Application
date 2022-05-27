package io.github.mat3e.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "task_groups")
public class TaskGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Task group's description must not be empty.")
    private String description;     //opis grupy
    private boolean done;           //czy zostaly wykonane wszystkie zadania

    @Embedded
    private Audit audit = new Audit();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group") //cascadeType = All -> zmiany w grupie wpływają na taski
    private Set<Task> tasks;        //zadania należące do grupy

    //Hibernate potrzebuje tego konstruktora do tworzenia encji i zapisywania ich w bazie
    TaskGroup() {}

    public int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(final boolean done) {
        this.done = done;
    }

     public Set<Task> getTasks() {
        return tasks;
    }

     void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

}
