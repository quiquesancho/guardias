package com.edu.quique.controllers.adapters;

import com.edu.quique.api.DocentesApi;
import com.edu.quique.api.model.Docente;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class DocentController implements DocentesApi {

    @Override
    public ResponseEntity<List<Docente>> docentesGet() {
        return ResponseEntity.ok(List.of());
    }
}