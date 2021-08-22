package br.ufmg.coltec.trabalhofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String APP_PREF_ID = "SP_MAIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //devolve último acesso e salva o acesso atual em Shared Preferences
        SharedPreferences pref2 = this.getSharedPreferences(APP_PREF_ID, 0);
        String data = pref2.getString("chaveData", "");

        String text = String.format("%s %s", getString(R.string.string_last_access), data);

        if (!data.equals("")) {
            Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG).show();
        }

        String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());

        SharedPreferences pref = this.getSharedPreferences(APP_PREF_ID, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("chaveData", currentDate + " - " + currentTime);
        editor.commit();


        Button btnMain = findViewById(R.id.btn_main);

        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);

            }
        });
    }
}