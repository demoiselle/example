package org.demoiselle.forum.service;

import io.swagger.jaxrs.config.BeanConfig;
import java.util.logging.Logger;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author PauloGladson
 */
@ApplicationPath("api")
public class ApplicationConfig extends Application {

    /**
     *
     */
    public ApplicationConfig() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setBasePath("/app/api");
        beanConfig.setResourcePackage("org.demoiselle.forum.service");
        beanConfig.setVersion("3.0.0");

        beanConfig.setContact("https://github.com/demoiselle");
        beanConfig.setDescription("Sistema base gerado pelo generetor-demoiselle https://github.com/demoiselle/generator-demoiselle ");
        beanConfig.setLicense("LGPL v2");
        beanConfig.setTermsOfServiceUrl("https://demoiselle.gitbooks.io/documentacao-jee/");
        beanConfig.setTitle("generetor-demoiselle");

        beanConfig.setScan(true);
    }
    private static final Logger LOG = Logger.getLogger(ApplicationConfig.class.getName());
}
