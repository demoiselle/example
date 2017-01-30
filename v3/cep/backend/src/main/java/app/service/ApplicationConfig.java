/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.models.Info;
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
        beanConfig.setResourcePackage("app.service");
        Info info = new Info();
        info.setDescription("Application.");
        info.setTitle("App");
        beanConfig.setInfo(info);
        beanConfig.setScan(true);
    }
}
