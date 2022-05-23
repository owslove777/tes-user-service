package com.skcc.tes.userservice.infrastructure.adapters.kafka;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface KafkaProcessor {

    String OUTPUT = "event-out";

    @Output(OUTPUT)
    MessageChannel outboundTopic();

}