package ru.gb.oseminar.Model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RepositoryFile implements Repository {
    private TaskMapper taskMapper = new TaskMapper();
    private OperationFile fileOperation;

    public RepositoryFile(OperationFile fileOperation) {
        this.fileOperation = fileOperation;
    }

    @Override
    public List<Task> getAllTasks() {
        List<String> lines = fileOperation.readAllLines();
        List<Task> tasks = new ArrayList<>();
        for (String line : lines) {
            tasks.add(taskMapper.map(line));
        }
        return tasks;
    }

    @Override
    public void CreateTask(Task task) {
        List<Task> tasks = getAllTasks();
        int ID = getMaxID(tasks);
        ID ++;
        LocalDate localDate = LocalDate.now();
        String date = localDate.getDayOfMonth() + "." +
                localDate.getMonthValue() + "." +
                localDate.getYear();

        LocalTime localTime = LocalTime.now();
        String time = localTime.getHour() + ":" +
                localTime.getMinute() + ":" +
                localTime.getSecond();
        task.setTaskID(String.valueOf(ID));
        task.setDate(date);
        task.setTime(time);
        saveTask(task, tasks);
    }

    @Override
    public void deleteTask(String taskID) {
        List<Task> tasks = getAllTasks();
        tasks.remove(findTask(taskID, tasks));
        saveTasks(tasks);
    }

    private Task findTask(String taskID, List<Task> tasks) {
        for (Task task : tasks) {
            if (task.getTaskID().equals(taskID)) {
                return task;
            }
        }
        throw new IllegalStateException("Ничего не найдено!");
    }

    private Integer getMaxID(List<Task> tasks) {
        int ID = 0;
        for (Task task : tasks) {
            if (Integer.parseInt(task.getTaskID()) > ID) {
                ID = Integer.parseInt(task.getTaskID());
            }
        }
        return ID;
    }

    private void saveTask(Task task, List<Task> tasks) {
        tasks.add(task);
        saveTasks(tasks);
    }

    private void saveTasks(List<Task> tasks) {
        List<String> lines = new ArrayList<>();
        for (Task task : tasks) {
            lines.add(taskMapper.map(task));
        }
        fileOperation.saveAllLines(lines);
    }
}
