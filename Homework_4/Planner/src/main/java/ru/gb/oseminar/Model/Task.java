package ru.gb.oseminar.Model;

public class Task {
    private String taskID = "";
    private String task;
    private Priority priority;
    private String date = "";
    private String time = "";
    private String deadline;
    private User user;

    public Task(String task, Priority priority, String deadline, User user) {
        this.task = task;
        this.priority = priority;
        this.deadline = deadline;
        this.user = user;
    }
    public Task(String task, Priority priority, String date, String time, String deadline, User user) {
        this(task, priority, deadline, user);
        this.date = date;
        this.time = time;
    }

    public Task(String taskID, String task, Priority priority, String date, String time, String deadline, User user) {
        this(task, priority, date, time, deadline, user);
        this.taskID = taskID;
    }

    @Override
    public String toString() {
        return "ID задачи: " + taskID + "\n" +
                "Задача: " + task + "\n" +
                "Приоритет: " + priority + "\n" +
                "Дата создания: " + date + "\n" +
                "Время создания: " + time + "\n" +
                "Срок выполнения: " + deadline + "\n" +
                "Ответственный: " + user.getLastName() + " " +
                user.getFirstName() + " " +
                user.getPatronymic() + "\n";
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
