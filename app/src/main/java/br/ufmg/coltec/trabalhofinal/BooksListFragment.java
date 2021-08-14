package br.ufmg.coltec.trabalhofinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.ListFragment;

import com.github.javafaker.Faker;

public class BooksListFragment extends ListFragment {

    private Book[] books = createFakerBooksList();

    public Book[] createFakerBooksList() {
        Faker faker = new Faker();
        Book[] books1 = new Book[50];
        for (int i = 0; i < 50; i++) {
            books1[i] = new Book(R.drawable.ic_launcher_background, faker.book().title(), faker.book().author(), faker.book().genre(), faker.book().publisher());
        }
        return books1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_books_list, container, false);

        BookAdapter bookAdapter = new BookAdapter(books, this.getActivity());
        setListAdapter(bookAdapter);

        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Book selectedBook = this.books[position];

        Intent intent = new Intent(this.getActivity(), BookDetailsActivity.class);
        intent.putExtra("book", selectedBook);
        startActivity(intent);
        
        super.onListItemClick(l, v, position, id);
    }

}


