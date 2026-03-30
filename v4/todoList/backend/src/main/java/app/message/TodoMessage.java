package app.message;

/**
 * Message interface for Todo messages.
 * Migrated from DeltaSpike @MessageBundle to simple interface.
 * Messages are resolved from app/message/TodoMessage.properties
 */
public interface TodoMessage {

    default String onlyOwner() {
        return "Você só pode alterar seu próprio dado";
    }
}
