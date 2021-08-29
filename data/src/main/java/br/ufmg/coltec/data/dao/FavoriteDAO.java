package br.ufmg.coltec.data.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.ufmg.coltec.data.entities.Favorite;

public class FavoriteDAO {

    private static final String TABLE_NAME = "favorite";
    private static final String TABLE_COLUMN_ONE = "id_user";
    private static final String TABLE_COLUMN_TWO = "id_exercise";
    private SQLiteOpenHelper helper;
    public FavoriteDAO(SQLiteOpenHelper helper){
        this.helper = helper;
    }

    public void insert(Favorite f){
        SQLiteDatabase db = helper.getWritableDatabase();

        try{
            ContentValues params = new ContentValues();
            params.put("id_user", f.getIdUser());
            params.put("id_exercise", f.getIdExercise());
            db.insert(TABLE_NAME, null, params);
            Log.d("INSERT FAVORITE", "SUCESS");
            getAll();
        }catch (Exception error){
            error.printStackTrace();
            Log.d("INSERT FAVORITE", "FAILED");
        }finally {
            db.close();
        }
    }

    public void delete(Favorite f){
        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            db.delete(TABLE_NAME,
                    TABLE_COLUMN_ONE + " = ? AND " + TABLE_COLUMN_TWO + " = ?",
                    new String[] {f.getIdUser(), f.getIdExercise()+""});
            Log.d("DELETE FAVORITE", "SUCESS");
            getAll();
        }catch (Exception error){
            error.printStackTrace();
            Log.d("DELETE FAVORITE", "FAILED");
        }finally {
            db.close();
        }
    }

    public boolean isFavorited(Favorite f){
        boolean check = false;
        for(Favorite obj : getAll()){
            if(obj.getIdUser().equals(f.getIdUser()) && obj.getIdExercise().equals(f.getIdExercise())){
                check = true;
            }
        }
        return check;
    }

    public List<Favorite> getAll(){
        List<Favorite> favoriteList = new ArrayList<>();
        SQLiteDatabase db = this.helper.getReadableDatabase();

        try {
            Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);

            if(c.moveToFirst()){
                do{
                    String idUser = c.getString(c.getColumnIndex(TABLE_COLUMN_ONE));
                    String idExercise = c.getString(c.getColumnIndex(TABLE_COLUMN_TWO));
                    Log.d("FAVORITE_DATA: ",  "{ "+ idUser + " : " + idExercise + " }");

                    favoriteList.add(new Favorite(idUser, idExercise));
                }while (c.moveToNext());
            }
        }catch (Exception e){
            Log.d("FAVORITE_DATA", "ERROR: "+e);
        }finally {
            db.close();
        }

        return favoriteList;
    }

    public List<String> getByUser(String id){
        List<String> exerciseList = new ArrayList<>();
        for(Favorite f : getAll()){
            if(f.getIdUser().equals(id)){
                Log.d("USER_FAV", f.getIdExercise());
                exerciseList.add(f.getIdExercise());
            }
        }
        return exerciseList;
    }
}
