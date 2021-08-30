package br.ufmg.coltec.trabalhofinal.views;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.ufmg.coltec.trabalhofinal.DAO.ContactDAO;
import br.ufmg.coltec.trabalhofinal.R;
import br.ufmg.coltec.trabalhofinal.models.Contact;
import br.ufmg.coltec.trabalhofinal.models.ContactDB;

public class CreateContactListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_list);

        // instanciando o banco de dados
        ContactDB contactDB = new ContactDB(this);
        // Instanciando DAO
        ContactDAO contactDAO = new ContactDAO(contactDB);

        EditText inputName = this.findViewById(R.id.input_name);
        EditText inputEmail = this.findViewById(R.id.input_email);
        EditText inputPhone= this.findViewById(R.id.input_phone);
        EditText inputLinkedIn = this.findViewById(R.id.input_linkedin);
        EditText inputGitHub = this.findViewById(R.id.input_github);

        Button btnRegister = this.findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(view -> {
            // recuperando dados do input
            String name = inputName.getText().toString();
            String email = inputEmail.getText().toString();
            String phone = inputPhone.getText().toString();
            String linkedin = inputLinkedIn.getText().toString();
            String github = inputGitHub.getText().toString();

            // instanciando modelo de contato
            Contact contact = new Contact(name);

            //inserindo dados do modelo no banco de dados
            contactDAO.insertContact(contact);

            Toast.makeText(CreateContactListActivity.this, "Contato cadastrado com sucesso", Toast.LENGTH_SHORT).show();
        });

    }
}
