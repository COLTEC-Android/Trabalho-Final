package br.ufmg.coltec.trabalhofinal.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FavoritesDB extends SQLiteOpenHelper {

    private static String FAVORITES_DB = "db.sqlite";
    private static String TABLE_NAME = "favorites";
    private static final int DB_VERSION = 1;
    private static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "movie_id TEXT, " +
                    "title TEXT, " +
                    "original_title TEXT, " +
                    "original_language TEXT, " +
                    "overview TEXT, " +
                    "release_date TEXT, " +
                    "poster_path TEXT, " +
                    "vote_average TEXT " +
                    ")";

    private static FavoritesDB instance;


   private FavoritesDB(Context context){
        super (context, FAVORITES_DB, null, DB_VERSION);
    }

    public static synchronized FavoritesDB getInstance(Context context){
       if(instance == null){
           instance = new FavoritesDB(context.getApplicationContext());
       }
       return instance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(SQL_CREATE);

        }catch (SQLException e){
            Log.e("DB_ERROR", e.getLocalizedMessage() );
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
