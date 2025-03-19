package com.example.demo.repository;

import com.example.demo.domain.Task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Repository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t")
    List<Task> getTaskList(Pageable pageable);

    @Query("Insert into Task (title, description, dueDate, priority) values (:title, :description, :dueDate, :priority)")
    void addTask(String title, String description, LocalDate dueDate, int priority);
}