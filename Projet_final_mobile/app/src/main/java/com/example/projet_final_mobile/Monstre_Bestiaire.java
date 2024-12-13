package com.example.projet_final_mobile;

import android.os.Bundle;
import android.util.Log;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import java.util.ArrayList;

public class Monstre_Bestiaire extends AppCompatActivity {
    List<Monster> les_Monstres = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.bestiaire);
        new Thread(() -> {
            les_Monstres = API_model.GetAllMonstre();
            runOnUiThread(() -> {
            });
        }).start();

        RecyclerView recyclerView = findViewById(R.id.monster_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ImageView monsterImage = findViewById(R.id.monster_image);
        TextView damageMin = findViewById(R.id.monster_dammage_min);
        TextView damageMax = findViewById(R.id.monster_dammage_max);
        TextView armor = findViewById(R.id.monster_armor);
        TextView pvMax = findViewById(R.id.monster_pv_max);
        TextView dodgeChance = findViewById(R.id.monster_dodgechance);
        TextView critChance = findViewById(R.id.monster_critchance);

        // Définir l'adaptateur
        monstreList adapter = new monstreList(les_Monstres, monster -> {
            // Action lorsqu'un monstre est cliqué
            // Exemple : changer l'image et afficher des stats fictives
            monsterImage.setImageResource(R.drawable.ic_launcher_foreground); // Remplacez par une image réelle
            damageMin.setText("Dammage Min:" + monster.dammage_min);
            damageMax.setText("Dammage Max: " + monster.dammage_max);
            armor.setText("Armor: " + monster.armor);
            pvMax.setText("PV Max: " + monster.pv_max);
            dodgeChance.setText("Dodge Chance: " + monster.dodgechance + " %");
            critChance.setText("Crit Chance: " + monster.critchance +" %");
        });
        recyclerView.setAdapter(adapter);
    }

}
