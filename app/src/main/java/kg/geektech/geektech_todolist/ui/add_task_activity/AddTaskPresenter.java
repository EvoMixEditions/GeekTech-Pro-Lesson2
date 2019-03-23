package kg.geektech.geektech_todolist.ui.add_task_activity;

import android.support.annotation.NonNull;

import kg.geektech.geektech_todolist.data.TaskModel;
import kg.geektech.geektech_todolist.repository.RepositoryProvider;

public class AddTaskPresenter implements AddTaskContract.Presenter {

    private AddTaskContract.View view;
    private TaskModel TASK_MODEL;
    private boolean isNewTask;

    public AddTaskPresenter(AddTaskContract.View view) {
        this.view = view;
    }

    @Override
    public void setTask(TaskModel task) {
        this.TASK_MODEL = task;
        if (TASK_MODEL != null) {
            isNewTask = false;
            view.setToolbarTitle("Edit Task");
            view.fillTheFields(TASK_MODEL);
        } else {
            isNewTask = true;
            view.setToolbarTitle("Add new Task");
        }
    }

    @Override
    public void clickSave(TaskModel taskModel) {
        if (checkTask(taskModel)) {
            if (isNewTask)
                RepositoryProvider
                        .provideTasksRepository()
                        .addTask(taskModel);
            else
                RepositoryProvider
                        .provideTasksRepository()
                        .editTask(TASK_MODEL.getId(), taskModel);

            closeView();

        } else
            view.showToast("Please, fill all the fields");
    }

    @Override
    public void detachView() {
        view = null;
    }

    public void closeView() {
        if (isNewTask) {
            view.showToast("New Task has been saved");
            view.closeView();
        } else {
            view.showToast("Task changes has been saved");
            view.closeView();
        }
    }

    public boolean checkTask(@NonNull TaskModel taskModel) {
        if (taskModel.getTitle().isEmpty())
            return false;

        if (taskModel.getDescription().isEmpty())
            return false;

        return true;
    }

//    public boolean checkTask(@NonNull TaskModel taskModel) {
//        if (taskModel.getTitle().isEmpty() || taskModel.getTitle().length() == 0)
//            return false;
//
//        if (taskModel.getDescription().isEmpty() || taskModel.getDescription().length() == 0)
//            return false;
//
//        return true;
//    }
}
