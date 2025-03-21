package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        List<Task> taskList = taskRepository.getTaskList(PageRequest.of(0, 10));
        List<TaskDto> taskDtoList = taskList.stream()
                .map(TaskDto::new)
                .collect(Collectors.toList());
        return new ResponseDto<>(taskDtoList, "Success get all list of all Tasks!");
    }

    @Transactional
    @Override
    public ResponseDto<TaskDto> addTask(TaskDto taskDto) {
        Task task = Task.builder()
                .title(taskDto.getTitle())
                .description(taskDto.getDescription())
                .dueDate(taskDto.getDueDate())
                .priority(taskDto.getPriority())
                .completed(taskDto.isCompleted())
                .dependentTaskId(taskDto.getDependentTaskId())
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
        task.setCompleted(taskDto.isCompleted());
        task.setDependentTaskId(taskDto.getDependentTaskId());
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

    @Transactional
    @Override
    public ResponseDto<TaskDto> updateTaskDependency(int id, Integer dependentTaskId) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            taskRepository.updateTaskDependency(id, dependentTaskId);
            Task updatedTask = taskRepository.findById(id).get();
            TaskDto taskDto = new TaskDto(updatedTask);
            return new ResponseDto<>(taskDto, "Task dependency updated successfully");
        } else {
            return new ResponseDto<>(null, "Task with id not found");
        }
    }
    @Transactional
    @Override
    public ResponseDto<TaskDto> deleteTaskDependency(int id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            taskRepository.deleteTaskDependency(id);
            Task updatedTask = taskRepository.findById(id).get();
            TaskDto taskDto = new TaskDto(updatedTask);
            return new ResponseDto<>(taskDto, "Task dependency deleted successfully");
        } else {
            return new ResponseDto<>(null, "Task with id not found");
        }
    }

    @Transactional
    @Override
    public ResponseDto<List<TaskDto>> getAllDependencies(int id) {
        List<TaskDto> dependencies = new ArrayList<>();
        fetchDependencies(id, dependencies);
        fetchDependents(id, dependencies);
        return new ResponseDto<>(dependencies, "Success get all dependencies of Task!");
    }

    private void fetchDependencies(int id, List<TaskDto> dependencies) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            if (task.getDependentTaskId() != null) {
                Task dependentTask = taskRepository.findById(task.getDependentTaskId()).orElse(null);
                if (dependentTask != null) {
                    TaskDto dependentTaskDto = new TaskDto(dependentTask);
                    dependencies.add(dependentTaskDto);
                    fetchDependencies(dependentTask.getId(), dependencies);
                }
            }
        }
    }

    private void fetchDependents(int id, List<TaskDto> dependencies) {
        List<Task> dependentTasks = taskRepository.findByDependentTaskId(id);
        for (Task dependentTask : dependentTasks) {
            TaskDto dependentTaskDto = new TaskDto(dependentTask);
            dependencies.add(dependentTaskDto);
            fetchDependents(dependentTask.getId(), dependencies);
        }
    }
}
