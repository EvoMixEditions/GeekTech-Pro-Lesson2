package kg.geektech.geektech_todolist.repository;

import java.util.List;

import kg.geektech.geektech_todolist.data.TaskModel;

public interface TasksRepository {

    void createTasks();

    List<TaskModel> getAllTasks();

    void deleteAllTasks();

    void addTask(TaskModel taskModel);

    void editTask(String id, TaskModel taskModel);
}
