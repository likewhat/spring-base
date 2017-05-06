package io.github.likewhat.springbase.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;


@Entity
public class Email extends AbstractAuditingEntity<Long> {

    @Column(nullable = false)
    private Long receiverId;


    @Column(length = 64, nullable = false)
    private String sendTo;


    @Column(length = 128, nullable = false)
    private String subject;

    @Lob
    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private boolean success;

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
