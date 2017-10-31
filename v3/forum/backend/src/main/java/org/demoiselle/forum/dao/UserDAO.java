package org.demoiselle.forum.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import static java.security.MessageDigest.getInstance;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import org.demoiselle.forum.entity.User;
import org.demoiselle.forum.security.Credentials;
import org.demoiselle.jee.core.api.security.DemoiselleUser;
import org.demoiselle.jee.core.api.security.SecurityContext;
import org.demoiselle.jee.core.api.security.Token;
import org.demoiselle.jee.crud.AbstractDAO;
import org.demoiselle.jee.security.exception.DemoiselleSecurityException;
import org.demoiselle.jee.security.message.DemoiselleSecurityMessages;

/**
 *
 * @author PauloGladson
 */
public class UserDAO extends AbstractDAO<User, UUID> {

    private static final Logger LOG = getLogger(UserDAO.class.getName());

    @Inject
    private SecurityContext securityContext;

    @Inject
    private DemoiselleUser loggedUser;

    @Inject
    private Token token;

    @Inject
    private PerfilDAO perfilDAO;

    @Inject
    private DemoiselleSecurityMessages bundle;

    /**
     *
     */
    @PersistenceContext(unitName = "forumPU")
    protected EntityManager em;

    /**
     *
     * @return
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     *
     * @param email
     * @param password
     * @return
     */
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

    /**
     *
     * @param entity
     * @return
     */
    @Override
    public User persist(User entity) {
        entity.setPass(md5(entity.getPass()));
        return super.persist(entity);
    }

    /**
     *
     * @param id
     * @return
     */
    public String valida(String id) {
        return "Email Validado";
    }

    /**
     *
     * @param credentials
     * @return
     */
    public Token login(Credentials credentials) {

        User usu = verifyEmail(credentials.getUsername(), credentials.getPassword());
        if (usu == null) {
            throw new DemoiselleSecurityException(bundle.invalidCredentials(), UNAUTHORIZED.getStatusCode());
        }

        loggedUser.setName(usu.getFirstName());
        loggedUser.setIdentity(usu.getId());
        loggedUser.addRole(usu.getPerfil().getDescription());
        loggedUser.addPermission("usuario", "insert");
        loggedUser.addPermission("usuario", "update");
        loggedUser.addPermission("usuario", "delete");
        loggedUser.addPermission("usuario", "find");

        loggedUser.addParam("Email", usu.getEmail());
        securityContext.setUser(loggedUser);

        return token;
    }

    /**
     *
     * @return
     */
    public Token retoken() {
        loggedUser = securityContext.getUser();
        securityContext.setUser(loggedUser);
        return token;
    }

    /**
     *
     * @param credentials
     * @return
     */
    public Token register(Credentials credentials) {
        User user = new User();
        user.setEmail(credentials.getUsername());
        user.setFirstName(credentials.getFirstName());
        user.setPass(credentials.getPassword());
        user.setPerfil(perfilDAO.find("9"));
        persist(user);
        return login(credentials);
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
