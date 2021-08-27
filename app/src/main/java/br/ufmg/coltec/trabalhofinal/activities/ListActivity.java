package br.ufmg.coltec.trabalhofinal.activities;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import br.ufmg.coltec.trabalhofinal.R;
import br.ufmg.coltec.trabalhofinal.classesintermediarias.BookDAO;
import br.ufmg.coltec.trabalhofinal.classesintermediarias.BooksListFragment;


public class ListActivity extends FragmentActivity {

    private final BookDAO bookDAO = new BookDAO(ListActivity.this);
    private static final String APP_PREF_THEME_ID = "SP_THEME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = this.getSharedPreferences(APP_PREF_THEME_ID, 0);
        boolean theme = preferences.getBoolean("theme", true);
        if (theme){
            this.setTheme(R.style.Theme_TrabalhoFinal);
        } else {
            this.setTheme(R.style.Theme_TrabalhoFinalGold);
        }
        setContentView(R.layout.activity_list);

        BooksListFragment booksListFragment = new BooksListFragment();
        booksListFragment.setBooks(bookDAO.getAll());

        FragmentManager fm = this.getSupportFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frag_books_list, booksListFragment);
        ft.commit();

    }

}