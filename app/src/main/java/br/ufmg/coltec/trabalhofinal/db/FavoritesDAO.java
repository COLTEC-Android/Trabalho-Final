package br.ufmg.coltec.trabalhofinal.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.ufmg.coltec.trabalhofinal.models.Movie;

public class FavoritesDAO {

    private FavoritesDB favoritesDB;
    private SQLiteDatabase db;

    public FavoritesDAO(Context context) {
        favoritesDB = FavoritesDB.getInstance(context);
    }

    public void insert(Movie movie) {

        db = favoritesDB.getWritableDatabase();

        try{
            ContentValues values = new ContentValues();
            values.put("movie_id", movie.getId());
            values.put("title", movie.getTitle());
            values.put("original_title", movie.getOriginal_title());
            values.put("original_language", movie.getOriginal_language());
            values.put("overview", movie.getOverview());
            values.put("release_date", movie.getRelease_date());
            values.put("poster_path", movie.getPoster_path());
            values.put("vote_average", movie.getVote_average());

            db.insert("favorites", null, values);

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    public List<Movie> getAll() {
        List<Movie> movies = new ArrayList<>();
        db = favoritesDB.getReadableDatabase();

        try {
            Cursor c = db.query("favorites", null, null, null, null, null, null);
            Log.d ("GET", "READ");
            if(c.moveToFirst()) {
                do {
                    String id = c.getString(c.getColumnIndex("movie_id"));
                    String title = c.getString(c.getColumnIndex("title"));
                    String original_title = c.getString(c.getColumnIndex("original_title"));
                    String original_language = c.getString(c.getColumnIndex("original_language"));
                    String overview = c.getString(c.getColumnIndex("overview"));
                    String release_date = c.getString(c.getColumnIndex("release_date"));
                    String poster_path = c.getString(c.getColumnIndex("poster_path"));
                    String vote_average = c.getString(c.getColumnIndex("vote_average"));
                    Log.d ("GET", "READ");

                    Movie movie = new Movie(id, title, original_title, original_language, overview, release_date, poster_path, vote_average);
                    movies.add(movie);
                    Log.d ("GET", "CREATE");

                } while(c.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            db.close();
        }
        return movies;
    }
}

