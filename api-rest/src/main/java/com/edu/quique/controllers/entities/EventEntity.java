package com.edu.quique.controllers.entities;

import com.edu.quique.sse.core.bean.BrokerEntity;

import java.time.OffsetDateTime;

public class EventEntity extends BrokerEntity<OffsetDateTime, EventResponse> {
  public EventEntity(OffsetDateTime messageId, EventResponse payload) {
    super(messageId, payload);
  }
}
