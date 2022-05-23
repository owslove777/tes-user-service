package com.skcc.tes.userservice.domain.ports.spi;

public interface UserMessagePort {
    String toJson();

    void publish(String json);

    void publish();

    void publishAfterCommit();

    String getEventType();

    void setEventType(String eventType);

    Long getTimestamp();

    void setTimestamp(Long timestamp);

    boolean validate();
}
