/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estacionamento.socket;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.EncodeException;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author 70744416353
 */
@ServerEndpoint(value = "/ws/vaga/{room}")
public class Vaga {

    private final Logger log = Logger.getLogger(getClass().getName());

    @OnOpen
    public void open(final Session session, @PathParam("room") final String room) {
        log.info("Vagas no " + room + " usuario " + session.getId());
        session.getUserProperties().put("room", room);
    }

    @OnMessage
    public void onMessage(final Session session, final String message) {
        String room = (String) session.getUserProperties().get("room");
        try {
            log.log(Level.INFO, message);
            for (Session s : session.getOpenSessions()) {
                if (s.isOpen() && room.equals(s.getUserProperties().get("room"))) {
                    s.getBasicRemote().sendText(message);
                    log.log(Level.INFO, "foi "+s.getId());
                }
            }
        } catch (IOException e) {
            log.log(Level.WARNING, "onMessage failed", e);
        }
    }
}
