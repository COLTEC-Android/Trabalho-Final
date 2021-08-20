package br.ufmg.coltec.trabalhofinal.controller.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.ufmg.coltec.trabalhofinal.models.Exercise;

public class ExerciseDAO {
    private static final String TABLE_NAME = "exercise";
    private static final String TABLE_COLUMN_ONE = "name";
    private static final String TABLE_COLUMN_TWO = "description";
    private static final String TABLE_COLUMN_THREE = "type";
    private static final String TABLE_COLUMN_FOUR = "image";

    private SQLiteOpenHelper helper;

    public ExerciseDAO(SQLiteOpenHelper helper){
        this.helper = helper;
    }

    public void insert(Exercise e){
        SQLiteDatabase db = helper.getWritableDatabase();

        try{
            ContentValues params = new ContentValues();
            params.put(TABLE_COLUMN_ONE, e.getName());
            params.put(TABLE_COLUMN_TWO, e.getDescription());
            params.put(TABLE_COLUMN_THREE, e.getType());
            params.put(TABLE_COLUMN_FOUR, e.getImage());

            db.insert(TABLE_NAME, null, params);
            Log.d("INSERT EXERCISE", "SUCESS");
        }catch (Exception error){
            error.printStackTrace();
            Log.d("INSERT EXERCISE", "FAILED");
        }finally {
            db.close();
        }
    }

    public List<Exercise> getAll(){
        List<Exercise> exerciseList = new ArrayList<>();
        SQLiteDatabase db = this.helper.getReadableDatabase();

        try {
            Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);

            if(c.moveToFirst()){
                do{
                    String name = c.getString(c.getColumnIndex(TABLE_COLUMN_ONE));
                    String description = c.getString(c.getColumnIndex(TABLE_COLUMN_TWO));
                    byte[] image = c.getBlob(4);
                    String type = c.getString(c.getColumnIndex(TABLE_COLUMN_THREE));
                    Log.d("EXERCISE_DATA: ",  "{ "+ name + " : " + description + " }");

                    exerciseList.add(new Exercise(name, description, image, type));
                }while (c.moveToNext());
            }
        }catch (Exception e){
            Log.d("EXERCISE_DATA", "ERROR: "+e);
        }finally {
            db.close();
        }

        return exerciseList;
    }

    public Exercise getByName(String name){
        Exercise exercise = null;
        SQLiteDatabase db = this.helper.getReadableDatabase();
        try{
            Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + TABLE_COLUMN_ONE + " = ?", new String[] {name});
            if (c.moveToFirst()){
                do {
                    String n = c.getString(c.getColumnIndex(TABLE_COLUMN_ONE));
                    String description = c.getString(c.getColumnIndex(TABLE_COLUMN_TWO));
                    byte[] image = c.getBlob(4);
                    String type = c.getString(c.getColumnIndex(TABLE_COLUMN_THREE));
                    Log.d("EXERCISE_DATA: ",  "{ "+ n + " : " + description + " }");
                    exercise = new Exercise(n, description, image, type);
                } while(c.moveToNext());
            }
        }catch (Exception e){
            Log.d("EXERCISE_DATA", "ERROR: "+e);
        }finally {
            db.close();
        }
        return exercise;
    }

    public List<Exercise> getSpecificList(List<String> exerciseName){
        List<Exercise> exerciseList = new ArrayList<>();

        for(Exercise e : getAll()){
           for(String name : exerciseName){
               if(e.getName().equals(name)){
                   exerciseList.add(e);
                   Log.d("SPECIFIC_LIST", e.getName());
               }
           }
        }

        return exerciseList;
    }
}
