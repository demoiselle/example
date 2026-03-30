package app.dao;

import app.entity.User;
import app.security.Credentials;
import java.math.BigInteger;
import java.security.MessageDigest;
import static java.security.MessageDigest.getInstance;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.ws.rs.core.MultivaluedHashMap;
import jakarta.ws.rs.core.MultivaluedMap;
import static jakarta.ws.rs.core.Response.Status.UNAUTHORIZED;
import org.demoiselle.jee.core.api.security.DemoiselleUser;
import org.demoiselle.jee.core.api.security.SecurityContext;
import org.demoiselle.jee.core.api.security.Token;
import org.demoiselle.jee.crud.AbstractDAO;
import org.demoiselle.jee.security.exception.DemoiselleSecurityException;
import org.demoiselle.jee.security.message.DemoiselleSecurityMessages;

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

        if (!usu.getPass().equalsIgnoreCase(md5(password))) {
            throw new DemoiselleSecurityException("Senha incorreta", UNAUTHORIZED.getStatusCode());
        }

        return usu;
    }

    @Override
    public User persist(User entity) {
        entity.setPass(md5(entity.getPass()));
        return super.persist(entity);
    }

    public Token login(Credentials credentials) {
        User usu = verifyEmail(credentials.getUsername(), credentials.getPassword());
        if (usu == null) {
            throw new DemoiselleSecurityException(bundle.invalidCredentials(), UNAUTHORIZED.getStatusCode());
        }

        loggedUser.setName(usu.getFirstName());
        loggedUser.setIdentity("" + usu.getId());
        loggedUser.addRole(usu.getPerfil().getValue());
        loggedUser.addParam("Email", usu.getEmail());
        securityContext.setUser(loggedUser);

        return token;
    }

    public Token retoken() {
        loggedUser = securityContext.getUser();
        securityContext.setUser(loggedUser);
        return token;
    }

    private String md5(String senha) {
        String sen = "";
        MessageDigest md = null;
        try {
            md = getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            LOG.severe(e.getMessage());
        }
        BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
        sen = hash.toString(16);
        return sen;
    }
}
