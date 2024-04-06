package com.example.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.model.Task;
import com.example.myapplication.viewmodel.DataBaseHelper;
import com.example.myapplication.viewmodel.TaskAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private Button button;
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private DataBaseHelper dataBaseHelper;

    List<Task> tasks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // привязка разметки к полям
        recyclerView = findViewById(R.id.recyclerview);
        button = findViewById(R.id.button);

        // создание объекта работы с базой данных
        dataBaseHelper = new DataBaseHelper(getApplicationContext());
        // инициализация контейнера задач
        fetchAllTasks(tasks);


        taskAdapter = new TaskAdapter(this, tasks);


        recyclerView.setAdapter(taskAdapter);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), AddTaskActivity.class));
            }
        });
    }

    public void fetchAllTasks(List<Task> taskList){

        Cursor cursor = dataBaseHelper.readTasks();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Задачи отсутствуют", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){

                taskList.add(new Task(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
            }
        }
    }
}