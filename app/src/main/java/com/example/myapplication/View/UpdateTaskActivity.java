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

public class UpdateTaskActivity extends AppCompatActivity {

    // поля
    private EditText taskName, taskDescription;
    private Button buttonUpdate, buttonDelete;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);


        taskName = findViewById(R.id.task_name);
        taskDescription = findViewById(R.id.task_description);
        buttonUpdate = findViewById(R.id.button_update);
        buttonDelete = findViewById(R.id.button_delete);


        Intent intent = getIntent();

        taskName.setText(intent.getStringExtra("name"));
        taskDescription.setText(intent.getStringExtra("description"));
        id = intent.getStringExtra("id");


        buttonUpdate.setOnClickListener(listener);
        buttonDelete.setOnClickListener(listener);
    }

    // определение слушателя
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String name = taskName.getText().toString();
            String description = taskDescription.getText().toString();
            if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(description)) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(getApplicationContext());

                if (view.getId() == R.id.button_update) {
                    // обновление задачи
                    dataBaseHelper.updateNotes(name, description, id);
                } else if (view.getId() == R.id.button_delete) {
                    // удаление задачи
                    dataBaseHelper.deleteSingleItem(id); // удаление записи БД по id
                }

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Данные не обновились", Toast.LENGTH_SHORT).show();
            }
        }
    };

}