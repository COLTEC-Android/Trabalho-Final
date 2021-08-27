package br.ufmg.coltec.trabalhofinal.classesintermediarias;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.test.espresso.Espresso;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import br.ufmg.coltec.classesdemodelo.Book;
import br.ufmg.coltec.trabalhofinal.R;

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