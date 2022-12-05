package ru.gb.oseminar.Model;

import java.util.List;

public interface Repository {
    List<Task> getAllTasks();
    void CreateTask(Task task);
    void deleteTask(String taskID);
}