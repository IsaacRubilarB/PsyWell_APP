package com.example.psywell;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.psywell.models.Audio;
import com.example.psywell.models.Libro;
import com.example.psywell.models.Video;
import java.io.IOException;
import java.util.List;

public class RecursosActivity extends AppCompatActivity {

    private LinearLayout soundSection, videoSection, bookSection;
    private MediaPlayer mediaPlayer;
    private ProgressBar progressBar;
    private String activeSoundUrl = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recursos);

        // Inicializar secciones
        soundSection = findViewById(R.id.sound_section);
        videoSection = findViewById(R.id.video_section);
        bookSection = findViewById(R.id.book_section);

        // Cargar datos de recursos
        loadSounds();
        loadVideos();
        loadBooks();
    }

    private void loadSounds() {
        List<Audio> sonidos = getSonidosFromDatabase(); // Método que simula obtener datos de la base de datos
        for (Audio sound : sonidos) {
            View audioCard = getLayoutInflater().inflate(R.layout.audio_card, null);
            TextView title = audioCard.findViewById(R.id.audio_title);
            title.setText(sound.getTitulo());
            ProgressBar audioProgressBar = audioCard.findViewById(R.id.audio_progress);

            audioCard.setOnClickListener(v -> {
                toggleAudio(sound.getUrl(), audioProgressBar);
            });

            soundSection.addView(audioCard);
        }
    }

    private void loadVideos() {
        List<Video> videos = getVideosFromDatabase();
        for (Video video : videos) {
            View videoCard = getLayoutInflater().inflate(R.layout.video_card, null);
            TextView title = videoCard.findViewById(R.id.video_title);
            title.setText(video.getTitulo());

            videoCard.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(video.getUrl()));
                startActivity(intent);
            });

            videoSection.addView(videoCard);
        }
    }

    private void loadBooks() {
        List<Libro> libros = getLibrosFromDatabase();
        for (Libro libro : libros) {
            View bookCard = getLayoutInflater().inflate(R.layout.book_card, null);
            TextView title = bookCard.findViewById(R.id.book_title);
            TextView author = bookCard.findViewById(R.id.book_author);
            ImageView cover = bookCard.findViewById(R.id.book_cover);

            title.setText(libro.getTitulo());
            author.setText(libro.getAutor());
            cover.setImageResource(R.drawable.default_cover); // Colocar una imagen por defecto

            bookCard.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(libro.getUrl()));
                startActivity(intent);
            });

            bookSection.addView(bookCard);
        }
    }

    private void toggleAudio(String url, ProgressBar progressBar) {
        if (activeSoundUrl != null && activeSoundUrl.equals(url)) {
            // Pausar si ya está reproduciendo
            stopAudio();
            progressBar.setProgress(0);
        } else {
            // Reproducir nuevo sonido
            stopAudio();
            playAudio(url, progressBar);
            activeSoundUrl = url;
        }
    }

    private void playAudio(String url, ProgressBar progressBar) {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            mediaPlayer.start();
            progressBar.setMax(mediaPlayer.getDuration());

            mediaPlayer.setOnBufferingUpdateListener((mp, percent) -> {
                progressBar.setSecondaryProgress(percent);
            });

            mediaPlayer.setOnCompletionListener(mp -> {
                progressBar.setProgress(0);
                activeSoundUrl = null;
            });

            // Hilo para actualizar el progreso
            new Thread(() -> {
                while (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    try {
                        progressBar.setProgress(mediaPlayer.getCurrentPosition());
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stopAudio() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            activeSoundUrl = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopAudio(); // Liberar recursos del reproductor
    }

    // Métodos para obtener datos simulados de sonidos, videos y libros
    private List<Audio> getSonidosFromDatabase() {
        // Simular la obtención de sonidos de la base de datos
        return List.of(new Audio("Sonido Relajante 1", "url1"), new Audio("Sonido Relajante 2", "url2"));
    }

    private List<Video> getVideosFromDatabase() {
        // Simular la obtención de videos de la base de datos
        return List.of(new Video("Video Ayuda 1", "https://youtube.com/..."), new Video("Video Ayuda 2", "https://youtube.com/..."));
    }

    private List<Libro> getLibrosFromDatabase() {
        // Simular la obtención de libros de la base de datos
        return List.of(new Libro("Libro 1", "https://linkdelibro.com", "Autor 1", null));
    }
}
