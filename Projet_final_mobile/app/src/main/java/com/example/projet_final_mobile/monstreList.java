package com.example.projet_final_mobile;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_final_mobile.Monster;

import java.util.List;

public class monstreList extends RecyclerView.Adapter<monstreList.MonsterViewHolder> {

    private List<Monster> monsterList;
    private OnMonsterClickListener clickListener;

    public interface OnMonsterClickListener {
        void onMonsterClick(Monster monster);
    }

    public monstreList(List<Monster> monsterList, OnMonsterClickListener clickListener) {
        this.monsterList = monsterList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MonsterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new MonsterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonsterViewHolder holder, int position) {
        Monster monster = monsterList.get(position);
        holder.textView.setText(monster.image);
        holder.itemView.setOnClickListener(v -> clickListener.onMonsterClick(monster));
    }

    @Override
    public int getItemCount() {
        return monsterList.size();
    }

    static class MonsterViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MonsterViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
}

