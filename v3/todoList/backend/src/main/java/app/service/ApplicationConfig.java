/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        beanConfig.setBasePath("/todo/api");
        beanConfig.setResourcePackage("app.service");
        beanConfig.setScan(true);
    }
}
