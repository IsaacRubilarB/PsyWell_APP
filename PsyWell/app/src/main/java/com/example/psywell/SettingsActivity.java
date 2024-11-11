package com.example.psywell;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private ImageView profileAvatar;
    private TextView profileName, profileCondition, profileAge;
    private Button editProfileButton;
    private Button btnNotifications, btnSoundPreferences, btnLanguage, btnPrivacy, btnAbout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Inicializar vistas
        profileAvatar = findViewById(R.id.profile_avatar);
        profileName = findViewById(R.id.profile_name);
        profileCondition = findViewById(R.id.profile_condition);
        profileAge = findViewById(R.id.profile_age);
        editProfileButton = findViewById(R.id.edit_profile_button);

        btnNotifications = findViewById(R.id.btn_notifications);
        btnSoundPreferences = findViewById(R.id.btn_sound_preferences);
        btnLanguage = findViewById(R.id.btn_language);
        btnPrivacy = findViewById(R.id.btn_privacy);
        btnAbout = findViewById(R.id.btn_about);

        // Configurar eventos de clic
        editProfileButton.setOnClickListener(v -> editProfile());
        btnNotifications.setOnClickListener(v -> showNotificationsDialog());
        btnSoundPreferences.setOnClickListener(v -> showSoundPreferencesDialog());
        btnLanguage.setOnClickListener(v -> showLanguageDialog());
        btnPrivacy.setOnClickListener(v -> showPrivacyDialog());
        btnAbout.setOnClickListener(v -> showAboutDialog());
    }

    private void editProfile() {
        // Abre la actividad o el modal para editar perfil
    }

    private void showNotificationsDialog() {
        // Mostrar dialogo para configurar notificaciones
    }

    private void showSoundPreferencesDialog() {
        // Mostrar dialogo para preferencias de sonido
    }

    private void showLanguageDialog() {
        // Mostrar dialogo para cambiar idioma
    }

    private void showPrivacyDialog() {
        // Mostrar dialogo para privacidad y seguridad
    }

    private void showAboutDialog() {
        // Mostrar dialogo con información de la aplicación
    }
}
