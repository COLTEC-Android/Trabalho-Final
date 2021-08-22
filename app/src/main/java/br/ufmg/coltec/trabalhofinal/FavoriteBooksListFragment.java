package br.ufmg.coltec.trabalhofinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.ListFragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

public class FavoriteBooksListFragment extends ListFragment {

    private SharedBookViewModel model;

    private List<Book> books = new ArrayList<>();

    public FavoriteBooksListFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_books_list, container, false);


        BookAdapter bookAdapter = new BookAdapter(getContext(), books);
        setListAdapter(bookAdapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        model = new ViewModelProvider(requireActivity()).get(SharedBookViewModel.class);
        model.getText().observe(getViewLifecycleOwner(), item -> {

            if (books.contains(item)) {
                Toast.makeText(getContext(), R.string.string_favorite_book_already_on_list, Toast.LENGTH_LONG).show();
            } else {
                books.add(item);

                BookAdapter bookAdapter = new BookAdapter(getContext(), books);
                setListAdapter(bookAdapter);

                Toast.makeText(getContext(), R.string.string_dialog_positive, Toast.LENGTH_LONG).show();
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        Book selectedBook = this.books.get(position);

        Intent intent = new Intent(this.getActivity(), BookDetailsActivity.class);
        intent.putExtra("book", selectedBook);
        startActivity(intent);

        super.onListItemClick(l, v, position, id);
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}
