package com.example.psywell;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.psywell.adapters.PsychologistAdapter;
import com.example.psywell.models.Psychologist;
import java.util.ArrayList;
import java.util.List;

public class PsicologoModalActivity extends AppCompatActivity {

    private RecyclerView psychologistList;
    private RecyclerView timeList;
    private DatePicker datePicker;
    private EditText locationInput, commentsInput;
    private Button acceptAppointmentButton;
    private TextView noPsychologists, selectDateLabel, successMessage;

    private List<Psychologist> psychologists = new ArrayList<>();
    private Psychologist selectedPsychologist = null;
    private String selectedTime = "";
    private String selectedDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psicologo_modal);

        // Inicialización de vistas
        psychologistList = findViewById(R.id.psychologist_list);
        timeList = findViewById(R.id.time_list);
        datePicker = findViewById(R.id.date_picker);
        locationInput = findViewById(R.id.location_input);
        commentsInput = findViewById(R.id.comments_input);
        acceptAppointmentButton = findViewById(R.id.accept_appointment_button);
        noPsychologists = findViewById(R.id.no_psychologists);
        selectDateLabel = findViewById(R.id.select_date_label);
        successMessage = findViewById(R.id.success_message);

        psychologistList.setLayoutManager(new LinearLayoutManager(this));
        timeList.setLayoutManager(new LinearLayoutManager(this));

        // Cargar la lista de psicólogos
        loadPsychologists();

        // Configurar el botón de aceptar cita
        acceptAppointmentButton.setOnClickListener(v -> acceptAppointment());
    }

    private void loadPsychologists() {
        // Aquí cargamos psicólogos de una fuente de datos (Firebase o Mocked para pruebas)
        // Simulación de datos
        psychologists.add(new Psychologist("Dr. Juan Pérez", "09:00 - 09:59", "10:00 - 10:59"));
        psychologists.add(new Psychologist("Dra. Ana López", "11:00 - 11:59", "14:00 - 14:59"));

        if (psychologists.isEmpty()) {
            noPsychologists.setVisibility(View.VISIBLE);
        } else {
            noPsychologists.setVisibility(View.GONE);
            psychologistList.setAdapter(new PsychologistAdapter(psychologists, this::selectPsychologist));
        }
    }

    private void selectPsychologist(Psychologist psychologist) {
        this.selectedPsychologist = psychologist;
        selectDateLabel.setVisibility(View.VISIBLE);
        datePicker.setVisibility(View.VISIBLE);
    }

    private void acceptAppointment() {
        if (selectedPsychologist == null || selectedTime.isEmpty() || selectedDate.isEmpty()) {
            showAlert("Error", "Por favor selecciona un psicólogo, fecha y hora para continuar.");
            return;
        }

        // Procesar datos de la cita y guardarlos en la base de datos o backend
        successMessage.setText("¡Cita con " + selectedPsychologist.getName() + " tomada exitosamente!");
        successMessage.setVisibility(View.VISIBLE);
    }

    private void showAlert(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }
}
