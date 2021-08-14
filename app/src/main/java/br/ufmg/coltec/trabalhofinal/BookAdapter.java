package br.ufmg.coltec.trabalhofinal;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BookAdapter extends BaseAdapter {

    private Book[] books;
    private Context context;

    public BookAdapter(Book[] books, Context context) {
        this.books = books;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.books.length;
    }

    @Override
    public Object getItem(int position) {
        return this.books[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Book book = this.books[position];

        TextView txtNome = new TextView(this.context);
        txtNome.setText(book.getTitle());

        return txtNome;
    }
}
