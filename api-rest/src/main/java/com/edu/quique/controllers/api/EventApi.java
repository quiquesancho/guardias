package com.edu.quique.controllers.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Tag(name = "Events", description = "Servicios para suscribirse a los eventos")
public interface EventApi {
  @Operation(summary = "Suscripción a los eventos dirigidos a un usuario")
  @GetMapping(value = "/events/users/{userId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  SseEmitter userEvents(
      @PathVariable("userId") String userId,
      @RequestParam(value = "lastEventId", required = false) Long lastEventId);
}
