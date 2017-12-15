package org.demoiselle.forum.dao;

import java.io.IOException;
import org.demoiselle.forum.security.Credentials;
import org.demoiselle.forum.security.Social;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import static java.security.MessageDigest.getInstance;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import static javax.ws.rs.core.HttpHeaders.USER_AGENT;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import org.demoiselle.forum.constants.Perfil;
import java.util.List;
import java.util.UUID;
import org.demoiselle.forum.entity.User;
import org.demoiselle.jee.core.api.security.DemoiselleUser;
import org.demoiselle.jee.core.api.security.SecurityContext;
import org.demoiselle.jee.core.api.security.Token;
import org.demoiselle.jee.crud.AbstractDAO;
import org.demoiselle.jee.security.exception.DemoiselleSecurityException;
import org.demoiselle.jee.security.message.DemoiselleSecurityMessages;
import org.demoiselle.forum.entity.Fingerprint;
import org.demoiselle.forum.cloud.CloudSender;

/**
 *
 * @author 70744416353
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
    private DemoiselleSecurityMessages bundle;

    @Inject
    private FingerprintDAO fingerprintDAO;

    @Inject
    private CloudSender sender;

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

        User usu = verifyEmail(email);

        if (!usu.getPass().equalsIgnoreCase(md5(password))) {
            throw new DemoiselleSecurityException("Senha incorreta", UNAUTHORIZED.getStatusCode());
        }

        return usu;
    }

    /**
     *
     * @param email
     * @return
     */
    public User verifyEmail(String email) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> from = query.from(User.class);
        TypedQuery<User> typedQuery = getEntityManager().createQuery(
                query.select(from)
                        .where(builder.equal(from.get("email"), email))
        );

        return typedQuery.getResultList().isEmpty() ? null : typedQuery.getResultList().get(0);
    }

    /**
     *
     * @param entity
     * @return
     */
    @Override
    public User persist(User entity) {
        entity.setPass(md5(entity.getPass()));
        entity.setPerfil(Perfil.USUARIO);
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

        loggedUser.setName(usu.getDescription());
        loggedUser.setIdentity(usu.getId().toString());
        loggedUser.addRole(usu.getPerfil().getValue());

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
     */
    public void register(Credentials credentials) {
        // envia email
        LOG.log(Level.INFO, "Enviando lembran\u00e7a para : {0}", credentials.getUsername());
        //return login(credentials);
    }

    /**
     *
     * @param credentials
     */
    public void amnesia(Credentials credentials) {
        // envia email
        LOG.log(Level.INFO, "Enviando lembran\u00e7a para : {0}", credentials.getUsername());
        //return login(credentials);
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

    /**
     *
     * @param social
     * @return
     */
    public Token social(Social social) {

        if (social.getProvider().equalsIgnoreCase("google") && !validateGoogle(social.getIdToken())) {
            throw new DemoiselleSecurityException("N�o validado pelo Google", Response.Status.PRECONDITION_FAILED.getStatusCode());
        }

        if (social.getProvider().equalsIgnoreCase("facebook") && !validateFacebook(social.getToken())) {
            throw new DemoiselleSecurityException("N�o validado pelo Facebook", Response.Status.PRECONDITION_FAILED.getStatusCode());
        }

        User usu = verifyEmail(social.getEmail());

        if (usu == null) {
            usu = new User();
            usu.setEmail(social.getEmail());
            usu.setDescription(social.getName());
            usu.setFoto(social.getImage());
            usu.setPerfil(Perfil.USUARIO);
            usu.setPass("132456");
            persist(usu);
        } else if (!social.getImage().equalsIgnoreCase(usu.getFoto())) {
            usu.setFoto(social.getImage());
            mergeFull(usu);
        }

        loggedUser.setName(usu.getDescription());
        loggedUser.setIdentity(usu.getId().toString());
        loggedUser.addRole(usu.getPerfil().toString());

        loggedUser.addParam("Email", usu.getEmail());
        loggedUser.addParam("Foto", usu.getFoto());
        securityContext.setUser(loggedUser);

        return token;
    }

    private boolean validateGoogle(String token) {

        try {
            String url = "https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=" + token;

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);

            return con.getResponseCode() == 200;
        } catch (MalformedURLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private boolean validateFacebook(String token) {

        try {
            String url = "https://graph.facebook.com/app?access_token=" + token;

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);

            return con.getResponseCode() == 200;
        } catch (MalformedURLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     *
     * @param fingerprint
     */
    public void setFingerprint(String fingerprint) {
        if (fingerprint != null && !fingerprint.isEmpty()) {
            List<Fingerprint> fps = fingerprintDAO.findByCodigo(fingerprint);

            if (fps == null || fps.isEmpty()) {
                Fingerprint fp = new Fingerprint();
                fp.setCodigo(securityContext.getUser().getParams("Email"));
                fp.setUsuario(fingerprint);
                fingerprintDAO.persist(fp);
            }

            if (fingerprint.contains("send")) {
                sender.send(fingerprint.split("send/")[1], "Login realizado!");
            }

            LOG.info(fingerprint.split("send/")[1] + " Fingerprint");

        }
    }
}
