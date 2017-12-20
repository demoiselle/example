package org.demoiselle.cep.dao;

import com.google.common.net.InetAddresses;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.demoiselle.cep.entity.Ip2locationDb11;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.demoiselle.jee.crud.AbstractDAO;

public class Ip2LocationDb11DAO extends AbstractDAO< Ip2locationDb11, Integer> {

    @PersistenceContext
    protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List getInfoV4(String ip) {
        try {
            if (ip != null) {
                InetAddress bar = InetAddress.getByName(ip);
                return getEntityManager().createNativeQuery("SELECT * FROM ip2location_db11 WHERE ? between ip_from and ip_to LIMIT 1", Ip2locationDb11.class).setParameter(1, new BigInteger(1, bar.getAddress())).getResultList();
            }
            return null;
        } catch (UnknownHostException ex) {
            Logger.getLogger(Ip2LocationDb11DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
