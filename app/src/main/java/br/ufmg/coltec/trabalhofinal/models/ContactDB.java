package br.ufmg.coltec.trabalhofinal.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ContactDB extends SQLiteOpenHelper {

    private static final String SECHEMA_NAME = "db.sqlite";
    private static final int SCHEMA_VERSION = 1;

    private static final String CONTACT_TABLE = "CREATE TABLE contact " +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name VARCHAR(100)," +
            "email VARCHAR(100) DEFAULT NULL," +
            "phone VARCHAR(12) DEFAULT NULL," +
            "linkedin VARCHAR(50) DEFAULT NULL," +
            "github VARCHAR(50) DEFAULT NULL)";

    public ContactDB(Context context){
        super(context, SECHEMA_NAME, null, SCHEMA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CONTACT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
