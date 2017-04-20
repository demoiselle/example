/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demoiselle.forum.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.demoiselle.forum.constants.Perfil;

@Entity
@Table(name = "visitante")
@XmlRootElement
@DiscriminatorValue("GUEST")
public class Guest extends User {

    private String apelido;
    private Integer nota;

    public Guest() {
        this.setPerfil(Perfil.VISITANTE);
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

}
