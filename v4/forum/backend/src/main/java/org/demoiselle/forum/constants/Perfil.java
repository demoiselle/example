package org.demoiselle.forum.constants;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author 70744416353
 */
public enum Perfil {

    ADMINISTRADOR("Administrador"),
    GERENTE("Gerente"),
    USUARIO("Usuário");

    private final String value;

    private Perfil(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public static Map getMap() {
        Map<Perfil, String> map = new ConcurrentHashMap<>();
        for (Perfil userType : Perfil.values()) {
            map.put(userType, userType.toString());
        }
        return map;
    }
}
