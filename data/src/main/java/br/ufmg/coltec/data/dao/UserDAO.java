package br.ufmg.coltec.data.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.ufmg.coltec.data.entities.User;


public class UserDAO {

    private static final String TABLE_NAME = "user";
    private static final String TABLE_COLUMN_ONE = "name";
    private static final String TABLE_COLUMN_TWO = "email";
    private static final String TABLE_COLUMN_THREE = "password";
    private SQLiteOpenHelper helper;

    public UserDAO(SQLiteOpenHelper helper){
        this.helper = helper;
    }

    public void insert(User u){
        SQLiteDatabase db = helper.getWritableDatabase();

        try{
            ContentValues params = new ContentValues();
            params.put(TABLE_COLUMN_ONE, u.getName());
            params.put(TABLE_COLUMN_TWO, u.getEmail());
            params.put(TABLE_COLUMN_THREE, u.getPassword());

            db.insert(TABLE_NAME, null, params);
            Log.d("INSERT USER", "SUCESS");
        }catch (Exception e){
            e.printStackTrace();
            Log.d("INSERT USER", "FAILED");
        }finally {
            db.close();
        }
    }

    public void delete(User u){
        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            db.delete(TABLE_NAME,
                    TABLE_COLUMN_TWO + " = ?",
                    new String[] {u.getEmail()});
            Log.d("DELETE_USER", "SUCESS");
            getAll();
        }catch (Exception error){
            error.printStackTrace();
            Log.d("DELETE_USER", "FAILED");
        }finally {
            db.close();
        }
    }

    public List<User> getAll(){
        List<User> userList = new ArrayList<>();
        SQLiteDatabase db = this.helper.getReadableDatabase();

        try {
            Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);

            if(c.moveToFirst()){
                do{
                    String name = c.getString(c.getColumnIndex(TABLE_COLUMN_ONE));
                    String email = c.getString(c.getColumnIndex(TABLE_COLUMN_TWO));
                    String password = c.getString(c.getColumnIndex(TABLE_COLUMN_THREE));

                    Log.d("USER_DATA: ", name + " : " + email);

                    userList.add(new User(name, email, password));
                }while (c.moveToNext());
            }
        }catch (Exception e){
            Log.d("USER_DATA", "ERROR: "+e);
        }finally {
            db.close();
        }

        return userList;
    }

    public boolean validateRegister(String email){
        Log.d("USER_EMAIL", email);
        boolean validate = true;

        for(User u : getAll()){
            if (u.getEmail().equals(email.toLowerCase())){
                Log.d("USER_CHECK", u.getEmail());
                Log.d("USER_VER", String.valueOf(u.getEmail().equals(email.toLowerCase())));
                validate = false;
            }
        }

        return validate;
    }

    public User validateUser(String email, String password){
        User user = null;

        for(User u: getAll()){
            if(u.getEmail().equals(email) && u.getPassword().equals(password)){
                user = u;
            }
        }

        return user;
    }
}
