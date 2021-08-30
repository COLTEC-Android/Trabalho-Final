package br.ufmg.coltec.trabalhofinal.views;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

import br.ufmg.coltec.trabalhofinal.DAO.ContactDAO;
import br.ufmg.coltec.trabalhofinal.R;
import br.ufmg.coltec.trabalhofinal.controllers.ContactsListFragment;
import br.ufmg.coltec.trabalhofinal.models.Contact;
import br.ufmg.coltec.trabalhofinal.models.ContactDB;

public class ContactListActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        ContactsListFragment contactsListFragment = new ContactsListFragment();
        ContactDB contactDB = new ContactDB(this);
        // Instanciando DAO
        ContactDAO contactDAO = new ContactDAO(contactDB);

        contactsListFragment.setContacts(contactDAO.getAllContacts());

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.frag_contacts_list, contactsListFragment)
                .commit();

    }
}
