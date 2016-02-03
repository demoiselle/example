/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estacionamento.socket.echo;

import com.google.gson.Gson;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author gladson
 */
public class EchoMessageDecoder implements Decoder.Text<EchoMessage> {

    @Override
    public void init(final EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public EchoMessage decode(final String textMessage) throws DecodeException {
        EchoMessage chatMessage = new Gson().fromJson((String) textMessage, EchoMessage.class);
        return chatMessage;
    }

    @Override
    public boolean willDecode(final String s) {
        return true;
    }
}
