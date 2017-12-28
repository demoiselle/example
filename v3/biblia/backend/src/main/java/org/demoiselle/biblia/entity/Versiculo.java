package org.demoiselle.biblia.entity;

import java.io.Serializable;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String livNome;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String verTexto;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String vrsNome;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String tesNome;

    private Integer verVersiculo;

    private Integer verCapitulo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLivNome() {
        return livNome;
    }

    public void setLivNome(String livNome) {
        this.livNome = livNome;
    }

    public String getVerTexto() {
        return verTexto;
    }

    public void setVerTexto(String verTexto) {
        this.verTexto = verTexto;
    }

    public String getVrsNome() {
        return vrsNome;
    }

    public void setVrsNome(String vrsNome) {
        this.vrsNome = vrsNome;
    }

    public String getTesNome() {
        return tesNome;
    }

    public void setTesNome(String tesNome) {
        this.tesNome = tesNome;
    }

    public Integer getVerVersiculo() {
        return verVersiculo;
    }

    public void setVerVersiculo(Integer verVersiculo) {
        this.verVersiculo = verVersiculo;
    }

    public Integer getVerCapitulo() {
        return verCapitulo;
    }

    public void setVerCapitulo(Integer verCapitulo) {
        this.verCapitulo = verCapitulo;
    }

}
