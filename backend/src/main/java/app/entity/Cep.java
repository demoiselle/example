package app.entity;

import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 *
 * @author gladson
 */
@Entity
@Cacheable
@XmlRootElement
@Table(name = "cep")
public class Cep implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Type(type = "objectid")
    private String id;

    private String cep;
    private String uf;
    private String cidade;
    private String logradouro;
    private String bairro_ini;
    private String bairro_fim;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro_ini() {
        return bairro_ini;
    }

    public void setBairro_ini(String bairro_ini) {
        this.bairro_ini = bairro_ini;
    }

    public String getBairro_fim() {
        return bairro_fim;
    }

    public void setBairro_fim(String bairro_fim) {
        this.bairro_fim = bairro_fim;
    }

}
