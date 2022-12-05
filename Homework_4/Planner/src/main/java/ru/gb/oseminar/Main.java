package ru.gb.oseminar;

import ru.gb.oseminar.Controller.TaskController;
import ru.gb.oseminar.Model.FileOperation;
import ru.gb.oseminar.Model.OperationFile;
import ru.gb.oseminar.Model.Repository;
import ru.gb.oseminar.Model.RepositoryFile;
import ru.gb.oseminar.view.ViewTask;

public class Main {
    public static void main(String[] args) {
        FileOperation fileOperation = new FileOperation("tasks.csv");
        Repository repository = new RepositoryFile(fileOperation);
        TaskController controller = new TaskController(repository);
        ViewTask view = new ViewTask(controller);
        view.run();
    }
}