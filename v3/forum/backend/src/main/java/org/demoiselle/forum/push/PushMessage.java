package org.demoiselle.forum.push;

import java.io.Serializable;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author SERPRO
 */
public class PushMessage implements Serializable {
    private static final Logger LOG = getLogger(PushMessage.class.getName());

    private String event;
    private String data;

    public PushMessage(String event, String data) {
        this.event = event;
        this.data = data;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
