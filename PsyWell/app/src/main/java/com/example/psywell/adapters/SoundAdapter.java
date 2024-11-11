package com.example.psywell.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.psywell.R;
import java.util.List;

public class SoundAdapter extends RecyclerView.Adapter<SoundAdapter.SoundViewHolder> {

    private List<Sound> sounds;
    private Context context;
    private OnSoundClickListener listener;

    public interface OnSoundClickListener {
        void onSoundClick(Sound sound);
    }

    public SoundAdapter(Context context, List<Sound> sounds, OnSoundClickListener listener) {
        this.context = context;
        this.sounds = sounds;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SoundViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sound, parent, false);
        return new SoundViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SoundViewHolder holder, int position) {
        Sound sound = sounds.get(position);
        holder.title.setText(sound.getTitle());
        holder.icon.setImageResource(R.drawable.musical_note); // Cambia según el ícono adecuado

        holder.itemView.setOnClickListener(v -> listener.onSoundClick(sound));
    }

    @Override
    public int getItemCount() {
        return sounds.size();
    }

    public static class SoundViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView icon;

        public SoundViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.sound_title);
            icon = itemView.findViewById(R.id.sound_icon);
        }
    }
}
