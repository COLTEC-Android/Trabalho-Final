package br.ufmg.coltec.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ApplicationDB extends SQLiteOpenHelper {
    private static ApplicationDB instance;
    private static Context context;
    private static final String SCHEMA_NAME = "calisthenics";
    private static final int SCHEMA_VERSION = 1;
    private static final String USER_TABLE = "CREATE TABLE user (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name VARCHAR(45), " +
            "email VARCHAR(50), " +
            "password VARCHAR(20)" +
            ")";
    private static final String EXERCISE_TABLE = "CREATE TABLE exercise (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name VARCHAR(45), " +
            "description VARCHAR(200), " +
            "type VARCHAR(15)," +
            "image VARCHAR(50) " +
            ")";
    private static final String FAVORITE = "CREATE TABLE favorite (" +
            "id_user INTEGER," +
            "id_exercise INTEGER" +
            ")";

    private boolean isCreating = false;
    private SQLiteDatabase currentDB = null;

    private ApplicationDB(Context context){
        super(context, SCHEMA_NAME, null, SCHEMA_VERSION);
        this.context = context;
    }

    public static ApplicationDB getInstance(Context context) {
        if (instance == null) {
            instance = new ApplicationDB(context);
        }
        else if(!instance.getContext().equals(context))
        {
            instance = new ApplicationDB(context);
        }
        return instance;
    }

    public Context getContext(){
        return context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("ON_CREATE", "BANCO CRIADO");
        sqLiteDatabase.execSQL(USER_TABLE);
        sqLiteDatabase.execSQL(EXERCISE_TABLE);
        sqLiteDatabase.execSQL(FAVORITE);

        // Add default value for all tables
        isCreating = true;
        currentDB = sqLiteDatabase;
        new GenerateAllDefaultData(this); //generate All Default Data
        // release var
        isCreating = false;
        currentDB = null;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        // TODO Auto-generated method stub
        if(isCreating && currentDB != null){
            return currentDB;
        }
        return super.getWritableDatabase();
    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        // TODO Auto-generated method stub
        if(isCreating && currentDB != null){
            return currentDB;
        }
        return super.getReadableDatabase();
    }
}
