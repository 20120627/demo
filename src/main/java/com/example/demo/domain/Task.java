package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_id_gen")
    @SequenceGenerator(name = "task_id_gen", sequenceName = "task_task_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "priority")
    private int priority;
}