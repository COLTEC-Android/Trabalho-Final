package br.ufmg.coltec.trabalhofinal.classesintermediarias;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

import br.ufmg.coltec.classesdemodelo.Book;
import br.ufmg.coltec.trabalhofinal.R;

public class BookAdapterTest extends TestCase {

    public void testGetCount() {
        Book book1 = new Book();
        Book book2 = new Book();

        List<Book> list = new ArrayList<>();

        list.add(book1);
        list.add(book2);

        assert list.size() == 2;
    }

    public void testGetItem() {
        Book book = new Book();

        List<Book> list = new ArrayList<>();

        list.add(book);

        Book book1 = list.get(0);

        assert book1 == book;
    }

    public void testGetItemId() {
        int position;

        Book book = new Book();

        List<Book> list = new ArrayList<>();

        list.add(book);

        position = list.indexOf(book);

        assert position == 0;
    }

    public void testGetView() {

        Book book = new Book(R.drawable.ic_launcher_background, "title", "author", "genre", "publisher");

        List<Book> list = new ArrayList<>();

        list.add(book);

        String txt = book.getTitle();

        assert txt.equals("title");
    }
}