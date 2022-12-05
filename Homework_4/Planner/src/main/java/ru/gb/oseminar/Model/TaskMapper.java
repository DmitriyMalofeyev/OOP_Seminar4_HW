package ru.gb.oseminar.Model;

public class TaskMapper {
    public String map(Task task) {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s", task.getTaskID(), task.getTask(), task.getPriority().name(),
                task.getDate(), task.getTime(), task.getDeadline(), task.getUser().getLastName(),
                task.getUser().getFirstName(), task.getUser().getPatronymic());
    }

    public Task map(String line) {
        String[] lines = line.split(",");
        Priority priority = Priority.NONE;
        switch (lines[2].toLowerCase()) {
            case "high":
                priority = Priority.HIGH;
                break;
            case "medium":
                priority = Priority.MEDIUM;
                break;
            case "low":
                priority = Priority.LOW;
                break;
        }
        if (priority == Priority.NONE) {
            throw new IllegalStateException("Приоритет не найден!");
        }
        return new Task(lines[0], lines[1], priority, lines[3], lines[4], lines[5], new User(lines[6], lines[7], lines[8]));
    }
}
