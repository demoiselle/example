package app.constants;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum Status {

    AGENDADO("Agendado"),
    INICIADO("Iniciado"),
    FINALIZADO("Finalizado");

    private final String value;

    private Status(String value) {
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
        Map<Status, String> map = new ConcurrentHashMap<>();
        for (Status userType : Status.values()) {
            map.put(userType, userType.value);
        }
        return map;
    }

}
