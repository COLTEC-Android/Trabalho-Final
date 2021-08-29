package br.ufmg.coltec.trabalhofinal.interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import br.ufmg.coltec.trabalhofinal.R;
import br.ufmg.coltec.trabalhofinal.business.ThemeManager;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeManager themeManager = new ThemeManager(this);
        this.setTheme(themeManager.getCurrentTheme());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setButtons();
    }

    @Override
    protected void onResume() {
        ThemeManager themeManager = new ThemeManager(this);
        this.setTheme(themeManager.getCurrentTheme());
        super.onResume();
    }

    private void setButtons(){
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);

        btnLogin.setOnClickListener(view ->{
            Intent loginActivity = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(loginActivity);
        });

        btnRegister.setOnClickListener(view -> {
            Intent registerActivity = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(registerActivity);
        });
    }


}