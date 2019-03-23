package kg.geektech.geektech_todolist.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import kg.geektech.geektech_todolist.data.TaskModel;

public class TasksRepositoryImpl implements TasksRepository {

    private HashMap<String, TaskModel> cashedTasks = new HashMap<>();

    @Override
    public void createTasks() {
        cashedTasks.put("1", new TaskModel("1", "Task 1", "Description"));
        cashedTasks.put("2", new TaskModel("2", "Task 2", "Description"));
        cashedTasks.put("3", new TaskModel("3", "Task 3", "Description"));
        cashedTasks.put("4", new TaskModel("4", "Task 4", "Description"));
        cashedTasks.put("5", new TaskModel("5", "Task 5", "Description"));
        cashedTasks.put("6", new TaskModel("6", "Task 6", "Description"));
        cashedTasks.put("7", new TaskModel("7", "Task 7", "Description"));
        cashedTasks.put("8", new TaskModel("8", "Task 8", "Description"));
        cashedTasks.put("9", new TaskModel("9", "Task 9", "Description"));
        cashedTasks.put("10", new TaskModel("10", "Task 10", "Description"));
        cashedTasks.put("11", new TaskModel("11", "Task 11", "Description"));
        cashedTasks.put("12", new TaskModel("12", "Task 12", "Description"));
        cashedTasks.put("13", new TaskModel("13", "Task 13", "Description"));
    }

    @Override
    public List<TaskModel> getAllTasks() {
        return new ArrayList<>(cashedTasks.values());
    }

    @Override
    public void addTask(TaskModel taskModel) {
        taskModel.setId(UUID.randomUUID().toString());
        cashedTasks.put(taskModel.getId(), taskModel);
    }

    @Override
    public void editTask(String ID, TaskModel taskModel) {
        taskModel.setId(ID);
        cashedTasks.put(ID, taskModel);
    }

    @Override
    public void deleteAllTasks() {
        cashedTasks.clear();
    }
}
