package app.service;

import io.swagger.jaxrs.config.BeanConfig;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author 70744416353
 */
@ApplicationPath("api")
public class ApplicationConfig extends Application {

    private static final Logger LOG = getLogger(ApplicationConfig.class.getName());

    public ApplicationConfig() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setBasePath("/api");
        beanConfig.setContact("https://github.com/demoiselle");
        beanConfig.setDescription("Sistema de exemplo do Demoiselle v3 - ToDolist https://github.com/demoiselle/example ");
        beanConfig.setLicense("LGPL v2");
        beanConfig.setVersion("3.0.0");
        beanConfig.setTermsOfServiceUrl("https://demoiselle.gitbooks.io/documentacao-jee/");
        beanConfig.setTitle("Demoiselle ToDoList - API");
        beanConfig.setResourcePackage("app.service");
        beanConfig.setScan(true);
    }
}
