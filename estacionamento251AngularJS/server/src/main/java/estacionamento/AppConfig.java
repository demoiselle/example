package estacionamento;

import br.gov.frameworkdemoiselle.annotation.Name;
import br.gov.frameworkdemoiselle.configuration.Configuration;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author 70744416353
 */
@Configuration(resource = "estacionamento")
public class AppConfig {

    private String url;

    private Long sessionTimeout = new Long(90 * 60 * 1000);

    @Name("email.email")
    private String email;

    @Name("email.username")
    private String usernameEmail;

    @Name("email.password")
    private String passwordEmail;

    @Name("email.url")
    private String urlEmail;

    @Name("email.accountId")
    private String accountId;

    @Name("email.folder.fail")
    private String folderEmailObjects;

    @Name("jwt.key")
    private String chave;

    @Name("jwt.minutes")
    private Float tempo;

    @Name("jwt.issuer")
    private String remetente;

    @Name("jwt.audience")
    private String destinatario;

    /**
     *
     */
    public AppConfig() {
        super();
    }

    /**
     *
     * @param url
     * @param email
     * @param usernameEmail
     * @param passwordEmail
     * @param urlEmail
     * @param accountId
     */
    public AppConfig(String url, String email, String usernameEmail,
                     String passwordEmail, String urlEmail, String accountId) {
        super();
        this.url = url;
        this.email = email;
        this.usernameEmail = usernameEmail;
        this.passwordEmail = passwordEmail;
        this.urlEmail = urlEmail;
        this.accountId = accountId;
    }

    /**
     *
     * @return
     */
    public String getFolderEmailObjects() {
        return folderEmailObjects;
    }

    /**
     *
     * @return
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @return
     */
    public Long getSessionTimeout() {
        return sessionTimeout;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @return
     */
    public String getUsernameEmail() {
        return usernameEmail;
    }

    /**
     *
     * @return
     */
    public String getPasswordEmail() {
        return passwordEmail;
    }

    /**
     *
     * @return
     */
    public String getUrlEmail() {
        return urlEmail;
    }

    /**
     *
     * @return
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     *
     * @return
     */
    public String getChave() {
        return chave;
    }

    /**
     *
     * @param chave
     */
    public void setChave(String chave) {
        this.chave = chave;
    }

    /**
     *
     * @return
     */
    public Float getTempo() {
        return tempo;
    }

    /**
     *
     * @param tempo
     */
    public void setTempo(Float tempo) {
        this.tempo = tempo;
    }

    /**
     *
     * @return
     */
    public String getRemetente() {
        return remetente;
    }

    /**
     *
     * @param remetente
     */
    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    /**
     *
     * @return
     */
    public String getDestinatario() {
        return destinatario;
    }

    /**
     *
     * @param destinatario
     */
    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

}
