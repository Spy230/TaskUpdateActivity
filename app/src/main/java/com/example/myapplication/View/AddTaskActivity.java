package com.example.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.viewmodel.DataBaseHelper;

public class AddTaskActivity extends AppCompatActivity {

    // поля
    private EditText taskName, taskDescription;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);


        taskName = findViewById(R.id.task_name);
        taskDescription = findViewById(R.id.task_description);
        button = findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!TextUtils.isEmpty(taskName.getText().toString()) && !TextUtils.isEmpty(taskDescription.getText().toString())){

                    DataBaseHelper dataBaseHelper = new DataBaseHelper(getApplicationContext());
                    dataBaseHelper.addTasks(taskName.getText().toString(), taskDescription.getText().toString());


                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                    finish();

                } else {
                    Toast.makeText(getApplicationContext(), "Необходимо заполнить оба поля", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}