/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estacionamento.cover;

import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author 70744416353
 */
/**
 *
 */
@RequestScoped
public class CredentialsData {

    private static final Logger LOG = Logger.getLogger(CredentialsData.class.getName());

    /**
     *
     */
    @NotNull(message = "{required.field}")
    @Size(min = 1, message = "{required.field}")
    public static String username;

    /**
     *
     */
    @NotNull(message = "{required.field}")
    @Size(min = 1, message = "{required.field}")
    public static String password;

}
