package br.ufmg.coltec.trabalhofinal.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    private String id;
    private String title;
    private String original_title;
    private String original_language;
    private String overview;
    private String release_date;
    private String poster_path;
    private String vote_average;

    public Movie(String id, String title, String original_title, String original_language, String overview, String release_date, String poster_path, String vote_average) {
        this.id = id;
        this.title = title;
        this.original_title = original_title;
        this.original_language = original_language;
        this.overview = overview;
        this.release_date = release_date;
        this.poster_path = poster_path;
        this.vote_average = vote_average;
    }

    protected Movie(Parcel in) {
        id = in.readString();
        title = in.readString();
        original_title = in.readString();
        original_language = in.readString();
        overview = in.readString();
        release_date = in.readString();
        poster_path = in.readString();
        vote_average = in.readString();
    }


    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(original_title);
        dest.writeString(original_language);
        dest.writeString(overview);
        dest.writeString(release_date);
        dest.writeString(poster_path);
        dest.writeString(vote_average);
    }
}
