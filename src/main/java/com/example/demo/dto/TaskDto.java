package com.example.demo.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.example.demo.domain.Task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskDto implements Serializable {
    int id;
    String title;
    String description;
    LocalDate dueDate;
    int priority;
    boolean completed;
    Integer dependentTaskId; // Changed from int to Integer

    public TaskDto(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.dueDate = task.getDueDate();
        this.priority = task.getPriority();
        this.completed = task.isCompleted();
        this.dependentTaskId = task.getDependentTaskId();
    }
}