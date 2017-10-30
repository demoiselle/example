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
import org.demoiselle.forum.constants.Perfil;
import static org.demoiselle.forum.constants.Perfil.VISITANTE;

/**
 *
 * @author PauloGladson
 */
@Entity
@Table(name = "visitante")
@XmlRootElement
@DiscriminatorValue("GUEST")
public class Guest extends User {

    private String apelido;
    private Integer nota;

    /**
     *
     */
    public Guest() {
        this.setPerfil(VISITANTE);
    }

    /**
     *
     * @return
     */
    public String getApelido() {
        return apelido;
    }

    /**
     *
     * @param apelido
     */
    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    /**
     *
     * @return
     */
    public Integer getNota() {
        return nota;
    }

    /**
     *
     * @param nota
     */
    public void setNota(Integer nota) {
        this.nota = nota;
    }
    private static final Logger LOG = Logger.getLogger(Guest.class.getName());

}
