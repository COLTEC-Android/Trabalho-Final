package br.ufmg.coltec.trabalhofinal.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.ufmg.coltec.trabalhofinal.models.Contact;

public class ContactDAO {

    private static final String TABLE_NAME = "contact";
    private SQLiteOpenHelper helper;

    public ContactDAO(SQLiteOpenHelper helper){
        this.helper = helper;
    }

    // método que insere dados na tabela
    public void insertContact(Contact contact) {

        SQLiteDatabase database = this.helper.getWritableDatabase();

        try {

            // criando lista de valores que serão inseridos
            ContentValues contentValues = new ContentValues();

            // inserindo valores na lista
            contentValues.put("name", contact.getName());
            contentValues.put("email", contact.getEmail());
            contentValues.put("phone", contact.getPhone());
            contentValues.put("linkedin", contact.getLinkedin());
            contentValues.put("github", contact.getGithub());

            // inserindo lista de valores na tabela
            database.insert(TABLE_NAME, null, contentValues);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.close();
        }
    }

    // método que lê dados da tabela
    public List<Contact> getAllContacts() {
        // instanciando a lista
        List<Contact> contacts = new ArrayList<>();

        SQLiteDatabase database = this.helper.getReadableDatabase();

        // consulta ao banco de dados
        try {

            Cursor cursor = database.query
                    (
                            TABLE_NAME,
                            new String[]{"name"},
                            null,
                            null,
                            null,
                            null,
                            null
                    );
            if (cursor.moveToFirst()) {
                do {
                    String name = cursor.getString(cursor.getColumnIndex("name"));

                    Contact contact = new Contact(name);
                    contacts.add(contact);
                } while (cursor.moveToNext());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.close();
        }

        return  contacts;
    }
}
