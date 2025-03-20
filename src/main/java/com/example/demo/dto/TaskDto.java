package com.example.demo.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.example.demo.domain.Task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.Data;

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

    public TaskDto(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.dueDate = task.getDueDate();
        this.priority = task.getPriority();
    }
}