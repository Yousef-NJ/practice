package com.training.adapter.messages.inbound;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ItemInputChannel {

    String INPUT="item-queue";

    @Input(INPUT)
    public SubscribableChannel input();
}
