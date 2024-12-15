package com.example.projet_final_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText emailEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        Button loginButton = findViewById(R.id.loginButton);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Validation des champs
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, R.string.Champ_manquant, Toast.LENGTH_SHORT).show();
                } else {
                    new Thread(() -> {
                        //Connect le user
                        User user = API_model.Login(emailEditText.getText().toString(),passwordEditText.getText().toString());

                        if(user != null)
                        {
                            user.password = password;
                            runOnUiThread(() -> {
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                intent.putExtra("USER", user);
                                startActivity(intent);
                            });
                        }
                    }).start();
                }
            }
        });

        //Créer le onClick pour le bouton inscription
        Button SignInButton = findViewById(R.id.SignInButton);
        SignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Validation des champs
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, R.string.Champ_manquant, Toast.LENGTH_SHORT).show();
                } else {
                    new Thread(() -> {
                        //Créer le user si le username est déjà pris renvoie null
                        User user = API_model.Signin(emailEditText.getText().toString(), passwordEditText.getText().toString());


                        if (user != null) {
                            //Si toute est valide change de page pour amener au menu principale
                            runOnUiThread(() -> {
                                user.password = password;
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                intent.putExtra("USER", user);
                                startActivity(intent);
                            });
                        }
                        else{
                            //Affiche un Toast en cas d'erreur de username
                            runOnUiThread(() -> {
                                Toast.makeText(LoginActivity.this, R.string.Erreur_username, Toast.LENGTH_SHORT).show();
                            });

                        }

                    }).start();
                }

            }
        });
    }
}