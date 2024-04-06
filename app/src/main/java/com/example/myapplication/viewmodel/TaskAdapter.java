package com.example.myapplication.viewmodel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.View.UpdateTaskActivity;
import com.example.myapplication.model.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private Context context;
    private final LayoutInflater inflater;
    private final List<Task> tasks;

    public TaskAdapter(Context context, List<Task> tasks) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.tasks = tasks;
    }
    @NonNull
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.task_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskAdapter.ViewHolder holder,@SuppressLint("RecyclerView") int position) {
        Task task = tasks.get(position);
        holder.nameView.setText(task.getName());
        holder.descriptionView.setText(task.getDescription());

        holder.layoutView.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View view) {
            Intent intent = new Intent(context, UpdateTaskActivity.class);
            intent.putExtra("name",tasks.get(position).getName());
            intent.putExtra("description", tasks.get(position).getDescription());
            intent.putExtra("id", tasks.get(position).getId());
            context.startActivity(intent);
        }
        });
    }


    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public  static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameView, descriptionView;

        ConstraintLayout layoutView;


        ViewHolder(@NonNull View view) {
            super(view);
            nameView = view.findViewById(R.id.task_name);
            descriptionView = view.findViewById(R.id.task_description);
            layoutView = view.findViewById(R.id.task_layout);
        }
    }
}
