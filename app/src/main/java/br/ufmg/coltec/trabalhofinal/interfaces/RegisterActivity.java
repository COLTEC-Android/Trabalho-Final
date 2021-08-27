package br.ufmg.coltec.trabalhofinal.interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import br.ufmg.coltec.data.ApplicationDB;
import br.ufmg.coltec.trabalhofinal.R;
import br.ufmg.coltec.data.entities.User;
import br.ufmg.coltec.data.dao.UserDAO;

public class RegisterActivity extends AppCompatActivity {
    private ApplicationDB applicationDB;
    private UserDAO userDAO;
    EditText name;
    EditText email;
    EditText password;
    Button btnRegister;
    ProgressBar progressBar;

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        applicationDB = ApplicationDB.getInstance(this);
        userDAO = new UserDAO(applicationDB);
        progressBar = findViewById(R.id.progress_bar_register);
        progressBar.setVisibility(View.INVISIBLE);
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
                Thread thread = new Thread() {
                    public void run() {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                progressBar.setVisibility(View.VISIBLE);
                            }
                        });
                        userDAO.insert(newUser);
                    }
                };
                thread.start();
                Intent homeActivity = new Intent(RegisterActivity.this, HomeActivity.class);
                homeActivity.putExtra("email", newUser.getEmail());
                startActivity(homeActivity);
            }else{
                Toast.makeText(this, R.string.invalid_register, Toast.LENGTH_SHORT).show();
            }
        });
    }
}