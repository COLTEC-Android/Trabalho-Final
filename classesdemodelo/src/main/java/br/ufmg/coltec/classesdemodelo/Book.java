package br.ufmg.coltec.classesdemodelo;

import java.io.Serializable;

public class Book implements Serializable {

    private Integer photoId;
    private String title;
    private String author;
    private String genre;
    private String publisher;

    public Book() {
    }

    public Book(Integer photoId, String title, String author, String genre, String publisher) {
        this.photoId = photoId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
    }

    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
