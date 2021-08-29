package br.ufmg.coltec.trabalhofinal.classesintermediarias;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.ufmg.coltec.classesdemodelo.Book;


public class BookAdapter extends BaseAdapter {

    private List<Book> books;
    private Context context;

    public BookAdapter(Context context, List<Book> books) {
        this.books = books;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.books.size();
    }

    @Override
    public Object getItem(int position) {
        return this.books.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Book book = this.books.get(position);

        TextView txtNome = new TextView(this.context);
        txtNome.setText(book.getTitle());

        return txtNome;
    }
}
