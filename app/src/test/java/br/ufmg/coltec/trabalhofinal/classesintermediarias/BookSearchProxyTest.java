package br.ufmg.coltec.trabalhofinal.classesintermediarias;

import junit.framework.TestCase;

import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import br.ufmg.coltec.classesdemodelo.Book;

public class BookSearchProxyTest extends TestCase {

    private final BookSearchInterface base = Mockito.mock(BookSearchInterface.class);

    public void testGetBook() {
        Book book = new Book();

        List<Book> cache = new ArrayList<>();

        cache.add(book);

        if (cache.contains(cache.get(0))) {
            assert  cache.get(0) == book;
        } else {
            Book book1 = base.getBook(0);
            if (book1 != null) {
                cache.add(book1);
                assert cache.get(1) == book1;
            }
        }
    }
}