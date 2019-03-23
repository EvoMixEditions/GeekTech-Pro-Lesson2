package kg.geektech.geektech_todolist.ui.task_list_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import kg.geektech.geektech_todolist.R;
import kg.geektech.geektech_todolist.data.TaskModel;
import kg.geektech.geektech_todolist.ui.add_task_activity.AddTaskActivity;

public class TaskListActivity extends AppCompatActivity implements TaskListContract.View {

    private TaskListContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new TaskListPresenter(this);

        initView();
    }

    public void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchAddTaskActivity();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getTasks();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_delete_all) {
            presenter.deleteAllTasks();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showTasks(List<TaskModel> taskModels) {
        RecyclerView recyclerView = findViewById(R.id.recycler_taskList);
        RecyclerTaskListAdapter adapter = new RecyclerTaskListAdapter();
        adapter.setItems(taskModels);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);

        adapter.setClickListener(new RecyclerTaskListAdapter.ClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                final TaskModel taskModel = taskModels.get(position);
                launchEditTaskActivity(taskModel);
            }
        });
    }

    @Override
    public void showText(String text) {
        Snackbar.make(findViewById(R.id.fab), text, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void launchAddTaskActivity() {
        Intent intent = new Intent(this, AddTaskActivity.class);
        startActivity(intent);
    }

    @Override
    public void launchEditTaskActivity(TaskModel taskModel) {
        Intent intent = new Intent(this, AddTaskActivity.class);
        intent.putExtra("TASK_MODEL", taskModel);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
