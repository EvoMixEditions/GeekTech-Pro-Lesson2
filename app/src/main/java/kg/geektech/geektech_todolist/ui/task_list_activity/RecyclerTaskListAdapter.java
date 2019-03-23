package kg.geektech.geektech_todolist.ui.task_list_activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kg.geektech.geektech_todolist.R;
import kg.geektech.geektech_todolist.data.TaskModel;

public class RecyclerTaskListAdapter extends RecyclerView.Adapter<RecyclerTaskListAdapter.ViewHolder> {

    private List<TaskModel> taskModels;
    private static ClickListener clickListener;

    public RecyclerTaskListAdapter() {
        taskModels = new ArrayList<>();
    }

    public void setItems(List<TaskModel> taskModels) {
        this.taskModels = taskModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_task, viewGroup, false);
        return new RecyclerTaskListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final TaskModel taskModel = taskModels.get(position);

        viewHolder.title.setText(taskModel.getTitle());
        viewHolder.description.setText(taskModel.getDescription());
    }

    @Override
    public int getItemCount() {
        return taskModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView_title);
            description = itemView.findViewById(R.id.textView_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onItemClick(view, ViewHolder.this.getAdapterPosition());
                }
            });
        }
    }

    public void setClickListener(ClickListener clickListener) {
        RecyclerTaskListAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(View v, int position);
    }
}
