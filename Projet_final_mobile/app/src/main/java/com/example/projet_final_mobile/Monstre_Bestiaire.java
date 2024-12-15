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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final List<Monster> les_Monstres = new ArrayList<>();
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.bestiaire);
        Thread t = new Thread(() -> {
            List<Monster> m = API_model.GetAllMonstre();
            synchronized (les_Monstres){
                les_Monstres.clear();
                les_Monstres.addAll(m);
            }
        });
        t.start();
        try {
            t.join(); // Attendre que le thread termine son exécution
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Log.d("Monstre",les_Monstres.toString());
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
            if(monster == null)
            {
                monster = les_Monstres.get(0);
            }
            // Action lorsqu'un monstre est cliqué
            // Exemple : changer l'image et afficher des stats fictives
            monsterImage.setImageResource(getResources().getIdentifier(monster.image, "drawable", getPackageName())); // Remplacez par une image réelle
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
