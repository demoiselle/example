package estacionamento.entity;

/**
 *
 * @author 70744416353
 */
public enum Perfil {

    /**
     *
     */
    ADMINISTRADOR(1),

    /**
     *
     */
    FUNCIONARIO(2),

    /**
     *
     */
    USUARIO(4);

    private final int id;

    Perfil(int valor) {
        id = valor;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     * @return
     */
    public static Perfil getPerfil(int id) {
        for (Perfil object : values()) {
            if (object.id == id) {
                return object;
            }
        }
        return null;
    }

}
