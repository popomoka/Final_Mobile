package com.example.projet_final_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.home_activity);

        User user = (User)getIntent().getSerializableExtra("USER");
        //L'app démarre sur cette page donc on dit que si le user est null
        // ça renvoie à la page de connexion sinon reste sur la page homeactivity
        if(user != null)
        {
            TextView usernameTextView = findViewById(R.id.welcomeText);
            usernameTextView.setText("Bonjour, " + user.username);
        }
        else
        {
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        //Créer le onclick sur le bouton jouer qui va renvoyer au jeu
        Button button= (Button)findViewById(R.id.jouerButton);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomeActivity.this, Game.class);
                intent.putExtra("USER", user);
                startActivity(intent);
            }
        });

        //Créer le onClick pour le bouton bestiaire qui renvoie à la page bestiaire
        Button buttonBestiaire= (Button)findViewById(R.id.BestiaireButton);
        buttonBestiaire.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, Monstre_Bestiaire.class);

                startActivity(intent);
            }
        });

        //Set le Onclick pour le bouton deconnexion qui renvoie à la page connexion
        Button Deco = (Button)findViewById(R.id.logoutButton);
        Deco.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}