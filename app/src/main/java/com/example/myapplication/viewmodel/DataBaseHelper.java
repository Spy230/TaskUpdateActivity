package com.example.myapplication.viewmodel;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {


    private Context context;

    private static final String DatabaseName = "MyPlanner";
    private static final int DatabaseVersion = 1;

    private static final String tableName = "myTasks";
    private static final String columnId = "id";
    private static final String columnName = "name";
    private static final String columnDescription = "description";

    // конструктор
    public DataBaseHelper(@Nullable Context context) {

        super(context, DatabaseName,null, DatabaseVersion);
        this.context = context;
    }

    // метод создания рабочей таблицы в БД
    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + tableName + " (" + columnId + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + columnName + " TEXT, " + columnDescription + " Text);";
        db.execSQL(query);
    }

    // метод обновления рабочей таблицы в БД
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ tableName);

        onCreate(db);
    }


    public void addTasks(String name, String description) {


        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues cv = new ContentValues();

        cv.put(columnName,name);
        cv.put(columnDescription,description);

        // добавление новой записи в БД
        long resultValue = db.insert(tableName,null, cv);

        if (resultValue == -1){
            Toast.makeText(context, "Данные в БД не добавлены", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Данные в БД успешно добавлены", Toast.LENGTH_SHORT).show();
        }
    }


    public Cursor readTasks(){


        String query = "SELECT * FROM " +  tableName;


        SQLiteDatabase database= this.getReadableDatabase();

        Cursor cursor = null;

        if (database != null){
            cursor = database.rawQuery(query,null);
        }


        return  cursor;
    }


    public void deleteAllTasks() {


        SQLiteDatabase database = this.getWritableDatabase();

        String query = "DELETE FROM " + tableName;
        database.execSQL(query);

    }


    public void updateNotes(String name, String description , String id){


        SQLiteDatabase database =  this.getWritableDatabase();


        ContentValues cv = new ContentValues();

        cv.put(columnName,name);
        cv.put(columnDescription, description);


        long result  = database.update(tableName, cv,"id=?", new String[]{id});

        if (result == -1) {
            Toast.makeText(context, "Обновление не получилось", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Обновление прошло успешно", Toast.LENGTH_SHORT).show();
        }
    }


    public  void  deleteSingleItem(String id){


        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete(tableName,"id=?", new String[]{id});

        if (result == -1) {
            Toast.makeText(context, "Запись не удалена", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Запись успешно удалена", Toast.LENGTH_SHORT).show();
        }
    }
}

