// CitasService.java
package com.example.psywell.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Body;
import retrofit2.http.Path;

public interface CitasService {
    @GET("/listarCitas")
    Call<List<ListaCitasResponse>> listarCitas();

    @POST("/registrarCita")
    Call<Void> registrarCita(@Body AppointmentData appointmentData);

    @GET("/disponibilidad/{idPsicologo}/{fecha}")
    Call<List<DisponibilidadInput>> obtenerDisponibilidad(
            @Path("idPsicologo") int idPsicologo,
            @Path("fecha") String fecha
    );
}
