package com.example.psywell;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.psywell.adapters.CitasAdapter;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private ImageView patientAvatar;
    private TextView patientName, patientEmail;
    private TextView bpmValue, sleepHours, waterGlasses, stressLevel;
    private RecyclerView recyclerCitas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Inicializar vistas
        patientAvatar = findViewById(R.id.patient_avatar);
        patientName = findViewById(R.id.patient_name);
        patientEmail = findViewById(R.id.patient_email);

        bpmValue = findViewById(R.id.bpm_value);
        sleepHours = findViewById(R.id.sleep_hours);
        waterGlasses = findViewById(R.id.water_glasses);
        stressLevel = findViewById(R.id.stress_level);

        recyclerCitas = findViewById(R.id.recycler_citas);
        recyclerCitas.setLayoutManager(new LinearLayoutManager(this));

        // Configurar adaptador para citas
        CitasAdapter citasAdapter = new CitasAdapter(getDummyCitas()); // Reemplazar con citas reales
        recyclerCitas.setAdapter(citasAdapter);

        // Cargar datos de usuario y datos fisiológicos
        loadPatientData();
        loadPhysiologicalData();
    }

    private void loadPatientData() {
        // Aquí puedes obtener los datos del paciente desde una base de datos o autenticación.
        patientName.setText(getString(R.string.patient_name));  // Reemplazar con datos reales
        patientEmail.setText(getString(R.string.patient_email));

        // Lógica para cambiar el avatar del paciente
        patientAvatar.setOnClickListener(v -> {
            // Abre un selector de imágenes para cambiar el avatar
            // Implementar la funcionalidad según los requisitos del proyecto
        });
    }

    private void loadPhysiologicalData() {
        // Cargar datos fisiológicos (ejemplo de valores estáticos)
        bpmValue.setText("72");           // Reemplazar con valor real de bpm
        sleepHours.setText("7.5");        // Reemplazar con horas de sueño
        waterGlasses.setText("8");        // Reemplazar con vasos de agua
        stressLevel.setText("Moderado");  // Reemplazar con nivel de estrés
    }

    // Método para generar citas de prueba (dummy), reemplazar con datos reales
    private List<Cita> getDummyCitas() {
        List<Cita> citas = new ArrayList<>();
        citas.add(new Cita("10-11-2024", "Consulta general", "En proceso"));
        citas.add(new Cita("15-11-2024", "Terapia semanal", "Confirmada"));
        return citas;
    }
}
