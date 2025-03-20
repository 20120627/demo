package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Task;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.TaskDto;
import com.example.demo.repository.TaskRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    @Autowired
    private final TaskRepository taskRepository;

    @Override
    public ResponseDto<List<TaskDto>> getTaskList() {
        List<Task> TaskList = taskRepository.getTaskList(PageRequest.of(0, 10));
        List<TaskDto> TaskDtoList = TaskList.stream()
                .map(TaskDto::new)
                .collect(Collectors.toList());
        return new ResponseDto<>(TaskDtoList, "Success get all list of all Tasks!");
    }

    @Transactional
    @Override
    public ResponseDto<TaskDto> addTask(TaskDto taskDto) {
        Task task = Task.builder()
                .title(taskDto.getTitle())
                .description(taskDto.getDescription())
                .dueDate(taskDto.getDueDate())
                .priority(taskDto.getPriority())
                .build();
        var savedTask = taskRepository.save(task);
        TaskDto savedTaskDto = new TaskDto(savedTask);
        return new ResponseDto<>(savedTaskDto, "Success add new Task!");
    }

    @Transactional
    @Override
    public ResponseDto<TaskDto> updateTask(int id, TaskDto taskDto) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setDueDate(taskDto.getDueDate());
        task.setPriority(taskDto.getPriority());
        Task updatedTask = taskRepository.save(task);
        TaskDto updatedTaskDto = new TaskDto(updatedTask);
        return new ResponseDto<>(updatedTaskDto, "Task updated successfully");
    }

    @Transactional
    @Override
    public ResponseDto<TaskDto> deleteTask(int id) {
        taskRepository.deleteTask(id);
        return new ResponseDto<>(null, "Success delete Task!");
    }
}
