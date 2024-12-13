package com.example.projet_final_mobile;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class Game extends AppCompatActivity {

    public Perso perso;
    public Monster monstre;
    boolean combatIsOver;
    String vitesse;
    List<Item> listeItems = new ArrayList<>();
    List<Monster> listeMonstre = new ArrayList<>();
    Handler handler = new Handler(Looper.getMainLooper());
    Item[] ItemSelection = new Item[3];
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //user = (User)getIntent().getSerializableExtra("USER");

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.game_activity);
        vitesse = "normal";
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

        Button buttonquit = (Button)findViewById(R.id.Quitter);
        buttonquit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //quit
            }
        });

        Button buttonvitesse = (Button)findViewById(R.id.vitesse);
        buttonvitesse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(buttonvitesse.getText()=="Vitesse : normal")
                {
                    vitesse = "rapide";
                    buttonvitesse.setText("Vitesse : rapide");
                }
                else
                {
                    vitesse = "normal";
                    buttonvitesse.setText("Vitesse : normal");
                }
            }
        });

        Button buttonsmallpv = (Button)findViewById(R.id.smallpvButton);
        buttonsmallpv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                perso.pv_actuel += 3;
                UpdateStats();
                HideButtonBoissons();
            }
        });

        Button buttonBigPv = (Button)findViewById(R.id.bigpvButton);
        buttonBigPv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                perso.pv_actuel += 10;
                UpdateStats();
                HideButtonBoissons();
            }
        });


        Button buttoncrit = (Button)findViewById(R.id.critButton);
        buttoncrit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                perso.critChance += 1;
                UpdateStats();
                HideButtonBoissons();
            }
        });

        ImageView ImageViewItem1 = findViewById(R.id.Item1);
        ImageViewItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView view;
                TextView textview;
                if(ItemSelection[0].type == "Casque")
                {
                    perso.casque = ItemSelection[0];
                    textview = findViewById((R.id.casqueText));
                    view = findViewById(R.id.casqueImage);
                }
                else if(ItemSelection[0].type == "Plastron")
                {
                    perso.plastron = ItemSelection[0];
                    textview = findViewById((R.id.plastronText));
                    view = findViewById(R.id.plastronImage);
                }
                else if(ItemSelection[0].type == "Jambière")
                {
                    perso.jambiere = ItemSelection[0];
                    textview = findViewById((R.id.jambiereText));
                    view = findViewById(R.id.jambiereImage);
                }
                else if(ItemSelection[0].type == "Bottes")
                {
                    perso.bottes = ItemSelection[0];
                    textview = findViewById((R.id.bottesText));
                    view = findViewById(R.id.bottesImage);
                }
                else
                {
                    perso.arme = ItemSelection[0];
                    textview = findViewById((R.id.armeText));
                    view = findViewById(R.id.armeImage);
                }
                textview.setText(GetItemTextStat(ItemSelection[0]));
                view.setImageResource(ConversionStringEnDrawableImage(ItemSelection[0].image));
                buttonClickedRecompense();
            }
        });

        ImageView ImageViewItem2 = findViewById(R.id.Item2);
        ImageViewItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView view;
                TextView textview;
                if(ItemSelection[1].type == "Casque")
                {
                    perso.casque = ItemSelection[1];
                    textview = findViewById((R.id.casqueText));
                    view = findViewById(R.id.casqueImage);
                }
                else if(ItemSelection[1].type == "Plastron")
                {
                    perso.plastron = ItemSelection[1];
                    textview = findViewById((R.id.plastronText));
                    view = findViewById(R.id.plastronImage);
                }
                else if(ItemSelection[1].type == "Jambière")
                {
                    perso.jambiere = ItemSelection[1];
                    textview = findViewById((R.id.jambiereText));
                    view = findViewById(R.id.jambiereImage);
                }
                else if(ItemSelection[1].type == "Bottes")
                {
                    perso.bottes = ItemSelection[1];
                    textview = findViewById((R.id.bottesText));
                    view = findViewById(R.id.bottesImage);
                }
                else
                {
                    perso.arme = ItemSelection[1];
                    textview = findViewById((R.id.armeText));
                    view = findViewById(R.id.armeImage);
                }
                textview.setText(GetItemTextStat(ItemSelection[1]));
                view.setImageResource(ConversionStringEnDrawableImage(ItemSelection[1].image));
                buttonClickedRecompense();
            }
        });

        ImageView ImageViewItem3 = findViewById(R.id.Item3);
        ImageViewItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView view;
                TextView textview;
                if(ItemSelection[2].type == "Casque")
                {
                    perso.casque = ItemSelection[2];
                    textview = findViewById((R.id.casqueText));
                    view = findViewById(R.id.casqueImage);
                }
                else if(ItemSelection[2].type == "Plastron")
                {
                    perso.plastron = ItemSelection[2];
                    textview = findViewById((R.id.plastronText));
                    view = findViewById(R.id.plastronImage);
                }
                else if(ItemSelection[2].type == "Jambière")
                {
                    perso.jambiere = ItemSelection[2];
                    textview = findViewById((R.id.jambiereText));
                    view = findViewById(R.id.jambiereImage);
                }
                else if(ItemSelection[2].type == "Bottes")
                {
                    perso.bottes = ItemSelection[2];
                    textview = findViewById((R.id.bottesText));
                    view = findViewById(R.id.bottesImage);
                }
                else
                {
                    perso.arme = ItemSelection[2];
                    textview = findViewById((R.id.armeText));
                    view = findViewById(R.id.armeImage);
                }

                textview.setText(GetItemTextStat(ItemSelection[2]));
                view.setImageResource(ConversionStringEnDrawableImage(ItemSelection[2].image));
                buttonClickedRecompense();
            }
        });
    }

    Runnable Combat = new Runnable() {



        int montour = 1;
        @Override
        public void run() {
            hideHit();
            Random rand = new Random();
            int dodge = rand.nextInt(101);
            int crit = rand.nextInt(101);
            switch(montour)
            {
                case 1:
                    TextView dammageText = findViewById(R.id.monstrehit);
                    if(dodge>=monstre.dodgechance) {
                        if(crit>=perso.critChance){
                            dammageText.setTextColor(Color.parseColor("#FF0000"));
                            GiveDammage(rand.nextInt((perso.dammage_max - perso.dammage_min) + 1) + perso.dammage_min);
                        } else{
                            dammageText.setTextColor(Color.parseColor("#FFFF00"));
                            GiveDammage(2*(rand.nextInt((perso.dammage_max - perso.dammage_min) + 1) + perso.dammage_min));
                        }
                    }
                    else{
                        dammageText.setTextColor(Color.parseColor("#FF0000"));
                        GiveDodge();
                    }
                    break;
                case 0:
                    TextView dammagepersoText = findViewById(R.id.persohit);
                    if(dodge>=perso.dodgeChance) {
                        if(crit>=monstre.critchance){
                            dammagepersoText.setTextColor(Color.parseColor("#FF0000"));
                            TakeDammage(rand.nextInt((monstre.dammage_max - monstre.dammage_min) + 1) + monstre.dammage_min);
                        } else{
                            dammagepersoText.setTextColor(Color.parseColor("#FFFF00"));
                            TakeDammage(2*(rand.nextInt((monstre.dammage_max - monstre.dammage_min) + 1) + monstre.dammage_min));
                        }
                    }
                    else{
                        dammagepersoText.setTextColor(Color.parseColor("#FF0000"));
                        TakeDodge();
                    }
                    break;
            }

            UpdatePv();

            if(montour == 1)
                montour = 0;
            else
                montour = 1;


            if(!combatIsOver)
            {
                if(vitesse=="normal"){
                    handler.postDelayed(Combat, 1200);
                }else{
                    handler.postDelayed(Combat, 600);
                }
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
        TextView view = findViewById(R.id.persohit);
        view.setText("-" + ammount);
        view.setVisibility(View.VISIBLE);

        perso.pv_actuel -= ammount;

        if (perso.pv_actuel <=0)
        {
            combatIsOver = true;
            GAMEOVER();
        }

        UpdatePv();
    }

    void TakeDodge(){
        TextView view = findViewById(R.id.persohit);
        view.setText("DODGE");
        view.setVisibility(View.VISIBLE);
    }

    void GiveDammage(int ammount)
    {
        TextView view = findViewById(R.id.monstrehit);
        view.setText("-" + ammount);
        view.setVisibility(View.VISIBLE);

        monstre.pv_actuel -= ammount;

        if (monstre.pv_actuel <=0)
        {
            combatIsOver = true;
            hideHit();
        }

        UpdatePv();
    }

    void GiveDodge(){
        TextView view = findViewById(R.id.monstrehit);
        view.setText("DODGE");
        view.setVisibility(View.VISIBLE);
    }

    void endround()
    {
        ImageView ImageMonstre = findViewById(R.id.ImageMonstre);
        ImageMonstre.setImageResource(R.drawable.vide);

        //should be random
        Random rand = new Random();

        Item item1 = listeItems.get(rand.nextInt(listeItems.size()));
        Item item2 = listeItems.get(rand.nextInt(listeItems.size()));
        Item item3 = listeItems.get(rand.nextInt(listeItems.size()));

        ItemSelection[0] = item1;
        ItemSelection[1] = item2;
        ItemSelection[2] = item3;

        ImageView item1Image = findViewById(R.id.Item1);
        ImageView item2Image = findViewById(R.id.Item2);
        ImageView item3Image = findViewById(R.id.Item3);

        TextView item1Text = findViewById(R.id.TextItem1);
        TextView item2Text = findViewById(R.id.TextItem2);
        TextView item3Text = findViewById(R.id.TextItem3);

        item1Image.setImageResource(ConversionStringEnDrawableImage(item1.image));
        item2Image.setImageResource(ConversionStringEnDrawableImage(item2.image));
        item3Image.setImageResource(ConversionStringEnDrawableImage(item3.image));

        item1Text.setText(GetItemTextStat(item1));
        item2Text.setText(GetItemTextStat(item2));
        item3Text.setText(GetItemTextStat(item3));

        View recompences = findViewById(R.id.ChoixRecompenses);
        recompences.setVisibility(View.VISIBLE);
    }

    void buttonClickedRecompense()
    {
        View v = findViewById(R.id.ChoixRecompenses);
        View v2 = findViewById(R.id.ButtonStart);
        View v3 = findViewById(R.id.OptionsBoissons);
        v.setVisibility(View.GONE);
        v2.setVisibility(View.VISIBLE);
        v3.setVisibility(View.VISIBLE);
        UpdateStats();
        StartRound();
    }

    void GenerateMonster()
    {
        //genere un id selon la round
        //vas chercher ses stats sur la bd
        Random rand = new Random();
        monstre = listeMonstre.get(rand.nextInt(listeMonstre.size()));
        monstre.pv_actuel = monstre.pv_max;
        ImageView v = findViewById(R.id.ImageMonstre);
        v.setImageResource(ConversionStringEnDrawableImage(monstre.image));
        UpdatePv();
    }

    void StartGame()
    {
        new Thread(() -> {
            listeItems = API_model.GetAllItems();
        }).start();

        new Thread(() -> {
            listeMonstre = API_model.GetAllMonstre();
        }).start();

        //+ upgrades
        perso = new Perso(user.upgradebought[0] , user.upgradebought[1], user.upgradebought[2], user.upgradebought[3], user.upgradebought[4], user.upgradebought[5], null, null, null, null, null);
        //perso = new Perso(0,0,0,0,0,0,null,null,null,null,null);
        UpdateStats();
        StartRound();
    }

    void GAMEOVER()
    {
        Intent intent = new Intent(Game.this, HomeActivity.class);
        intent.putExtra("USERNAME", "pops" );
        startActivity(intent);


        //give recompense
        //load en bd
        //changeScene
    }

    void HideButtonBoissons()
    {
        View buttons = findViewById(R.id.OptionsBoissons);
        buttons.setVisibility(View.GONE);

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
        int monsterPvMax = monstre.pv_max;
        TextView text_pv_stats = findViewById(R.id.text_pv_stats);
        TextView text_pv_stats_monstre = findViewById(R.id.monstrePv);

        text_pv_stats.setText("Points de vie : " + perso.pv_actuel + "/" + pvmax);
        text_pv_stats_monstre.setText(monstre.pv_actuel + "/" + monsterPvMax);
    }

    void hideHit()
    {
        TextView monshit = findViewById(R.id.monstrehit);
        TextView pershit = findViewById(R.id.persohit);
        monshit.setVisibility(View.GONE);
        pershit.setVisibility(View.GONE);
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

            // Blobs
            case "blue_blob":
                return R.drawable.blue_blob;
            case "green_blob":
                return R.drawable.green_blob;
            case "pink_blob":
                return R.drawable.pink_blob;
            case "purple_blob":
                return R.drawable.purple_blob;
            case "red_blob":
                return R.drawable.red_blob;
            case "white_blob":
                return R.drawable.white_blob;
            case "yellow_blob":
                return R.drawable.yellow_blob;

// Cubes
            case "blue_cube":
                return R.drawable.blue_cube;
            case "green_cube":
                return R.drawable.green_cube;
            case "pink_cube":
                return R.drawable.pink_cube;
            case "purple_cube":
                return R.drawable.purple_cube;
            case "red_cube":
                return R.drawable.red_cube;
            case "white_cube":
                return R.drawable.white_cube;
            case "yellow_cube":
                return R.drawable.yellow_cube;

// Eggmen
            case "blue_eggman":
                return R.drawable.blue_eggman;
            case "green_eggman":
                return R.drawable.green_eggman;
            case "pink_eggman":
                return R.drawable.pink_eggman;
            case "purple_eggman":
                return R.drawable.purple_eggman;
            case "red_eggman":
                return R.drawable.red_eggman;
            case "white_eggman":
                return R.drawable.white_eggman;
            case "yellow_eggman":
                return R.drawable.yellow_eggman;

// Gluttons
            case "blue_glutton":
                return R.drawable.blue_glutton;
            case "green_glutton":
                return R.drawable.green_glutton;
            case "pink_glutton":
                return R.drawable.pink_glutton;
            case "purple_glutton":
                return R.drawable.purple_glutton;
            case "red_glutton":
                return R.drawable.red_glutton;
            case "white_glutton":
                return R.drawable.white_glutton;
            case "yellow_glutton":
                return R.drawable.yellow_glutton;


            default:
                return R.drawable.vide; // Image par défaut si aucun cas ne correspond
        }
    }

    String GetItemTextStat(Item item)
    {
        return "dmg : " + item.dammage_min + "-" + item.dammage_max + "\n" + "armor : " + item.armor + "\n" + "pv : " + item.pv + "\n" + "crit : " + item.critChance+ "\n" + "dodge : " + item.dodgeChance;
    }
}