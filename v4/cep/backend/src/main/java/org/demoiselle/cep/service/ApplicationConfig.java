package org.demoiselle.cep.service;

import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 *
 * @author PauloGladson
 */
@ApplicationPath("api")
public class ApplicationConfig extends Application {

    private static final Logger LOG = getLogger(ApplicationConfig.class.getName());

}
