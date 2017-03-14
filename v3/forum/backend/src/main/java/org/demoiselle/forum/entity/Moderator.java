/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demoiselle.forum.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.demoiselle.forum.constants.Perfil;

@Entity
@Table(name = "moderador")
@XmlRootElement
public class Moderator extends User {

    private String cor;
    private String ordem;

    public Moderator() {
        this.setPerfil(Perfil.MODERADOR);
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getOrdem() {
        return ordem;
    }

    public void setOrdem(String ordem) {
        this.ordem = ordem;
    }

}
