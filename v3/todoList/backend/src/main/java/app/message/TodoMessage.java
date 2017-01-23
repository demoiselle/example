package app.message;

import org.apache.deltaspike.core.api.message.MessageBundle;
import org.apache.deltaspike.core.api.message.MessageTemplate;


@MessageBundle
public interface TodoMessage {

    @MessageTemplate("{onlyOwner}")
    String onlyOwner();

}
