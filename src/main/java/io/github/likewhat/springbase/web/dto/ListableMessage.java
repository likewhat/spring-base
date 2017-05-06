package io.github.likewhat.springbase.web.dto;


import org.springframework.util.Assert;

import java.util.List;


public class ListableMessage {

    private String type;

    private String header;

    private List<String> messages;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ListableMessage withType(MessageType mt) {
        Assert.notNull(mt, "MessageType must not be null");
        setType(mt.toString());
        return this;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }


    public ListableMessage withHeader(String header) {
        Assert.hasText(header, "MessageType must not be null");
        setHeader(header);
        return this;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public ListableMessage withMessages(List<String> messages) {
        Assert.notNull(messages, "MessageType must not be null");
        setMessages(messages);
        return this;
    }
}
