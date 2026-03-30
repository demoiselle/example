package app.socket.push;

import com.google.gson.Gson;
import java.util.logging.Logger;
import jakarta.websocket.DecodeException;
import jakarta.websocket.Decoder;
import jakarta.websocket.EndpointConfig;

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
