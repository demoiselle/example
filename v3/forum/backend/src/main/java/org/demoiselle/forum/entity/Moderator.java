/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demoiselle.forum.entity;

import java.util.logging.Logger;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PauloGladson
 */
@Entity
@Table(name = "moderador")
@XmlRootElement
@DiscriminatorValue("MODERADOR")
public class Moderator extends User {

    private String cor;
    private String ordem;

    /**
     *
     * @return
     */
    public String getCor() {
        return cor;
    }

    /**
     *
     * @param cor
     */
    public void setCor(String cor) {
        this.cor = cor;
    }

    /**
     *
     * @return
     */
    public String getOrdem() {
        return ordem;
    }

    /**
     *
     * @param ordem
     */
    public void setOrdem(String ordem) {
        this.ordem = ordem;
    }
    private static final Logger LOG = Logger.getLogger(Moderator.class.getName());

}
