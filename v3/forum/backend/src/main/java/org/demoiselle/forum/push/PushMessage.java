package org.demoiselle.forum.push;

import java.io.Serializable;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author gladson
 */
public class PushMessage implements Serializable {
    private static final Logger LOG = getLogger(PushMessage.class.getName());

    private String event;
    private String data;

    /**
     *
     * @param event
     * @param data
     */
    public PushMessage(String event, String data) {
        this.event = event;
        this.data = data;
    }

    /**
     *
     * @return
     */
    public String getEvent() {
        return event;
    }

    /**
     *
     * @param event
     */
    public void setEvent(String event) {
        this.event = event;
    }

    /**
     *
     * @return
     */
    public String getData() {
        return data;
    }

    /**
     *
     * @param data
     */
    public void setData(String data) {
        this.data = data;
    }

}
