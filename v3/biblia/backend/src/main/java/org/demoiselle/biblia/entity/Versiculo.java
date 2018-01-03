package org.demoiselle.biblia.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

@Entity
@Table
@Indexed
@XmlRootElement
@Analyzer(impl = BrazilianAnalyzer.class)
public class Versiculo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    @Column(length = 128)
    private String liv_nome;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    @Column(length = 2048)
    private String ver_texto;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    @Column(length = 128)
    private String vrs_nome;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    @Column(length = 128)
    private String tes_nome;

    private Integer ver_versiculo;

    private Integer ver_capitulo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLiv_nome() {
        return liv_nome;
    }

    public void setLiv_nome(String liv_nome) {
        this.liv_nome = liv_nome;
    }

    public String getVer_texto() {
        return ver_texto;
    }

    public void setVer_texto(String ver_texto) {
        this.ver_texto = ver_texto;
    }

    public String getVrs_nome() {
        return vrs_nome;
    }

    public void setVrs_nome(String vrs_nome) {
        this.vrs_nome = vrs_nome;
    }

    public String getTes_nome() {
        return tes_nome;
    }

    public void setTes_nome(String tes_nome) {
        this.tes_nome = tes_nome;
    }

    public Integer getVer_versiculo() {
        return ver_versiculo;
    }

    public void setVer_versiculo(Integer ver_versiculo) {
        this.ver_versiculo = ver_versiculo;
    }

    public Integer getVer_capitulo() {
        return ver_capitulo;
    }

    public void setVer_capitulo(Integer ver_capitulo) {
        this.ver_capitulo = ver_capitulo;
    }

    @Override
    public String toString() {
        return "Versiculo{" + "id=" + id + ", liv_nome=" + liv_nome + ", ver_texto=" + ver_texto + ", vrs_nome=" + vrs_nome + ", tes_nome=" + tes_nome + ", ver_versiculo=" + ver_versiculo + ", ver_capitulo=" + ver_capitulo + '}';
    }

}
