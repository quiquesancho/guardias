package com.edu.quique.controllers.adapters;

import com.edu.quique.api.DocumentApi;
import com.edu.quique.application.ports.in.usecases.UpdateXMLUseCasePort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@Slf4j
public class DocumentController implements DocumentApi {

    private UpdateXMLUseCasePort updateXMLUseCasePort;
    @Override
    public ResponseEntity<Void> updateXML(MultipartFile file) {
        log.info("POST /document/updateXML");
        try {
            updateXMLUseCasePort.execute(file.getInputStream());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }
}
