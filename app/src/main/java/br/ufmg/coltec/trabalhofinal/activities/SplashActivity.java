package br.ufmg.coltec.trabalhofinal.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import br.ufmg.coltec.classesdemodelo.Book;
import br.ufmg.coltec.trabalhofinal.R;
import br.ufmg.coltec.trabalhofinal.classesintermediarias.BookDAO;

public class SplashActivity extends AppCompatActivity {

    BookDAO bookDAO = new BookDAO(SplashActivity.this);
    private static final String APP_PREF_ID = "SP_MAIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        int SPLASH_TIME_OUT = 4000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //devolve último acesso e salva o acesso atual em Shared Preferences
                SharedPreferences pref2 = getSharedPreferences(APP_PREF_ID, 0);
                String chaveData = pref2.getString("chaveData", "");

                String text = String.format("%s %s", getString(R.string.string_last_access), chaveData);

                if (!chaveData.equals("")) {
                    Toast.makeText(SplashActivity.this, text, Toast.LENGTH_LONG).show();
                }

                String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
                String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());

                SharedPreferences pref = getSharedPreferences(APP_PREF_ID, 0);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("chaveData", currentDate + " - " + currentTime);
                editor.commit();
                //TODO inserir dados reais no banco, SE não existirem no mesmo && colocar as imagens corretas
                if (!bookDAO.getAll().contains(new Book(R.drawable.ic_launcher_background, "Memórias Póstumas de Brás Cubas", "Machado de Assis", "Romance", "Tipografia Nacional"))){
                    bookDAO.insert(new Book(R.drawable.ic_launcher_background, "Memórias Póstumas de Brás Cubas", "Machado de Assis", "Romance", "Tipografia Nacional"));

                }


                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);

                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}