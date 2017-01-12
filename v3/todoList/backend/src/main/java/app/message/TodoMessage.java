/*
 * Demoiselle Framework
 *
 * License: GNU Lesser General Public License (LGPL), version 3 or later.
 * See the lgpl.txt file in the root directory or <https://www.gnu.org/licenses/lgpl.html>.
 */
package app.message;

import org.apache.deltaspike.core.api.message.MessageBundle;
import org.apache.deltaspike.core.api.message.MessageTemplate;

/**
 * Message class intended to be used by all framework.
 *
 * @author SERPRO
 */
@MessageBundle
public interface TodoMessage {

    /**
     *
     * @return start message
     */
    @MessageTemplate("{onlyOwner}")
    String onlyOwner();

}
