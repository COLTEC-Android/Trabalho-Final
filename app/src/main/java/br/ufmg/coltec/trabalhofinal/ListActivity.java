package br.ufmg.coltec.trabalhofinal;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;

import android.os.Bundle;

import java.io.Serializable;


public class ListActivity extends FragmentActivity {

    private final BookDAO bookDAO = new BookDAO(ListActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        BooksListFragment booksListFragment = new BooksListFragment();
        booksListFragment.setBooks(bookDAO.getAll());

        FragmentManager fm = this.getSupportFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frag_books_list, booksListFragment);
        ft.commit();

    }

}