package org.demoiselle.forum.cloud;

/**
 *
 * @author paulo
 */
public class CloudMessage {

    private CloudNotification notification;
    private String priority;
    private String to;
    private Boolean content_available;

    /**
     *
     * @return
     */
    public CloudNotification getNotification() {
        return notification;
    }

    /**
     *
     * @param notification
     */
    public void setNotification(CloudNotification notification) {
        this.notification = notification;
    }

    /**
     *
     * @return
     */
    public String getTo() {
        return to;
    }

    /**
     *
     * @param to
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     *
     * @return
     */
    public String getPriority() {
        return priority;
    }

    /**
     *
     * @param priority
     */
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
