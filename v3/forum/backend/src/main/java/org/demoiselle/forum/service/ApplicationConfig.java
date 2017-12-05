package org.demoiselle.forum.service;

import io.swagger.jaxrs.config.BeanConfig;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("api")
public class ApplicationConfig extends Application {

    public ApplicationConfig() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setBasePath("/forum/api");
        beanConfig.setResourcePackage("org.demoiselle.forum.service");
        beanConfig.setVersion("3.0.0");

        beanConfig.setContact("https://github.com/demoiselle");
        beanConfig.setDescription("Sistema base gerado pelo generetor-demoiselle https://github.com/demoiselle/generator-demoiselle ");
        beanConfig.setLicense("LGPL v2");
        beanConfig.setTermsOfServiceUrl("https://demoiselle.gitbooks.io/documentacao-jee/");
        beanConfig.setTitle("generetor-demoiselle");

        beanConfig.setScan(true);
    }
}
