package io.github.likewhat.springbase.web.dto;


import org.springframework.util.Assert;

public class Message {

    /**
     * Message type
     */
    private String type;

    /**
     * Message text
     */
    private String text;


    public Message(MessageType type, String text) {
        this.type = type.toString();
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public static MessageBuilder builder() {
        return new MessageBuilder();
    }

    public static class MessageBuilder {
        private MessageType type = MessageType.SUCCESS;
        private String text;

        public MessageBuilder withType(MessageType type) {
            this.type = type;
            return this;
        }

        public MessageBuilder withText(String text) {
            Assert.hasText(text, "Text must not be empty!");
            this.text = text;
            return this;
        }

        public Message build() {
            return new Message(this.type, this.text);
        }
    }
}
