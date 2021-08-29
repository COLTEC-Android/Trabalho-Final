package br.ufmg.coltec.trabalhofinal.classesintermediarias;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.ufmg.coltec.classesdemodelo.Book;

public class BookDAO extends SQLiteOpenHelper {

    private static String DB_NAME = "BookDAO.sqlite";
    private static final int DB_VERSION = 1;
    private static final String SCRIPT_CREATE =
            "CREATE TABLE books (" +
                    "_id integer primary key autoincrement," +
                    "photoId integer," +
                    "title text," +
                    "author text," +
                    "genre text," +
                    "publisher text" +
                    ")";

    public BookDAO(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SCRIPT_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS books");
        onCreate(db);
    }

    public void insert(Book b) {

        try (SQLiteDatabase db = getWritableDatabase()) {
            ContentValues values = new ContentValues();

            values.put("photoId", b.getPhotoId());
            values.put("title", b.getTitle());
            values.put("author", b.getAuthor());
            values.put("genre", b.getGenre());
            values.put("publisher", b.getPublisher());

            db.insert("books", null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Book> getAll() {

        List<Book> books = new ArrayList<>();

        try (SQLiteDatabase db = getReadableDatabase()) {
            Cursor c = db.query("books", null, null, null, null, null, null);

            if (c.moveToFirst()) {
                do {
                    Integer fotoId = c.getInt(c.getColumnIndex("photoId"));
                    String titulo = c.getString(c.getColumnIndex("title"));
                    String autor = c.getString(c.getColumnIndex("author"));
                    String genero = c.getString(c.getColumnIndex("genre"));
                    String editora = c.getString(c.getColumnIndex("publisher"));

                    Book b = new Book();
                    b.setPhotoId(fotoId);
                    b.setTitle(titulo);
                    b.setAuthor(autor);
                    b.setGenre(genero);
                    b.setPublisher(editora);

                    books.add(b);
                } while (c.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }
}
