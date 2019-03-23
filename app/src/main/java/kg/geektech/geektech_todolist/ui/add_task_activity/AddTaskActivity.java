package kg.geektech.geektech_todolist.ui.add_task_activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import kg.geektech.geektech_todolist.R;
import kg.geektech.geektech_todolist.data.TaskModel;

public class AddTaskActivity extends AppCompatActivity implements AddTaskContract.View {

    private AddTaskContract.Presenter presenter;
    private EditText editText_title;
    private EditText editText_description;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        presenter = new AddTaskPresenter(this);

        initView();
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar_add);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);

        editText_title = findViewById(R.id.editText_title);
        editText_description = findViewById(R.id.editText_description);

        FloatingActionButton fab = findViewById(R.id.fab_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaskModel taskModel = new TaskModel();
                taskModel.setTitle(editText_title.getText().toString());
                taskModel.setDescription(editText_description.getText().toString());

                presenter.clickSave(taskModel);
            }
        });

        TaskModel taskModel = (TaskModel) getIntent().getSerializableExtra("TASK_MODEL");
        presenter.setTask(taskModel);
    }

    @Override
    public void setToolbarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void fillTheFields(TaskModel taskModel) {
        editText_title.setText(taskModel.getTitle());
        editText_description.setText(taskModel.getDescription());
    }


    @Override
    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void closeView() {
        presenter.detachView();
        finish();
    }
}
