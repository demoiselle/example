package org.demoiselle.biblia.cloud;

/**
 *
 * @author paulo
 */
public class CloudMessage {

    private CloudNotification notification;
    private String priority;
    private String to;
    private Boolean content_available;

    public CloudNotification getNotification() {
        return notification;
    }

    public void setNotification(CloudNotification notification) {
        this.notification = notification;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Boolean getContent_available() {
        return content_available;
    }

    public void setContent_available(Boolean content_available) {
        this.content_available = content_available;
    }

}
