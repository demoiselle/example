package estacionamento.service;

import io.swagger.jaxrs.config.BeanConfig;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author 70744416353
 */
@ApplicationPath("api")
public class RESTApp extends Application {

    /**
     *
     */
    public RESTApp() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setBasePath("/app/api");
        beanConfig.setResourcePackage("estacionamento.service");
        beanConfig.setScan(true);
    }
}
