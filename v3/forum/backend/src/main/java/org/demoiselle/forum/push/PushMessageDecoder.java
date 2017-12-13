package org.demoiselle.forum.push;

import com.google.gson.Gson;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author SERPRO
 */
public class PushMessageDecoder implements Decoder.Text<PushMessage> {

    private static final Logger LOG = getLogger(PushMessageDecoder.class.getName());

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
