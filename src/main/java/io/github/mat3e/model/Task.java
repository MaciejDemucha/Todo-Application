package io.github.mat3e.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tasks")
class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Task must not be empty.")
    private String description;     //opis zadania
    private boolean done;           //czy zostalo wykonane

    Task() {

    }

    public int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return description;
    }

    void setDesc(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    void setDone(boolean done) {
        this.done = done;
    }
}