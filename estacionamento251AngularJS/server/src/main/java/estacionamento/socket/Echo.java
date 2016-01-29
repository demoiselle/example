/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estacionamento.socket;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author gladson
 */
@ServerEndpoint("/ws/echo")
public class Echo {
    private static final Logger LOG = Logger.getLogger(Echo.class.getName());

    @OnMessage
    public String sayHello(String name) {
        return ("echo " + name);
    }

    @OnOpen
    public void helloOnOpen(Session session) {
        LOG.log(Level.INFO, "WebSocket opened: {0}", session.getId());
    }

    @OnClose
    public void helloOnClose(CloseReason reason) {
        LOG.log(Level.INFO, "Closing a WebSocket due to {0}", reason.getReasonPhrase());
    }

}
