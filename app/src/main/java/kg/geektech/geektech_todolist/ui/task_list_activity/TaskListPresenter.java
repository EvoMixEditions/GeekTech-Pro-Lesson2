package kg.geektech.geektech_todolist.ui.task_list_activity;

import java.util.List;

import kg.geektech.geektech_todolist.data.TaskModel;
import kg.geektech.geektech_todolist.repository.RepositoryProvider;

public class TaskListPresenter implements TaskListContract.Presenter {

    private TaskListContract.View view;

    public TaskListPresenter(TaskListContract.View view) {
        this.view = view;
    }

    @Override
    public void getTasks() {
        List<TaskModel> taskModels = RepositoryProvider
                .provideTasksRepository()
                .getAllTasks();

        view.showTasks(taskModels);
    }

    @Override
    public void deleteAllTasks() {
        RepositoryProvider
                .provideTasksRepository()
                .deleteAllTasks();

        getTasks();
        view.showText("All tasks has been deleted");
    }

    @Override
    public void detachView() {
        view = null;
    }
}
