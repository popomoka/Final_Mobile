package com.example.projet_final_mobile;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_final_mobile.Monster;

import java.util.List;

// Déclaration de la classe monstreList qui hérite de RecyclerView.Adapter
// Cette classe gère l'adaptation d'une liste de "Monstres" pour un RecyclerView.
public class monstreList extends RecyclerView.Adapter<monstreList.MonsterViewHolder> {

    // Liste des monstres qui seront affichés dans le RecyclerView
    private List<Monster> monsterList;
    // Listener pour gérer les clics sur les éléments de la liste
    private OnMonsterClickListener clickListener;

    // Interface définissant un callback pour les clics sur un élément
    public interface OnMonsterClickListener {
        void onMonsterClick(Monster monster); // Méthode appelée lorsqu'un monstre est cliqué
    }

    // Constructeur pour initialiser la liste des monstres et le listener de clic
    public monstreList(List<Monster> monsterList, OnMonsterClickListener clickListener) {
        this.monsterList = monsterList;
        this.clickListener = clickListener;
    }

    // Méthode appelée pour créer un nouveau ViewHolder
    @NonNull
    @Override
    public MonsterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Création de la vue pour un élément de la liste
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false); // Utilise un layout par défaut
        // Retourne un nouveau ViewHolder contenant cette vue
        return new MonsterViewHolder(view);
    }

    // Méthode appelée pour lier un élément de la liste à un ViewHolder
    @Override
    public void onBindViewHolder(@NonNull MonsterViewHolder holder, int position) {
        // Récupère le monstre correspondant à la position actuelle
        Monster monster = monsterList.get(position);
        // Définit le texte du TextView (ici, l'image du monstre est utilisée comme texte)
        holder.textView.setText(monster.image);
        // Définit un listener de clic sur l'élément
        holder.itemView.setOnClickListener(v -> clickListener.onMonsterClick(monster));
    }

    // Retourne le nombre total d'éléments dans la liste
    @Override
    public int getItemCount() {
        return monsterList.size();
    }

    // Classe interne représentant un ViewHolder pour un élément de la liste
    static class MonsterViewHolder extends RecyclerView.ViewHolder {
        // Référence au TextView qui affiche les données d'un monstre
        TextView textView;

        // Constructeur pour initialiser la vue
        public MonsterViewHolder(@NonNull View itemView) {
            super(itemView);
            // Récupère le TextView défini dans le layout utilisé (simple_list_item_1 ici)
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
}


