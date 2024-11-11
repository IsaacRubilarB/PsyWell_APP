// UsersService.java
package com.example.psywell.services;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UsersService {
    @POST("/agregarUsuario")
    Call<Void> registrarUsuario(@Body UserData userData);

    @GET("/ListarUsuarios")
    Call<List<UserData>> obtenerUsuarios();
}
