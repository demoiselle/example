package org.demoiselle.forum.push;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import static java.util.logging.Level.FINE;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import javax.inject.Inject;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import org.demoiselle.forum.dao.UserDAO;
import org.demoiselle.forum.entity.User;

/**
 *
 * @author SERPRO
 */
@ServerEndpoint(value = "/push/{channel}", encoders = PushMessageEncoder.class, decoders = PushMessageDecoder.class)
public class PushEndpoint {

    private static final ConcurrentLinkedQueue<Session> peers = new ConcurrentLinkedQueue<>();

    private static final Logger logger = getLogger(PushEndpoint.class.getName());

    @Inject
    private UserDAO dao;

    /**
     *
     * @param session
     * @param c
     * @param channel
     */
    @OnOpen
    public void open(final Session session, EndpointConfig c, @PathParam("channel") String channel) {
        peers.add(session);
        session.getUserProperties().putIfAbsent("channel", channel);
    }

    /**
     *
     * @param session
     * @param message
     * @param channel
     */
    @OnMessage
    public void onMessage(final Session session, final PushMessage message, @PathParam("channel") String channel) {

        if (message.getEvent().equalsIgnoreCase("login")) {
            if (message.getData() != null && !message.getData().isEmpty()) {
                User usu = dao.find(UUID.fromString(message.getData()));
                if (usu != null) {
                    session.getUserProperties().putIfAbsent("id", usu.getId().toString());
                    session.getUserProperties().putIfAbsent("name", usu.getDescription());
                    session.getUserProperties().putIfAbsent("role", usu.getPerfil().toString());
                    session.getUserProperties().putIfAbsent("email", usu.getEmail());
                    PushMessage mm = new PushMessage("list", new Gson().toJson(listUsers(channel)));
                    sendTo(new Gson().toJson(mm), channel);
                    mm = new PushMessage("count", count());
                    sendTo(new Gson().toJson(mm), channel);
                    logger.log(FINE, "Logged - ", message.getData());
                }

            }
        }

        if (message.getEvent().equalsIgnoreCase("geo")) {
            if (message.getData() != null && !message.getData().isEmpty()) {
                session.getUserProperties().putIfAbsent("geo", message.getData());
                logger.log(FINE, "Logged - ", message.getData());
            }
        }

        if (message.getEvent().equalsIgnoreCase("logout")) {
            session.getUserProperties().remove("user");
            PushMessage mm = new PushMessage("list", new Gson().toJson(listUsers(channel)));
            sendTo(new Gson().toJson(mm), channel);
            mm = new PushMessage("count", count());
            sendTo(new Gson().toJson(mm), channel);
            logger.log(FINE, "Log off - ", message.getData());
        }

    }

    /**
     *
     * @param session
     * @param t
     */
    @OnError
    public void onError(final Session session, Throwable t) {
        peers.remove(session);
        logger.log(FINE, "onError failed - Session: " + session.getId(), t);
    }

    /**
     *
     * @param session
     * @param channel
     */
    @OnClose
    public void closedConnection(final Session session, @PathParam("channel") String channel) {
        peers.remove(session);
        PushMessage mm = new PushMessage("list", new Gson().toJson(listUsers(channel)));
        sendTo(new Gson().toJson(mm), channel);
        mm = new PushMessage("count", count());
        sendTo(new Gson().toJson(mm), channel);
    }

    /**
     *
     * @return
     */
    public String count() {
        return "" + peers.size();
    }

    /**
     *
     * @param term
     * @return
     */
    public String count(String term) {
        return "" + peers.parallelStream().filter(s -> s.getUserProperties().containsValue(term)).count();
    }

    /**
     *
     * @param recipient
     * @return
     */
    public List<String> listUsers(String recipient) {
        List<String> list = new ArrayList<>();
        peers.parallelStream().forEach((s) -> {
            if (s.isOpen()) {
                if (recipient != null && s.getUserProperties().containsValue(recipient)) {
                    if (s.getUserProperties().get("name") != null) {
                        list.add((String) s.getUserProperties().get("name") + ":" + (String) s.getUserProperties().get("email") + ":" + (String) s.getUserProperties().get("role"));
                    }
                }
            } else {
                peers.remove(s);
            }

        });
        return list;
    }

    /**
     *
     * @return
     */
    public List<String> listUsers() {
        List<String> list = new ArrayList<>();
        peers.parallelStream().forEach((s) -> {
            if (s.isOpen()) {
                if (s.getUserProperties().containsKey("id") && s.getUserProperties().get("id") != null) {
                    list.add((String) s.getUserProperties().get("id"));
                }
            } else {
                peers.remove(s);
            }
        });
        return list;
    }

    /**
     *
     * @param texto
     * @param recipient
     */
    public void sendTo(final String texto, final String recipient) {
        peers.parallelStream().forEach((s) -> {
            if (s.isOpen()) {
                if (recipient != null && s.getUserProperties().containsValue(recipient)) {
                    try {
                        s.getBasicRemote().sendText(texto);
                    } catch (IOException e) {
                        logger.log(Level.SEVERE, "sendToUser failed " + s.getId() + " " + recipient + " " + texto, e);
                    }
                }
            } else {
                peers.remove(s);
            }
        });
    }

    /**
     *
     * @param texto
     * @param sessionID
     */
    public void sendToSession(final String texto, final String sessionID) {
        peers.parallelStream().forEach((s) -> {
            if (s.isOpen()) {
                if (sessionID != null && s.getId().equalsIgnoreCase(sessionID)) {
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

    /**
     *
     * @param texto
     */
    public void sendToSessions(final String texto) {
        peers.parallelStream().forEach((s) -> {
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

}
