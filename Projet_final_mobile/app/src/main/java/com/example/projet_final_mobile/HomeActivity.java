package com.example.projet_final_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.home_activity);

        User user = (User)getIntent().getSerializableExtra("USER");

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

        Button button= (Button)findViewById(R.id.jouerButton);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, Game.class);
                intent.putExtra("USER", user);
                startActivity(intent);
            }
        });
    }
}