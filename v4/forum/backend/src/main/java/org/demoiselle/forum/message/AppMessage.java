package org.demoiselle.forum.message;

/**
 *
 * @author 70744416353
 */
public interface AppMessage {

    default String onlyOwner() {
        return "Você só pode alterar seu próprio dado";
    }
}
