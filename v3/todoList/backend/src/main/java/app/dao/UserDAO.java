
package app.dao;

import app.entity.User;
import app.security.Credentials;
import static app.util.MD5.parser;
import static java.lang.Long.parseLong;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import org.demoiselle.jee.core.api.security.DemoiselleUser;
import org.demoiselle.jee.core.api.security.SecurityContext;
import org.demoiselle.jee.core.api.security.Token;
import org.demoiselle.jee.persistence.crud.AbstractDAO;
import org.demoiselle.jee.security.exception.DemoiselleSecurityException;
import org.demoiselle.jee.security.message.DemoiselleSecurityMessages;

/**
 *
 * @author gladson
 */
public class UserDAO extends AbstractDAO<User, String> {

    private static final Logger LOG = getLogger(UserDAO.class.getName());

    @Inject
    private SecurityContext securityContext;

    @Inject
    private DemoiselleUser loggedUser;

    @Inject
    private Token token;

    @Inject
    private DemoiselleSecurityMessages bundle;

    @PersistenceContext(unitName = "TodoPU")
    protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected Predicate[] extractPredicates(MultivaluedMap<String, String> queryParameters, CriteriaBuilder criteriaBuilder, Root<User> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (queryParameters.containsKey("id")) {
            Long id = parseLong(queryParameters.getFirst("id"));
            predicates.add(criteriaBuilder.equal(root.get("id"), id));
        }

        if (queryParameters.containsKey("nome")) {
            String nome = queryParameters.getFirst("nome");
            predicates.add(criteriaBuilder.like(root.get("nome"), "%" + nome + "%"));
        }

        return predicates.toArray(new Predicate[]{});
    }

    public User verifyEmail(String email, String password) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> from = query.from(User.class);
        TypedQuery<User> typedQuery = getEntityManager().createQuery(
                query.select(from)
                        .where(builder.equal(from.get("email"), email))
        );

        if (typedQuery.getResultList().isEmpty()) {
            throw new DemoiselleSecurityException("Usuário não existe", UNAUTHORIZED.getStatusCode());
        }

        User usu = typedQuery.getResultList().get(0);

        if (usu == null) {
            throw new DemoiselleSecurityException("Usuário não existe", UNAUTHORIZED.getStatusCode());
        }

        if (!usu.getPass().equalsIgnoreCase(parser(password))) {
            throw new DemoiselleSecurityException("Senha incorreta", UNAUTHORIZED.getStatusCode());
        }

        return usu;
    }

    @Override
    public User persist(User entity) {
        entity.setPass(parser(entity.getPass()));
        entity.setRole("USER");
        return super.persist(entity);
    }

    public String valida(String id) {
        return "Email Validado";
    }

    public String login(Credentials credentials) {

        User usu = verifyEmail(credentials.getUsername(), credentials.getPassword());
        if (usu == null) {
            throw new DemoiselleSecurityException(bundle.invalidCredentials(), UNAUTHORIZED.getStatusCode());
        }

        MultivaluedMap<String, String> values = new MultivaluedHashMap<>();
        values.putSingle("usuario", usu.getId());

        loggedUser.setName(usu.getFirstName());
        loggedUser.setIdentity("" + usu.getId());
        loggedUser.addRole(usu.getRole());

        loggedUser.addParam("Email", usu.getEmail());
        securityContext.setUser(loggedUser);

        return token.getKey();
    }

    public String retoken() {
        loggedUser = securityContext.getUser();
        securityContext.setUser(loggedUser);
        return token.getKey();
    }

}
