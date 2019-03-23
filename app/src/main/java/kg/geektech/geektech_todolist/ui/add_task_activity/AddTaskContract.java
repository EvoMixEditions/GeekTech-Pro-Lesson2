package kg.geektech.geektech_todolist.ui.add_task_activity;

import kg.geektech.geektech_todolist.data.TaskModel;

public interface AddTaskContract {

    interface View {
        void setToolbarTitle(String title);

        void fillTheFields(TaskModel taskModel);

        void showToast(String text);

        void closeView();
    }

    interface Presenter {
        void setTask(TaskModel taskModel);

        void clickSave(TaskModel taskModel);

        void detachView();
    }
}
