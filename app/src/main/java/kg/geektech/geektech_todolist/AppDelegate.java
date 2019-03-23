package kg.geektech.geektech_todolist;

import android.app.Application;

import kg.geektech.geektech_todolist.repository.RepositoryProvider;

public class AppDelegate extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RepositoryProvider.init();
        RepositoryProvider.provideTasksRepository().createTasks();
    }
}
