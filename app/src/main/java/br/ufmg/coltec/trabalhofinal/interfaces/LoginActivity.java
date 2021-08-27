package br.ufmg.coltec.trabalhofinal.interfaces;

import androidx.appcompat.app.AppCompatActivity;

import br.ufmg.coltec.data.ApplicationDB;
import br.ufmg.coltec.trabalhofinal.R;
import br.ufmg.coltec.data.entities.User;
import br.ufmg.coltec.data.dao.UserDAO;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button btnSignIn;
    private ApplicationDB applicationDB;
    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applicationDB = ApplicationDB.getInstance(this);
        userDAO = new UserDAO(applicationDB);
        setContentView(R.layout.activity_login);
        setEditTexts();
        setButtons();
    }

    private  void setEditTexts(){
        email = findViewById(R.id.edit_text_register_email);
        password = findViewById(R.id.edit_text_register_password);
    }

    private void setButtons(){
        btnSignIn = findViewById(R.id.btn_sign_in);
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