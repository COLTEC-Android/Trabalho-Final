package br.ufmg.coltec.trabalhofinal.classesintermediarias;

import java.util.List;

import br.ufmg.coltec.classesdemodelo.Book;


public class BookSearch implements BookSearchInterface {

    private List<Book> books;

    public BookSearch() {
    }

    @Override
    public Book getBook(int position) {
        return books.get(position);
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
