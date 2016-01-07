package estacionamento.business;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.Priority;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import estacionamento.entity.User;
import estacionamento.persistence.UserDAO;

/**
 *
 * @author 70744416353
 */
@BusinessController
public class UserBC extends DelegateCrud<User, Long, UserDAO> {

    private static final long serialVersionUID = -7801407214303725321L;

    @Inject
    private UserDAO userDAO;

    /**
     * Verifica se o usuario existe pelo CPF. Caso não exista persiste o
     * usuário.
     *
     * @param user
     * @return
     */
    public User carregarOuInserir(User user) {
        User usuarioSistema = userDAO.loadByCPF(user.getTelephoneNumber());
        if (usuarioSistema == null) {
            usuarioSistema = userDAO.insert(user);
        }
        return usuarioSistema;
    }

    //@Startup\
    /**
     *
     * @return
     */
    @Priority(0)

    public User iniciarUsuario() {
        User admin = new User();
        admin.setEmail("admin@catalogotecnologia.serpro");
        admin.setName("ADMIN");
        admin.setTelephoneNumber("");

        return this.carregarOuInserir(admin);
    }

    /**
     *
     * @return
     */
    public Long count() {
        return getDelegate().count();
    }

    /**
     *
     * @param field
     * @param order
     * @param init
     * @param qtde
     * @return
     */
    public List<User> list(String field, String order, int init, int qtde) {
        return getDelegate().list(field, order, init, qtde);
    }

    /**
     *
     * @param name
     * @return
     */
    public List<User> pesquisar(String name) {
        return getDelegate().pesquisar(name);
    }

}
