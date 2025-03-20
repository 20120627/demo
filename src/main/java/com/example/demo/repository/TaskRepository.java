package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.Task;


public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query("SELECT t FROM Task t")
    List<Task> getTaskList(Pageable pageable);

    @Query("Insert into Task (title, description, dueDate, priority) values (:title, :description, :dueDate, :priority)")
    void addTask(String title, String description, LocalDate dueDate, int priority);
}