package br.ufmg.coltec.trabalhofinal.classesintermediarias;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import br.ufmg.coltec.classesdemodelo.Book;

public class FavoriteBooksListFragmentTest {

    @Test
    public void onCreateView() {
    }

    @Test
    public void onViewCreated() {

        Book book = new Book();

        List<Book> books = new ArrayList<>();

        books.add(book);

        SharedBookViewModel model = Mockito.mock(SharedBookViewModel.class);

        model.setText(book);

        assert model.getText().getValue() == book;
    }

    @Test
    public void onListItemClick() {
    }
}