package org.demoiselle.livraria.message;

public interface AppMessage {

    default String onlyOwner() {
        return "Você só pode alterar seu próprio dado";
    }

}
