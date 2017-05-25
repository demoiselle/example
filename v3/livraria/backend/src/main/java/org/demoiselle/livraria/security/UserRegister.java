package org.demoiselle.livraria.security;

import java.io.Serializable;
import java.util.Objects;

public class UserRegister implements Serializable {

    private String livraria;
    private String nome;
    private String username;
    private String password;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLivraria() {
        return livraria;
    }

    public void setLivraria(String livraria) {
        this.livraria = livraria;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.username);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserRegister other = (UserRegister) obj;
        return Objects.equals(this.username, other.username);
    }

    @Override
    public String toString() {
        return "Credentials{" + "username=" + username + ", password=" + password + '}';
    }

}
