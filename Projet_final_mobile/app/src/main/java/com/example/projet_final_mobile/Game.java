package com.example.projet_final_mobile;

import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Handler;
import java.util.Random;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Game extends AppCompatActivity {

    public Perso perso;
    public Monster monstre;
    boolean combatIsOver;
    Item[] listeItems = new Item[40];
    Monster[] listeMonstre = new Monster[40];
    Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.game_activity);
        StartGame();

        Button button = (Button)findViewById(R.id.ButtonStart);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                View buttonStart = findViewById(R.id.ButtonStart);
                buttonStart.setVisibility(View.GONE);
                combatIsOver = false;
                View OptionsBoissons = findViewById(R.id.OptionsBoissons);
                OptionsBoissons.setVisibility(View.GONE);

                handler.post(Combat);
            }
        });
    }

    Runnable Combat = new Runnable() {
        int montour = 1;
        @Override
        public void run() {
            switch(montour)
            {
                case 1:
                    GiveDammage(1);
                    break;
                case 0:
                    TakeDammage(1);
                    break;
            }

            UpdatePv();

            if(montour == 1)
                montour = 0;
            else
                montour = 1;


            if(!combatIsOver)
            {
                handler.postDelayed(Combat, 1000);
            }
            else
            {
                endround();
            }
        }
    };

    void StartRound()
    {
        GenerateMonster();
        View buttonStart = findViewById(R.id.ButtonStart);
        View OptionsBoissons = findViewById(R.id.OptionsBoissons);
        OptionsBoissons.setVisibility(View.VISIBLE);
        buttonStart.setVisibility(View.VISIBLE);

    }

    void TakeDammage(int ammount)
    {
        perso.pv_actuel -= ammount;

        if (perso.pv_actuel <=0)
        {
            combatIsOver = true;
        }

        UpdatePv();
    }

    void GiveDammage(int ammount)
    {
        monstre.pv_actuel -= ammount;

        if (monstre.pv_actuel <=0)
        {
            combatIsOver = true;
        }

        UpdatePv();
    }

    void endround()
    {
        ImageView ImageMonstre = findViewById(R.id.ImageMonstre);
        ImageMonstre.setImageResource(R.drawable.vide);

        //should be random
        Item item1 = listeItems[0];
        Item item2 = listeItems[1];
        Item item3 = listeItems[2];

        ImageView item1Image = findViewById(R.id.Item1);
        ImageView item2Image = findViewById(R.id.Item2);
        ImageView item3Image = findViewById(R.id.Item3);

        TextView item1Text = findViewById(R.id.TextItem1);
        TextView item2Text = findViewById(R.id.TextItem2);
        TextView item3Text = findViewById(R.id.TextItem3);

        item1Image.setImageResource(ConversionStringEnDrawableImage(item1.image));
        item2Image.setImageResource(ConversionStringEnDrawableImage(item2.image));
        item3Image.setImageResource(ConversionStringEnDrawableImage(item3.image));

        View recompences = findViewById(R.id.ChoixRecompenses);
        recompences.setVisibility(View.VISIBLE);
        //spawn button recompences
    }

    void buttonClickedRecompense(View v)
    {
        // affichage nouvel item
        //stats nouvel item
        //cacher les buttons

        //activer boutton boisson
        StartRound();
    }

    void GenerateMonster()
    {
        //genere un id selon la round
        //vas chercher ses stats sur la bd
        Random rand = new Random();
        monstre = listeMonstre[rand.nextInt(3)];
    }

    void StartGame()
    {
        Item item1 = new Item(1,3,0,0,0,2,"casque_1");
        Item item2 = new Item(2,5,3,0,0,3,"casque_7");
        Item item3 = new Item(0,7,0,7,10,0,"jamb_5");
        //...

        listeItems[0] = item1;
        listeItems[1] = item2;
        listeItems[2] = item3;
        //...

        Monster monstre1 = new Monster(0,3,0,7,0,0,"test");
        Monster monstre2 = new Monster(0,4,1,4,0,0,"test");
        Monster monstre3 = new Monster(1,2,0,7,1,4,"test");
        //...

        listeMonstre[0] = monstre1;
        listeMonstre[1] = monstre2;
        listeMonstre[2] = monstre3;
        //...

        perso = new Perso(0, 5, 1, 20, 0, 0, null, null, null, null, null, null);
        UpdateStats();
        StartRound();
    }

    void UpdateStats()
    {
        int dmgmin = perso.getDammage_min();
        int dmgmax = perso.getDammage_max();
        TextView text_dammage_stats = findViewById(R.id.text_dammage_stats);
        text_dammage_stats.setText("Dammage : " + dmgmin + "-" + dmgmax);

        int pvmax = perso.getPv();
        TextView text_pv_stats = findViewById(R.id.text_pv_stats);
        text_pv_stats.setText("Points de vie : " + perso.pv_actuel + "/" + pvmax);

        int armor = perso.getArmor();
        TextView text_armor_stats = findViewById(R.id.text_armor_stats);
        text_armor_stats.setText("Armure : " + armor);

        int critChance = perso.getCritChance();
        TextView text_crit_stats = findViewById(R.id.text_crit_stats);
        text_crit_stats.setText("Chance de coup critique : " + critChance + "%");

        int dodgeChance = perso.getDodgeChance();
        TextView text_dodge_stats = findViewById(R.id.text_dodge_stats);
        text_dodge_stats.setText("Chance d'esquive: " + dodgeChance + "%");
    }

    void UpdatePv()
    {
        int pvmax = perso.getPv();
        TextView text_pv_stats = findViewById(R.id.text_pv_stats);
        text_pv_stats.setText("Points de vie : " + perso.pv_actuel + "/" + pvmax);
    }

    int ConversionStringEnDrawableImage(String image)
    {
        switch(image) {
            // Casque (1 à 10)
            case "casque_1":
                return R.drawable.casque_1;
            case "casque_2":
                return R.drawable.casque_2;
            case "casque_3":
                return R.drawable.casque_3;
            case "casque_4":
                return R.drawable.casque_4;
            case "casque_5":
                return R.drawable.casque_5;
            case "casque_6":
                return R.drawable.casque_6;
            case "casque_7":
                return R.drawable.casque_7;
            case "casque_8":
                return R.drawable.casque_8;
            case "casque_9":
                return R.drawable.casque_9;
            case "casque_10":
                return R.drawable.casque_10;

            // Jambes (1 à 10)
            case "jamb_1":
                return R.drawable.jamb_1;
            case "jamb_2":
                return R.drawable.jamb_2;
            case "jamb_3":
                return R.drawable.jamb_3;
            case "jamb_4":
                return R.drawable.jamb_4;
            case "jamb_5":
                return R.drawable.jamb_5;
            case "jamb_6":
                return R.drawable.jamb_6;
            case "jamb_7":
                return R.drawable.jamb_7;
            case "jamb_8":
                return R.drawable.jamb_8;
            case "jamb_9":
                return R.drawable.jamb_9;
            case "jamb_10":
                return R.drawable.jamb_10;

            // plastron (1 à 10)
            case "plastron_1":
                return R.drawable.plastron_1;
            case "plastron_2":
                return R.drawable.plastron_2;
            case "plastron_3":
                return R.drawable.plastron_3;
            case "plastron_4":
                return R.drawable.plastron_4;
            case "plastron_5":
                return R.drawable.plastron_5;
            case "plastron_6":
                return R.drawable.plastron_6;
            case "plastron_7":
                return R.drawable.plastron_7;
            case "plastron_8":
                return R.drawable.plastron_8;
            case "plastron_9":
                return R.drawable.plastron_9;
            case "plastron_10":
                return R.drawable.plastron_10;

            // Bottes (1 à 10)
            case "bottes_1":
                return R.drawable.bottes_1;
            case "bottes_2":
                return R.drawable.bottes_2;
            case "bottes_3":
                return R.drawable.bottes_3;
            case "bottes_4":
                return R.drawable.bottes_4;
            case "bottes_5":
                return R.drawable.bottes_5;
            case "bottes_6":
                return R.drawable.bottes_6;
            case "bottes_7":
                return R.drawable.bottes_7;
            case "bottes_8":
                return R.drawable.bottes_8;
            case "bottes_9":
                return R.drawable.bottes_9;
            case "bottes_10":
                return R.drawable.bottes_10;

            default:
                return R.drawable.vide; // Image par défaut si aucun cas ne correspond
        }
    }
}