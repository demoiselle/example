/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

import io.swagger.jaxrs.config.BeanConfig;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author gladson
 */
@ApplicationPath("api")
public class ApplicationConfig extends Application {

    /**
     *
     */
    public ApplicationConfig() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setBasePath("/api");
        beanConfig.setContact("http://demoiselle.io");
        beanConfig.setDescription("Sistema Web Push - Envie mensagens/commandos push para os clients da sua app");
        beanConfig.setLicense("LGPL v2");
        beanConfig.setVersion("3.0.0");
        beanConfig.setTermsOfServiceUrl("https://github.com/demoiselle");
        beanConfig.setTitle("Demoiselle Web Push - API");
        beanConfig.setResourcePackage("app.service");
        beanConfig.setScan(true);
    }
}
