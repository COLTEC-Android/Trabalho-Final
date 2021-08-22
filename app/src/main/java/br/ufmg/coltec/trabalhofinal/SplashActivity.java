package br.ufmg.coltec.trabalhofinal;

import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.github.javafaker.Faker;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        BookDAO bookDAO = new BookDAO(SplashActivity.this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //TODO inserir dados reais no banco
                bookDAO.insert(new Book(R.drawable.ic_launcher_background, "Memórias Póstumas de Brás Cubas", "Machado de Assis", "Romance", "Tipografia Nacional"));


                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);

                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}