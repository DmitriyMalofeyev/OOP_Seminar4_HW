package ru.gb.oseminar.view;

import ru.gb.oseminar.Controller.TaskController;
import ru.gb.oseminar.Model.Priority;
import ru.gb.oseminar.Model.Task;
import ru.gb.oseminar.Model.User;

import java.util.Scanner;

public class ViewTask {
    private TaskController taskController;

    public ViewTask(TaskController taskController) {
        this.taskController = taskController;
    }

    public void run() {
        Commands com = Commands.NONE;
        String lastName = prompt("Введите фамилию ответственного за задачу: ");
        String firstName = prompt("Введите имя ответственного за задачу: ");
        String patronymic = prompt("Введите отчество ответственного за задачу: ");
        if (lastName.isEmpty() || firstName.isEmpty() || patronymic.isEmpty()) {
            throw new IllegalStateException("Поля данных пусты");
        }
        User user = new User(lastName, firstName, patronymic);
        while (true) {
            try {
                String command = prompt("Введите команду или HELP для списка команд): ");
                com = Commands.valueOf(command.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Такой команды нет");
                continue;
            }
            if (com == Commands.EXIT)   return;
            switch (com) {
                case CREATE:
                    try {
                        String task = prompt("Задача: ");
                        Priority priority = Priority.NONE;
                        try {
                            String input = prompt("Приоритет (high/medium/low): ");
                            priority = Priority.valueOf(input.toUpperCase());
                        } catch (IllegalArgumentException e) {
                            System.out.println("Приоритет неизвестен");
                            continue;
                        }
                        String deadline = prompt("Срок выполнения: ");
                        taskController.saveTask(new Task(task, priority, deadline, user));
                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                    break;
                case READ:
                    String ID = prompt("ID задачи: ");
                    try {
                        Task task = taskController.readTask(ID);
                        System.out.println(task);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                    break;
                case LIST:
                    taskController.readTasks().forEach(System.out::println);
                    break;
                case DELETE:
                    String taskID = prompt("ID задачи: ");
                    try {
                        taskController.deleteTask(taskID);
                        System.out.println("OK");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                    break;
                case HELP:
                    System.out.println("Список команд:");
                    System.out.println("CREATE - создание новой задачи");
                    System.out.println("READ - вывод задачи по ID");
                    System.out.println("LIST - вывод списка всех задач");
                    System.out.println("DELETE - удаление задачи по ID");
                    System.out.println("EXIT - выход");
                    break;
            }
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}