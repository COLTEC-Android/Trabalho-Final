package br.ufmg.coltec.trabalhofinal.controllers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.ListFragment;

import java.util.ArrayList;
import java.util.List;

import br.ufmg.coltec.trabalhofinal.R;
import br.ufmg.coltec.trabalhofinal.models.Contact;

// classe com métodos usados para construir as informações na tela
public class ContactsListFragment extends ListFragment {

    private List<Contact> contacts = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_list, container, false);
        ContactAdapter contactAdapter = new ContactAdapter(this.getActivity(), contacts);
        setListAdapter(contactAdapter);

        return view;
    }

    public void setContacts(List<Contact> contacts){
        this.contacts = contacts;
    }

}
