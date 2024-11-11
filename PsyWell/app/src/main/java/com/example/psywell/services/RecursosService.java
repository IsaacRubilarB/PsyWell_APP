// RecursosService.java
package com.example.psywell.services;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.List;
import com.google.firebase.firestore.CollectionReference;
import com.google.android.gms.tasks.Task;

public class RecursosService {
    private FirebaseFirestore firestore;

    public RecursosService() {
        firestore = FirebaseFirestore.getInstance();
    }

    public Task<QuerySnapshot> obtenerSonidos() {
        CollectionReference soundsCollection = firestore.collection("audios");
        return soundsCollection.get();
    }

    public Task<QuerySnapshot> obtenerVideos() {
        CollectionReference videosCollection = firestore.collection("recursos-materiales");
        return videosCollection.get();
    }

    public Task<QuerySnapshot> obtenerLibros() {
        CollectionReference booksCollection = firestore.collection("libros");
        return booksCollection.get();
    }
}
