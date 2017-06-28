package org.demoiselle.livraria.dao;

import org.demoiselle.livraria.constants.Perfil;
import org.demoiselle.livraria.tenant.User;
import org.demoiselle.livraria.security.Credentials;
import java.math.BigInteger;
import java.security.MessageDigest;
import static java.security.MessageDigest.getInstance;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import org.demoiselle.jee.core.api.security.DemoiselleUser;
import org.demoiselle.jee.core.api.security.SecurityContext;
import org.demoiselle.jee.core.api.security.Token;
import org.demoiselle.jee.crud.AbstractDAO;
import org.demoiselle.jee.security.exception.DemoiselleSecurityException;
import org.demoiselle.jee.security.message.DemoiselleSecurityMessages;
import org.demoiselle.livraria.security.UserRegister;
import org.demoiselle.livraria.tenant.Livraria;
import org.demoiselle.livraria.tenant.Mensagem;

public class UserDAO extends AbstractDAO<User, String> {

    private static final Logger LOG = getLogger(UserDAO.class.getName());

    @Inject
    private SecurityContext securityContext;

    @Inject
    private DemoiselleUser loggedUser;

    @Inject
    private Token token;

    @Inject
    private LivrariaDAO livrariadao;

    @Inject
    private MensagemDAO mdao;

    @Inject
    private DemoiselleSecurityMessages bundle;

    @PersistenceContext(unitName = "pu")
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

    public String valida(String id) {
        return "Email Validado";
    }

    public Token login(Credentials credentials) {

        User usu = verifyEmail(credentials.getUsername(), credentials.getPassword());
        if (usu == null) {
            throw new DemoiselleSecurityException(bundle.invalidCredentials(), UNAUTHORIZED.getStatusCode());
        }

        loggedUser.setName(usu.getFirstName());
        loggedUser.setIdentity(usu.getId().toString());
        loggedUser.addRole(usu.getPerfil().getValue());

        loggedUser.addParam("email", usu.getEmail());
        loggedUser.addParam("tenant", "tenant" + usu.getLivraria().getId().toString().toLowerCase().replace("-", ""));
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

    public Token register(UserRegister entity) {

        try {

            CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<User> query = builder.createQuery(User.class);
            Root<User> from = query.from(User.class);
            TypedQuery<User> typedQuery = getEntityManager().createQuery(
                    query.select(from)
                            .where(builder.equal(from.get("email"), entity.getUsername()))
            );

            if (typedQuery.getResultList() != null && !typedQuery.getResultList().isEmpty()) {
                throw new DemoiselleSecurityException("Usuário existe, utilize outro email", Status.PRECONDITION_FAILED.getStatusCode());
            }

            if (livrariadao.nomeExists(entity.getLivraria())) {
                throw new DemoiselleSecurityException("Livraria ja existe, escolha outro nome", Status.PRECONDITION_FAILED.getStatusCode());
            }

            Livraria livraria = new Livraria();
            livraria.setDescription(entity.getLivraria());
            livraria = livrariadao.persist(livraria);

            User user = new User();
            user.setLivraria(livraria);
            user.setFirstName(entity.getNome());
            user.setEmail(entity.getUsername());
            user.setPerfil(Perfil.ADMINISTRADOR);
            user.setPass(entity.getPassword());
            user = persist(user);

            loggedUser.setName(user.getFirstName());
            loggedUser.setIdentity(user.getId().toString());
            loggedUser.addRole(user.getPerfil().getValue());

            loggedUser.addParam("email", user.getEmail());
            loggedUser.addParam("tenant", "tenant" + livraria.getId().toString().toLowerCase().replace("-", ""));

            Mensagem mem = new Mensagem();
            mem.setAtivo(true);
            mem.setComando("tenant" + livraria.getId().toString().toLowerCase().replace("-", ""));
            mem.setTipo("DBADD");

            mdao.persist(mem);

            securityContext.setUser(loggedUser);
            return token;

        } catch (DemoiselleSecurityException e) {
            throw new DemoiselleSecurityException(e.getLocalizedMessage(), Status.PRECONDITION_FAILED.getStatusCode());
        }

    }

}
