package ru.gb.oseminar.Controller;

import ru.gb.oseminar.Model.Priority;
import ru.gb.oseminar.Model.Repository;
import ru.gb.oseminar.Model.Task;

import java.util.List;

public class TaskController {
    private final Repository repository;

    public TaskController(Repository repository) {
        this.repository = repository;
    }

    public void saveTask(Task task) {
        validateTaskData(task);
        repository.CreateTask(task);
    }

    public Task readTask(String taskID) throws Exception {
        List<Task> tasks = repository.getAllTasks();
        for (Task task : tasks) {
            if (task.getTaskID().equals(taskID)) {
                return task;
            }
        }
        throw new Exception("Ничего не найдено!");
    }

    public List<Task> readTasks() {
        return repository.getAllTasks();
    }

    public void validateTaskData(Task task) {
        if (task.getTask().isEmpty() || task.getDeadline().isEmpty() || task.getPriority() == Priority.NONE ||
        task.getUser().getLastName().isEmpty() || task.getUser().getFirstName().isEmpty() ||
        task.getUser().getPatronymic().isEmpty()) {
            throw new IllegalStateException("Поля пусты!");
        }
    }

    public void deleteTask(String taskID) {
        repository.deleteTask(taskID);
    }
}