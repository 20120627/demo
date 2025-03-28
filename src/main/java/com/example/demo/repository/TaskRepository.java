package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.Task;

import jakarta.transaction.Transactional;


public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query("SELECT t FROM Task t")
    List<Task> getTaskList(Pageable pageable);

    @Modifying
    @Transactional
    @Query("Insert into Task (title, description, dueDate, priority) values (:title, :description, :dueDate, :priority)")
    void addTask(String title, String description, LocalDate dueDate, int priority);
   
    @Modifying
    @Transactional
    @Query("Update Task set title = :title, description = :description, dueDate = :dueDate, priority = :priority where id = :id")
    void updateTask(int id, String title, String description, LocalDate dueDate, int priority);

    @Modifying
    @Transactional
    @Query("Delete from Task where id = :id")
    void deleteTask(int id);

    @Modifying
    @Transactional
    @Query("UPDATE Task SET dependentTaskId = :dependentTaskId WHERE id = :id")
    void updateTaskDependency(@Param("id") int id, @Param("dependentTaskId") int dependentTaskId);

    @Modifying
    @Transactional
    @Query("UPDATE Task SET dependentTaskId = NULL WHERE id = :id")
    void deleteTaskDependency(@Param("id") int id);

    @Query("SELECT t FROM Task t WHERE t.dependentTaskId = :id")
    List<Task> findByDependentTaskId(@Param("id") int id);
}