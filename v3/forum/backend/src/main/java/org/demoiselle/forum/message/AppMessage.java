package org.demoiselle.forum.message;

import org.apache.deltaspike.core.api.message.MessageBundle;
import org.apache.deltaspike.core.api.message.MessageTemplate;

/**
 *
 * @author PauloGladson
 */
@MessageBundle
public interface AppMessage {

    /**
     *
     * @return
     */
    @MessageTemplate("{onlyOwner}")
    String onlyOwner();

}
