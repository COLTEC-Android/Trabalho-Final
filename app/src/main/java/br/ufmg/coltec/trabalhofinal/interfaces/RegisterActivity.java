package br.ufmg.coltec.trabalhofinal.interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.ufmg.coltec.trabalhofinal.R;
import br.ufmg.coltec.trabalhofinal.data.ApplicationDB;
import br.ufmg.coltec.trabalhofinal.data.entities.User;
import br.ufmg.coltec.trabalhofinal.data.dao.UserDAO;

public class RegisterActivity extends AppCompatActivity {
    private ApplicationDB applicationDB;
    private UserDAO userDAO;
    EditText name;
    EditText email;
    EditText password;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        applicationDB = ApplicationDB.getInstance(this);
        userDAO = new UserDAO(applicationDB);
        setEditTexts();
        setButtons();
    }

    private  void setEditTexts(){
        name = findViewById(R.id.edit_text_register_name);
        email = findViewById(R.id.edit_text_register_email);
        password = findViewById(R.id.edit_text_register_password);
    }

    private void setButtons(){
        btnRegister = findViewById(R.id.btn_sign_up);
        btnRegister.setOnClickListener(view -> {
            if(userDAO.validateRegister(email.getText().toString())){
                User newUser = new User(name.getText().toString(),
                        email.getText().toString(),
                        password.getText().toString());
                userDAO.insert(newUser);
                Intent homeActivity = new Intent(RegisterActivity.this, HomeActivity.class);
                homeActivity.putExtra("email", newUser.getEmail());
                startActivity(homeActivity);
            }else{
                Toast.makeText(this, R.string.invalid_register, Toast.LENGTH_SHORT).show();
            }
        });
    }
}