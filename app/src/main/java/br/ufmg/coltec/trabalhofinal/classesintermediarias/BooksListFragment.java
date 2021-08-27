package br.ufmg.coltec.trabalhofinal.classesintermediarias;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.ListFragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

import br.ufmg.coltec.classesdemodelo.Book;
import br.ufmg.coltec.trabalhofinal.R;
import br.ufmg.coltec.trabalhofinal.activities.BookDetailsActivity;

public class BooksListFragment extends ListFragment {

    private SharedBookViewModel bookModel;
    private List<Book> books = new ArrayList<>();

    public BooksListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_books_list, container, false);

        BookAdapter bookAdapter = new BookAdapter(getContext(), books);
        setListAdapter(bookAdapter);

        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        BookSearch bs = new BookSearch();
        BookSearchProxy pbs = new BookSearchProxy(bs);
        pbs.setCache(books);

        Book selectedBook = pbs.getCache().get(position);

        Intent intent = new Intent(this.getActivity(), BookDetailsActivity.class);
        intent.putExtra("book", selectedBook);
        startActivity(intent);

        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                BookSearch bs = new BookSearch();
                BookSearchProxy pbs = new BookSearchProxy(bs);
                pbs.setCache(books);

                Book selectedBook = pbs.getCache().get(position);

                Log.v("long clicked", "pos: " + position);

                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getContext());

                alertBuilder.setIcon(R.drawable.ic_launcher_foreground);
                alertBuilder.setTitle(R.string.string_dialog_title);
                alertBuilder.setMessage(R.string.string_dialog_message);

                alertBuilder.setNegativeButton(R.string.string_dialog_btn_negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), R.string.string_dialog_negative, Toast.LENGTH_LONG).show();
                    }
                });

                alertBuilder.setPositiveButton(R.string.string_dialog_btn_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        bookModel = new ViewModelProvider(requireActivity()).get(SharedBookViewModel.class);
                        bookModel.setText(selectedBook);
                    }
                });
                AlertDialog dialog = alertBuilder.create();
                dialog.show();
                return true;
            }
        });

        super.onListItemClick(l, v, position, id);
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}


