package br.ufmg.coltec.trabalhofinal.interfaces;

import androidx.appcompat.app.AppCompatActivity;

import br.ufmg.coltec.data.ApplicationDB;
import br.ufmg.coltec.trabalhofinal.R;
import br.ufmg.coltec.data.entities.User;
import br.ufmg.coltec.data.dao.UserDAO;
import br.ufmg.coltec.trabalhofinal.business.ThemeManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeManager themeManager = new ThemeManager(this);
        this.setTheme(themeManager.getCurrentTheme());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setComponents();
    }

    @Override
    protected void onResume() {
        ThemeManager themeManager = new ThemeManager(this);
        this.setTheme(themeManager.getCurrentTheme());
        super.onResume();
    }

    private void setComponents(){
        EditText email = findViewById(R.id.edit_text_register_email);
        EditText password = findViewById(R.id.edit_text_register_password);
        Button btnSignIn = findViewById(R.id.btn_sign_in);

        ApplicationDB applicationDB = ApplicationDB.getInstance(this);
        UserDAO userDAO = new UserDAO(applicationDB);

        btnSignIn.setOnClickListener(view -> {
            User user = userDAO.validateUser(email.getText().toString(), password.getText().toString());
            if(user == null){
                Toast.makeText(this, R.string.invalid_login, Toast.LENGTH_SHORT).show();
            }else{
                Intent homeActivity = new Intent(LoginActivity.this, HomeActivity.class);
                Log.d("LOGIN USERDATA: ", user.getName() + " : " + user.getEmail());
                //homeActivity.putExtra("name", user.getName());
                homeActivity.putExtra("email", user.getEmail());
                startActivity(homeActivity);
            }
        });
    }
}