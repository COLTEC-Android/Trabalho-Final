package br.ufmg.coltec.trabalhofinal.classesintermediarias;

import junit.framework.TestCase;

import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import br.ufmg.coltec.classesdemodelo.Book;

public class BookSearchTest extends TestCase {

    public void testGetBook() {

        Book book = new Book();

        List<Book> list = new ArrayList<>();

        list.add(book);

        Book book1 = list.get(0);

        assert book1 == book;
    }

}