package com.example.demo.controller;

import com.example.demo.service.TaskService;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.TaskDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/all")
    public ResponseEntity<ResponseDto<List<TaskDto>>> getTaskList() {
        ResponseDto<List<TaskDto>> response = taskService.getTaskList();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PostMapping("/add")
    public ResponseEntity<ResponseDto<TaskDto>> addTask(@RequestBody TaskDto taskDto) {
        ResponseDto<TaskDto> response = taskService.addTask(taskDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}