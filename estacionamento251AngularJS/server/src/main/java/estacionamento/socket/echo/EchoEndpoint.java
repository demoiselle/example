/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estacionamento.socket.echo;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author gladson
 */
@ServerEndpoint(value = "/ws/echo", encoders = EchoMessageEncoder.class, decoders = EchoMessageDecoder.class)
public class EchoEndpoint {

    static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());
    private final Logger log = Logger.getLogger(getClass().getName());

    @OnOpen
    public void open(final Session session) throws EncodeException {
        peers.add(session);
    }

    @OnMessage
    public void onMessage(final Session session, final EchoMessage message) throws EncodeException {
        try {
            if (message.getEvent().equalsIgnoreCase("qtde")) {
                send(new Gson().toJson((new EchoMessage("Acessando", "" + peers.size()))));
            }

            if (message.getEvent().equalsIgnoreCase("msg")) {
                send(new Gson().toJson(message));
            }

        } catch (IOException e) {
            log.log(Level.WARNING, "onMessage failed", e);
        }
    }

    @OnClose
    public void closedConnection(final Session session) throws EncodeException {
        peers.remove(session);
    }

    public void send(String texto) throws IOException {
        for (Session s : peers) {
            if (s.isOpen()) {
                s.getBasicRemote().sendText(texto);
                log.log(Level.INFO, "MSG {0}", s.getId());
            }
        }
    }

}
