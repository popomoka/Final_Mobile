package com.example.projet_final_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class BoutiqueActivity extends AppCompatActivity {
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.boutique);
        user = (User)getIntent().getSerializableExtra("USER");
        TextView cash = findViewById(R.id.player_money);
        cash.setText(getString(R.string.Argent) + " : " + user.cash + "$");

        setButtonText();

        //bouton pour revenir au mennu
        Button menu = (Button)findViewById(R.id.retourMenu2);
        menu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(BoutiqueActivity.this, HomeActivity.class);
                intent.putExtra("USER", user);
                startActivity(intent);
            }
        });

        //Bouton qui retire l'argent et met a jour les upgrades et stats du joueurs pour l'achat du upgrade 1
        Button upgrade1 = (Button)findViewById(R.id.btn_upgrade_1);
        upgrade1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Thread t = new Thread(() -> {
                    user.cash -= GetPrixUpgrade(1,user.upgradebought[0]);
                    user.upgradebought[0]+=1;
                    ApiCall_Perso.PutPerso(user);
                    runOnUiThread(() -> {
                        // Exemple de rafraîchissement de l'UI
                        setButtonText();
                        cash.setText(getString(R.string.Argent) +" : "+ user.cash + "$");
                    });
                });
                t.start();
                try {
                    t.join(); // Attendre que le thread termine son exécution
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //Bouton qui retire l'argent et met a jour les upgrades et stats du joueurs pour l'achat du upgrade 2
        Button upgrade2 = (Button)findViewById(R.id.btn_upgrade_2);
        upgrade2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Thread t = new Thread(() -> {
                    user.cash -= GetPrixUpgrade(2,user.upgradebought[1]);
                    user.upgradebought[1]+=1;
                    ApiCall_Perso.PutPerso(user);
                    runOnUiThread(() -> {
                        // Exemple de rafraîchissement de l'UI
                        setButtonText();
                        cash.setText(getString(R.string.Argent) +" : "+ user.cash + "$");
                    });
                });
                t.start();
                try {
                    t.join(); // Attendre que le thread termine son exécution
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //Bouton qui retire l'argent et met a jour les upgrades et stats du joueurs pour l'achat du upgrade 3
        Button upgrade3 = (Button)findViewById(R.id.btn_upgrade_3);
        upgrade3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Thread t = new Thread(() -> {
                    user.cash -= GetPrixUpgrade(3,user.upgradebought[2]);
                    user.upgradebought[2]+=1;
                    ApiCall_Perso.PutPerso(user);
                    runOnUiThread(() -> {
                        // Exemple de rafraîchissement de l'UI
                        setButtonText();
                        cash.setText(getString(R.string.Argent) + " : " + user.cash + "$");
                    });
                });
                t.start();
                try {
                    t.join(); // Attendre que le thread termine son exécution
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //Bouton qui retire l'argent et met a jour les upgrades et stats du joueurs pour l'achat du upgrade 4
        Button upgrade4 = (Button)findViewById(R.id.btn_upgrade_4);
        upgrade4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Thread t = new Thread(() -> {
                    user.cash -= GetPrixUpgrade(4,user.upgradebought[3]);
                    user.upgradebought[3]+=1;
                    ApiCall_Perso.PutPerso(user);
                    runOnUiThread(() -> {
                        // Exemple de rafraîchissement de l'UI
                        setButtonText();
                        cash.setText(getString(R.string.Argent) +" : "+ user.cash + "$");
                    });
                });
                t.start();
                try {
                    t.join(); // Attendre que le thread termine son exécution
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //Bouton qui retire l'argent et met a jour les upgrades et stats du joueurs pour l'achat du upgrade 5
        Button upgrade5 = (Button)findViewById(R.id.btn_upgrade_5);
        upgrade5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Thread t = new Thread(() -> {
                    user.cash -= GetPrixUpgrade(5,user.upgradebought[4]);
                    user.upgradebought[4]+=1;
                    ApiCall_Perso.PutPerso(user);
                    runOnUiThread(() -> {
                        // Exemple de rafraîchissement de l'UI
                        setButtonText();
                        cash.setText(getString(R.string.Argent) +" : "+ user.cash + "$");
                    });
                });
                t.start();
                try {
                    t.join(); // Attendre que le thread termine son exécution
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //Bouton qui retire l'argent et met a jour les upgrades et stats du joueurs pour l'achat du upgrade 6
        Button upgrade6 = (Button)findViewById(R.id.btn_upgrade_6);
        upgrade6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Thread t = new Thread(() -> {
                    user.cash -= GetPrixUpgrade(6,user.upgradebought[5]);
                    user.upgradebought[5]+=1;
                    ApiCall_Perso.PutPerso(user);
                    runOnUiThread(() -> {
                        // Exemple de rafraîchissement de l'UI
                        setButtonText();
                        cash.setText(getString (R.string.Argent) +" : "+ user.cash + "$");
                    });
                });
                t.start();
                try {
                    t.join(); // Attendre que le thread termine son exécution
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //met a jour l'interface en fonction des achats déjà éffectués par le joueur
    void setButtonText()
    {
        int upgrade1 = user.upgradebought[0];
        int upgrade2 = user.upgradebought[1];
        int upgrade3 = user.upgradebought[2];
        int upgrade4 = user.upgradebought[3];
        int upgrade5 = user.upgradebought[4];
        int upgrade6 = user.upgradebought[5];

        TextView upgrade1_textview = findViewById(R.id.description_upgrade_1);
        TextView upgrade2_textview = findViewById(R.id.description_upgrade_2);
        TextView upgrade3_textview = findViewById(R.id.description_upgrade_3);
        TextView upgrade4_textview = findViewById(R.id.description_upgrade_4);
        TextView upgrade5_textview = findViewById(R.id.description_upgrade_5);
        TextView upgrade6_textview = findViewById(R.id.description_upgrade_6);

        Button btn_upgrade1 = findViewById(R.id.btn_upgrade_1);
        Button btn_upgrade2 = findViewById(R.id.btn_upgrade_2);
        Button btn_upgrade3 = findViewById(R.id.btn_upgrade_3);
        Button btn_upgrade4 = findViewById(R.id.btn_upgrade_4);
        Button btn_upgrade5 = findViewById(R.id.btn_upgrade_5);
        Button btn_upgrade6 = findViewById(R.id.btn_upgrade_6);

        upgrade1_textview.setText(getString(R.string.Dommage_max) + " : \n\t" + upgrade1 + " -> " + (upgrade1+1));
        upgrade2_textview.setText(getString(R.string.Dommage_min) + " : \n\t" + upgrade2 + " -> " + (upgrade2+1));
        upgrade3_textview.setText(getString(R.string.Armure) + " : \n\t" + upgrade3 + " -> " + (upgrade3+1));
        upgrade4_textview.setText(getString(R.string.Pv) + " : \n\t" + upgrade4 + " -> " + (upgrade4+1));
        upgrade5_textview.setText(getString(R.string.Dodge) + " : \n\t" + upgrade5 + " -> " + (upgrade5+1));
        upgrade6_textview.setText(getString(R.string.Crit) + " : \n\t" + upgrade6 + " -> " + (upgrade6+1));

        btn_upgrade1.setText(GetPrixUpgrade(1,upgrade1) + " $");
        btn_upgrade2.setText(GetPrixUpgrade(2,upgrade2) + " $");
        btn_upgrade3.setText(GetPrixUpgrade(3,upgrade3) + " $");
        btn_upgrade4.setText(GetPrixUpgrade(4,upgrade4) + " $");
        btn_upgrade5.setText(GetPrixUpgrade(5,upgrade5) + " $");
        btn_upgrade6.setText(GetPrixUpgrade(6,upgrade6) + " $");
    }

    //donne le prix d'un upgrade
    int GetPrixUpgrade(int id, int lvl)
    {
        if(id== 1)
        {
            return lvl*50+10;
        }
        if(id== 2)
        {
            return lvl*50+10;
        }
        if(id== 3)
        {
            return lvl*50+10;
        }
        if(id== 4)
        {
            return lvl*50+10;
        }
        if(id== 5)
        {
            return lvl*50+10;
        }
        if(id== 6)
        {
            return lvl*50+10;
        }

        return 0;
    }
}
