package com.edu.quique.controllers.adapters;

import com.edu.quique.application.domain.RegistryAbsence;
import com.edu.quique.controllers.api.EventApi;
import com.edu.quique.controllers.entities.EventEntity;
import com.edu.quique.controllers.entities.EventResponse;
import com.edu.quique.sse.impl.broker.SseEmiterBroker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.time.OffsetDateTime;

@Slf4j
@RestController
@RequiredArgsConstructor
public class EventControllerAdapter implements EventApi {
  private final String USER_TOPIC_MASK = "events/users/%s";
  private final SseEmiterBroker broker;

  @Override
  public SseEmitter userEvents(String userId, Long lastEventId) {
    String topic = String.format(USER_TOPIC_MASK, userId);
    log.info("New connection for topic: '{}' ", topic);

    SseEmitter emitter = new SseEmitter();
    broker.subscribe(topic, emitter);

    return emitter;
  }

  @EventListener(RegistryAbsence.class)
  public void listen(RegistryAbsence event) {
    String topicDestination = String.format(USER_TOPIC_MASK, event.getTeacherGuard().getEmail());
    EventEntity eventEntity =
        new EventEntity(
            OffsetDateTime.now(),
            EventResponse.builder().registryAbsence(event).build());
    log.info("Evento publicado: {}", event.getTeacherGuard().getEmail());
    broker.publish(topicDestination, eventEntity);
  }
}
