/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.socket.push;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.PathParam;

/**
 *
 * @author gladson
 */
@ServerEndpoint(value = "/push/{channel}", encoders = PushMessageEncoder.class, decoders = PushMessageDecoder.class)
public class PushEndpoint {

    private static final ConcurrentLinkedQueue<Session> peers = new ConcurrentLinkedQueue<>();

    private static final Logger logger = getLogger(PushEndpoint.class.getName());

    @OnOpen
    public void open(final Session session, EndpointConfig c, @PathParam("channel") String channel) {
        peers.add(session);
        session.getUserProperties().putIfAbsent("channel", channel);
        PushMessage mm = new PushMessage("qtde", count());
        sendToSessions(new Gson().toJson(mm));
    }

    @OnMessage
    public void onMessage(final Session session, final PushMessage message) {

        if (message.getEvent().equalsIgnoreCase("login")) {
            if (message.getData() != null && !message.getData().isEmpty()) {
                String[] str = message.getData().split(":");
                session.getUserProperties().putIfAbsent("usuario", str[0]);
                session.getUserProperties().putIfAbsent("grupo", str[1]);
                logger.log(Level.FINE, "Logado - ", message.getData());
            }
        }

        if (message.getEvent().equalsIgnoreCase("logout")) {
            session.getUserProperties().remove("usuario");
            logger.log(Level.FINE, "Deslogado - ", message.getData());
        }

    }

    @OnError
    public void onError(final Session session, Throwable t) {
        peers.remove(session);
        logger.log(Level.SEVERE, "onError failed - Session: " + session.getId(), t);
    }

    @OnClose
    public void closedConnection(final Session session) {
        peers.remove(session);
        PushMessage mm = new PushMessage("qtde", count());
        sendToSessions(new Gson().toJson(mm));
    }

    public String count() {
        return "" + peers.size();
    }

    public List<String> listaUsuarios(String channel) {
        List<String> list = new ArrayList<>();
        peers.stream().parallel().forEach((s) -> {
            if (s.isOpen()) {
                if ((((String) s.getUserProperties().get("usuario")) != null && !((String) s.getUserProperties().get("usuario")).isEmpty())
                        && (channel.equalsIgnoreCase((String) s.getUserProperties().get("channel")))) {
                    list.add(((String) s.getUserProperties().get("usuario") + ":" + s.getUserProperties().get("grupo")));
                }
            } else {
                peers.remove(s);
            }
        });
        return list;
    }

    public List<String> listChannels() {
        List<String> list = new ArrayList<>();
        peers.stream().parallel().forEach((s) -> {
            if (s.isOpen()) {
                if (((String) s.getUserProperties().get("channel")) != null && !((String) s.getUserProperties().get("channel")).isEmpty()) {
                    list.add(((String) s.getUserProperties().get("channel")));
                }
            } else {
                peers.remove(s);
            }
        });
        return list;
    }

    public void sendToChannel(final String texto, final String channel) {
        peers.stream().parallel().forEach((s) -> {
            if (s.isOpen()) {
                if (channel.equalsIgnoreCase((String) s.getUserProperties().get("channel"))) {
                    try {
                        s.getBasicRemote().sendText(texto);
                    } catch (IOException e) {
                        logger.log(Level.SEVERE, "sendToUser failed " + s.getId() + " " + channel + " " + texto, e);
                    }
                }
            } else {
                peers.remove(s);
            }
        });
    }

    public void sendToGroup(final String texto, final String group) {
        peers.stream().parallel().forEach((s) -> {
            if (s.isOpen()) {
                if (group.equalsIgnoreCase((String) s.getUserProperties().get("grupo"))) {
                    try {
                        s.getBasicRemote().sendText(texto);
                    } catch (IOException e) {
                        logger.log(Level.SEVERE, "sendToGroup failed " + s.getId() + " " + group + " " + texto, e);
                    }
                }
            } else {
                peers.remove(s);
            }
        });
    }

    public void sendToUser(final String texto, final String usuario, final String channel) {
        peers.stream().parallel().forEach((s) -> {
            if (s.isOpen()) {
                if (usuario.equalsIgnoreCase((String) s.getUserProperties().get("usuario"))
                        && channel.equalsIgnoreCase((String) s.getUserProperties().get("channel"))) {
                    try {
                        s.getBasicRemote().sendText(texto);
                    } catch (IOException e) {
                        logger.log(Level.SEVERE, "sendToUser failed " + s.getId() + " " + usuario + " " + texto, e);
                    }
                }
            } else {
                peers.remove(s);
            }
        });
    }

    public void sendToSessions(final String texto, final String sessionID, final String channel) {
        peers.stream().parallel().forEach((s) -> {
            if (s.isOpen()) {
                if (!sessionID.equalsIgnoreCase(s.getId()) && channel.equalsIgnoreCase((String) s.getUserProperties().get("channel"))) {
                    try {
                        s.getBasicRemote().sendText(texto);
                    } catch (IOException e) {
                        logger.log(Level.SEVERE, "sendToSessions failed " + s.getId() + " " + sessionID + " " + texto, e);
                    }
                }
            } else {
                peers.remove(s);
            }
        });
    }

    public void sendToSessions(final String texto) {
        peers.stream().parallel().forEach((s) -> {
            if (s.isOpen()) {
                try {
                    s.getBasicRemote().sendText(texto);
                } catch (IOException e) {
                    logger.log(Level.SEVERE, "sendToSessions failed " + s.getId() + " " + texto, e);
                }
            } else {
                peers.remove(s);
            }
        });
    }

    public void sendToMySession(final String texto, final String sessionID) {
        peers.stream().parallel().forEach((s) -> {
            if (s.isOpen()) {
                if (sessionID.equalsIgnoreCase(s.getId())) {
                    try {
                        s.getBasicRemote().sendText(texto);
                    } catch (IOException e) {
                        logger.log(Level.SEVERE, "sendToMySession failed  " + s.getId() + " " + sessionID + " " + texto, e);
                    }
                }
            } else {
                peers.remove(s);
            }
        });
    }

}
