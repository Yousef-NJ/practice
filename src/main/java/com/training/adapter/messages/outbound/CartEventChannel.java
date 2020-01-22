package com.training.adapter.messages.outbound;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CartEventChannel {
    String OUTPUT= "cart-events";

    @Output(OUTPUT)
    public MessageChannel outputEvents();
}
