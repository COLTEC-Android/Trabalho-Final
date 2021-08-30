package br.ufmg.coltec.trabalhofinal;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

import android.content.Context;

import br.ufmg.coltec.trabalhofinal.controllers.ContactAdapter;
import br.ufmg.coltec.trabalhofinal.controllers.ContactsListFragment;
import br.ufmg.coltec.trabalhofinal.models.Contact;


public class ContactAdapterTest {

    Contact contact;
    List<Contact> contacts;
    Context context;
    ContactAdapter contactAdapter = new ContactAdapter(context.getApplicationContext(), contacts);

    @Before
    public void initClass(){

    }

    @Test
    public void testgetCount(){
        int sizeCountMethod = contactAdapter.getCount();
        int listSize = contacts.size();
        assertEquals(listSize, sizeCountMethod);
    }

    @Test
    public void testgetItem(){
        int itemPositionMethod = (int) contactAdapter.getItem(0);
        Contact objectContact = contact;
        assertEquals(objectContact, itemPositionMethod);
    }

    @Test
    public void testgetItemId(){
        int idItemMethod = (int) contactAdapter.getItemId(0);
        assertEquals(0, idItemMethod);
    }

}
