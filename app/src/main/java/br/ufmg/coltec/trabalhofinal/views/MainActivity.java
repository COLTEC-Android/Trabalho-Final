package br.ufmg.coltec.trabalhofinal.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import br.ufmg.coltec.trabalhofinal.R;

public class MainActivity extends AppCompatActivity {

    private static int CURRENT_THEME = R.style.Theme_TrabalhoFinal;

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}