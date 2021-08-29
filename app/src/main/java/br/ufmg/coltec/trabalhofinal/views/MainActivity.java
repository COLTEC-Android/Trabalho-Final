package br.ufmg.coltec.trabalhofinal.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import br.ufmg.coltec.trabalhofinal.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnRegisterContact = findViewById(R.id.btn_contact_register);

        btnRegisterContact.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CreateContactListActivity.class);
            startActivity(intent);
        });

        Button btnContactList = findViewById(R.id.btn_contact_list);

        btnContactList.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ContactListActivity.class);
            startActivity(intent);
        });
    }
}