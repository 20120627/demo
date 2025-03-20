package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.TaskDto;

public interface TaskService {
    ResponseDto<List<TaskDto>> getTaskList();
    ResponseDto<TaskDto> addTask(TaskDto taskDto);
    ResponseDto<TaskDto> updateTask(int id,TaskDto taskDto);
    ResponseDto<TaskDto> deleteTask(int id);
}
