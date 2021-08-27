package br.ufmg.coltec.trabalhofinal.classesintermediarias;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import br.ufmg.coltec.classesdemodelo.Book;
import br.ufmg.coltec.trabalhofinal.R;

public class BooksListFragmentTest {

    @Test
    public void onCreateView() {
        BooksListFragment listFragment = Mockito.mock(BooksListFragment.class);
        LayoutInflater inflater = Mockito.mock(LayoutInflater.class);
        ViewGroup container = Mockito.mock(ViewGroup.class);
        View view = inflater.inflate(R.layout.fragment_books_list, container, false);

        BookAdapter bookAdapter = Mockito.mock(BookAdapter.class);
        listFragment.setListAdapter(bookAdapter);


    }

    @Test
    public void onListItemClick() {

        Book book = new Book();

        List<Book> list = new ArrayList<>();
        List<Book> list1 = new ArrayList<>();

        list.add(book);

        BookSearch bs = Mockito.mock(BookSearch.class);
        BookSearchProxy pbs = new BookSearchProxy(bs);
        pbs.setCache(list);

        Book selectedBook = pbs.getCache().get(0);

        assert selectedBook == book;


    }
}