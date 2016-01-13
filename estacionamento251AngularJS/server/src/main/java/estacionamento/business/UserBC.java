package estacionamento.business;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import estacionamento.entity.User;
import estacionamento.persistence.UserDAO;
import java.util.List;

/**
 *
 * @author 70744416353
 */
@BusinessController
public class UserBC extends DelegateCrud<User, Long, UserDAO> {

    private static final long serialVersionUID = -7801407214303725321L;

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
