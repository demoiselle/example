package app.socket.push;

import com.google.gson.Gson;
import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;
import jakarta.websocket.EndpointConfig;

/**
 *
 * @author gladson
 */
public class PushMessageEncoder implements Encoder.Text<PushMessage> {

    @Override
    public void init(final EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public String encode(final PushMessage chatMessage) throws EncodeException {
        return new Gson().toJson(chatMessage);
    }
}
