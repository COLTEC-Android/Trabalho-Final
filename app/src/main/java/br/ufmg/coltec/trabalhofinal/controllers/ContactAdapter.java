package br.ufmg.coltec.trabalhofinal.controllers;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.ufmg.coltec.trabalhofinal.models.Contact;

public class ContactAdapter extends BaseAdapter {

    private List<Contact> contacts;
    private Context context;

    public ContactAdapter(Context context, List<Contact> contacts) {
        this.contacts = contacts;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return this.contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Contact contact = this.contacts.get(position);

        TextView nameView = new TextView(this.context);
        nameView.setText(contact.getName());

        return nameView;
    }
}
