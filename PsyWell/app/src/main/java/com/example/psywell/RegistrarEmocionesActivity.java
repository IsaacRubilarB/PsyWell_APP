package com.example.psywell;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RegistrarEmocionesActivity extends AppCompatActivity {

    private ImageView emotionAngry, emotionSad, emotionNeutral, emotionHappy, emotionVeryHappy;
    private String selectedEmotion = "";
    private EditText commentBox;
    private Switch remindMedicationSwitch;
    private Button saveButton;
    private LinearLayout keywordsSection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_emociones);

        // Inicializar vistas
        emotionAngry = findViewById(R.id.emotion_angry);
        emotionSad = findViewById(R.id.emotion_sad);
        emotionNeutral = findViewById(R.id.emotion_neutral);
        emotionHappy = findViewById(R.id.emotion_happy);
        emotionVeryHappy = findViewById(R.id.emotion_very_happy);
        commentBox = findViewById(R.id.comment_box);
        remindMedicationSwitch = findViewById(R.id.remind_medication_switch);
        saveButton = findViewById(R.id.save_button);
        keywordsSection = findViewById(R.id.keywords_section);

        // Configurar botones de emociones
        setEmotionListeners();

        // Configurar botón de guardar
        saveButton.setOnClickListener(v -> saveEmotion());
    }

    private void setEmotionListeners() {
        View.OnClickListener listener = view -> {
            resetSelectedEmotion();
            switch (view.getId()) {
                case R.id.emotion_angry:
                    selectedEmotion = "Muy enojado";
                    emotionAngry.setAlpha(1f);
                    break;
                case R.id.emotion_sad:
                    selectedEmotion = "Molesto";
                    emotionSad.setAlpha(1f);
                    break;
                case R.id.emotion_neutral:
                    selectedEmotion = "Neutral";
                    emotionNeutral.setAlpha(1f);
                    break;
                case R.id.emotion_happy:
                    selectedEmotion = "Feliz";
                    emotionHappy.setAlpha(1f);
                    break;
                case R.id.emotion_very_happy:
                    selectedEmotion = "Muy Feliz!";
                    emotionVeryHappy.setAlpha(1f);
                    break;
            }
            showKeywordsForEmotion(selectedEmotion);
        };

        emotionAngry.setOnClickListener(listener);
        emotionSad.setOnClickListener(listener);
        emotionNeutral.setOnClickListener(listener);
        emotionHappy.setOnClickListener(listener);
        emotionVeryHappy.setOnClickListener(listener);
    }

    private void resetSelectedEmotion() {
        emotionAngry.setAlpha(0.5f);
        emotionSad.setAlpha(0.5f);
        emotionNeutral.setAlpha(0.5f);
        emotionHappy.setAlpha(0.5f);
        emotionVeryHappy.setAlpha(0.5f);
    }

    private void showKeywordsForEmotion(String emotion) {
        keywordsSection.removeAllViews();
        String[] keywords;
        switch (emotion) {
            case "Muy enojado":
                keywords = new String[]{"Discutí con alguien", "Me siento frustrado", "Tuve un mal día"};
                break;
            case "Molesto":
                keywords = new String[]{"Me siento decepcionado", "Problemas en el trabajo"};
                break;
            case "Neutral":
                keywords = new String[]{"Día normal", "Todo tranquilo"};
                break;
            case "Feliz":
                keywords = new String[]{"Me hicieron un cumplido", "Pasé tiempo con amigos", "Tuve un buen día"};
                break;
            case "Muy Feliz!":
                keywords = new String[]{"Logré una meta", "Recibí buenas noticias"};
                break;
            default:
                keywords = new String[]{};
        }

        for (String keyword : keywords) {
            TextView keywordView = new TextView(this);
            keywordView.setText(keyword);
            keywordView.setTextSize(16);
            keywordView.setPadding(10, 10, 10, 10);
            keywordView.setBackgroundResource(R.drawable.keyword_background);
            keywordView.setTextColor(getResources().getColor(android.R.color.white));
            keywordView.setOnClickListener(v -> addKeywordToComment(keyword));
            keywordsSection.addView(keywordView);
        }
        keywordsSection.setVisibility(View.VISIBLE);
    }

    private void addKeywordToComment(String keyword) {
        String currentText = commentBox.getText().toString();
        if (!currentText.isEmpty()) {
            commentBox.setText(keyword + ", " + currentText);
        } else {
            commentBox.setText(keyword);
        }
    }

    private void saveEmotion() {
        String emotion = selectedEmotion;
        String comment = commentBox.getText().toString();
        boolean remindMedication = remindMedicationSwitch.isChecked();

        // Aquí agregarías la lógica para guardar la emoción, comentarios y recordatorio de pastillas
        // en la base de datos o enviar al backend.

        // Resetea el formulario después de guardar
        resetForm();
    }

    private void resetForm() {
        selectedEmotion = "";
        commentBox.setText("");
        remindMedicationSwitch.setChecked(false);
        resetSelectedEmotion();
        keywordsSection.setVisibility(View.GONE);
    }
}
