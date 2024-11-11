package com.example.psywell.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.psywell.models.Psychologist;
import com.example.psywell.R;
import java.util.List;

public class PsychologistAdapter extends RecyclerView.Adapter<PsychologistAdapter.PsychologistViewHolder> {

    private final List<Psychologist> psychologists;
    private final OnPsychologistSelectedListener listener;

    public PsychologistAdapter(List<Psychologist> psychologists, OnPsychologistSelectedListener listener) {
        this.psychologists = psychologists;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PsychologistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_psychologist, parent, false);
        return new PsychologistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PsychologistViewHolder holder, int position) {
        Psychologist psychologist = psychologists.get(position);
        holder.psychologistName.setText(psychologist.getName());
        holder.itemView.setOnClickListener(v -> listener.onPsychologistSelected(psychologist));
    }

    @Override
    public int getItemCount() {
        return psychologists.size();
    }

    public static class PsychologistViewHolder extends RecyclerView.ViewHolder {
        TextView psychologistName;

        public PsychologistViewHolder(@NonNull View itemView) {
            super(itemView);
            psychologistName = itemView.findViewById(R.id.psychologist_name);
        }
    }

    public interface OnPsychologistSelectedListener {
        void onPsychologistSelected(Psychologist psychologist);
    }
}
