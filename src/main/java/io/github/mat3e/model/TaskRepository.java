package io.github.mat3e.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "todos", collectionResourceRel = "todos")
interface TaskRepository extends JpaRepository<Task, Integer> {
    @Override
    @RestResource(exported = false)     //zablokowanie usuwania zadań
    void deleteById(Integer integer);

    @Override
    @RestResource(exported = false)
    void delete(Task entity);
}
