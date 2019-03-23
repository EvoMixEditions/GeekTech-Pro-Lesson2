package kg.geektech.geektech_todolist.ui.task_list_activity;

import java.util.List;

import kg.geektech.geektech_todolist.data.TaskModel;

public interface TaskListContract {

    interface View {
        void showTasks(List<TaskModel> taskModels);

        void showText(String text);

        void launchAddTaskActivity();

        void launchEditTaskActivity(TaskModel taskModel);
    }

    interface Presenter {
        void getTasks();

        void deleteAllTasks();

        void detachView();
    }
}
