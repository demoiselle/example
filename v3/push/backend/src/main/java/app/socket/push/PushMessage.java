/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.socket.push;

import java.io.Serializable;

/**
 *
 * @author gladson
 */
public class PushMessage implements Serializable {

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
