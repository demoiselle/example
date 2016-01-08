package estacionamento.business;

import br.gov.frameworkdemoiselle.annotation.Priority;
import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import estacionamento.entity.Perfil;
import estacionamento.entity.User;
import estacionamento.persistence.UserDAO;
import estacionamento.util.Util;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author 70744416353
 */
@BusinessController
public class UserBC extends DelegateCrud<User, Long, UserDAO> {

    private static final long serialVersionUID = -7801407214303725321L;

    /**
     * Verifica se o usuario existe pelo CPF. Caso não exista persiste o
     * usuário.
     *
     * @param user
     * @return
     */
    public User carregarOuInserir(User user) {
        User usuarioSistema = getDelegate().loadByFone(user.getTelephoneNumber());
        if (usuarioSistema == null) {
            usuarioSistema = getDelegate().insert(user);
        }
        return usuarioSistema;
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

    /**
     *
     * @param email
     * @param senha
     * @return
     */
    public User loadEmailPass(String email, String senha) {
        return getDelegate().loadEmailPass(email, senha);
    }

}
