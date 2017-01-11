/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.bc;

import app.entity.User;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import org.demoiselle.jee.crud.AbstractBusiness;

/**
 *
 * @author gladson
 */
public class UserBC extends AbstractBusiness<User, String> {

    private static final Logger LOG = getLogger(UserBC.class.getName());

}
