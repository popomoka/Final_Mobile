package com.example.projet_final_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RecomenceActivity extends AppCompatActivity {
    User user;
    int rounds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recompence);
        user = (User)getIntent().getSerializableExtra("USER");
        rounds = getIntent().getIntExtra("ROUND",0);

        Button menu = findViewById(R.id.retourMenu);
        TextView cash = findViewById(R.id.cash);
        TextView roundview = findViewById(R.id.rounds);

        cash.setText(Math.round(rounds*0.8) + "$");
        roundview.setText(rounds + " rondes");

        EnvoyerEnBd();

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecomenceActivity.this, HomeActivity.class);
                intent.putExtra("USER", user );
                startActivity(intent);
            }
        });
    }


    void EnvoyerEnBd()
    {
        
    }
}
