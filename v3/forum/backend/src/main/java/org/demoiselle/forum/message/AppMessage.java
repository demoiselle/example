package org.demoiselle.forum.message;

import org.apache.deltaspike.core.api.message.MessageBundle;
import org.apache.deltaspike.core.api.message.MessageTemplate;

@MessageBundle
public interface AppMessage {

    @MessageTemplate("{onlyOwner}")
    String onlyOwner();

}
