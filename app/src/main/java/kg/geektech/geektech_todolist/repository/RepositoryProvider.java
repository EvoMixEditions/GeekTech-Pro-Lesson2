package kg.geektech.geektech_todolist.repository;

import android.support.annotation.MainThread;

public class RepositoryProvider {

    private static TasksRepository tasksRepository;

    public RepositoryProvider() {
    }

    public static TasksRepository provideTasksRepository() {
        if (tasksRepository == null)
            tasksRepository = new TasksRepositoryImpl();

        return tasksRepository;
    }

    @MainThread
    public static void init() {
        tasksRepository = new TasksRepositoryImpl();
    }
}
