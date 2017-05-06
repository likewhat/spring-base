package io.github.likewhat.springbase.web.dto;


/**
 * Message type for different message level.
 */
public enum MessageType {
    SUCCESS,
    INFO,
    WARNING,
    ERROR;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
