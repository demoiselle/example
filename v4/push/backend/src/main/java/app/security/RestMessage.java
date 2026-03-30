package app.security;

import java.io.Serializable;

/**
 *
 * @author SERPRO
 */
public class RestMessage implements Serializable {

    private String event;
    private String recipient;
    private String message;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
