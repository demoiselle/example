/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.socket.push;

import com.google.gson.Gson;
import java.util.logging.Logger;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author gladson
 */
public class PushMessageDecoder implements Decoder.Text<PushMessage> {

    private static final Logger LOG = Logger.getLogger(PushMessageDecoder.class.getName());

    @Override
    public void init(final EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public PushMessage decode(final String textMessage) throws DecodeException {
        PushMessage chatMessage = new Gson().fromJson(textMessage, PushMessage.class);
        return chatMessage;
    }

    @Override
    public boolean willDecode(final String s) {
        return true;
    }
}
