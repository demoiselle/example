package org.demoiselle.biblia.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import org.demoiselle.biblia.entity.Versiculo;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.util.Span;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.apache.lucene.search.Sort;
import org.demoiselle.biblia.constants.ResponseFTS;
import org.demoiselle.jee.crud.AbstractDAO;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

public class VersiculoDAO extends AbstractDAO< Versiculo, Integer> {

    private static final Logger LOG = getLogger(VersiculoDAO.class.getName());

    @PersistenceContext(unitName = "bibliaPU")
    protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     *
     * @param nome
     * @return
     */
    public List<ResponseFTS> listarVersiculosFTS(String nome) {

        List<ResponseFTS> lista = new ArrayList<>();
        FullTextEntityManager fullTextEm = Search.getFullTextEntityManager(getEntityManager());
        QueryBuilder qb = fullTextEm.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Versiculo.class)
                .get();

        org.apache.lucene.search.Query luceneQuery;

        if (nome.split(" ").length > 1) {
            luceneQuery = qb.phrase()
                    .onField("liv_nome")
                    .andField("ver_texto").boostedTo(2)
                    .andField("vrs_nome")
                    .andField("tes_nome")
                    .sentence(nome).createQuery();
        } else {
            luceneQuery = qb.keyword()
                    .onField("liv_nome")
                    .andField("ver_texto").boostedTo(2)
                    .andField("vrs_nome")
                    .andField("tes_nome")
                    .matching(nome).createQuery();
        }

        FullTextQuery fullTextQuery = fullTextEm.createFullTextQuery(luceneQuery, Versiculo.class);
        fullTextQuery.setProjection(FullTextQuery.SCORE, FullTextQuery.THIS);

        fullTextQuery.setSort(Sort.RELEVANCE).getResultList().forEach((object) -> {
            try {
                StringBuilder sb = new StringBuilder();
                Float score = (Float) ((Object[]) object)[0];
                Versiculo versiculo = (Versiculo) ((Object[]) object)[1];
                ResponseFTS fts = new ResponseFTS();
                fts.setIdOrigem(versiculo.getId());
                fts.setOrigem(versiculo.getTes_nome());
                fts.setNome(versiculo.getLiv_nome().trim() + " cap. " + versiculo.getVer_capitulo() + " ver. " + versiculo.getVer_versiculo() + " tradução " + versiculo.getVrs_nome());
                fts.setOcorrencias(score);

                fts.setTexto(versiculo.getVer_texto());

                lista.add(fts);
            } catch (Exception ex) {
                Logger.getLogger(VersiculoDAO.class.getName()).log(Level.SEVERE, null, ex);

            }
        });

        return lista;

    }

    public List<String> nomes() {
        List<String> resultado = new ArrayList<>();

        try {
            List<Versiculo> lista = listAll();
            // http://opennlp.sourceforge.net/models-1.5/en-ner-person.bin
            // Aguardando a versão em pt-br
            TokenNameFinderModel model = new TokenNameFinderModel(new File("/opt/es-ner-person.bin"));
            NameFinderME finder = new NameFinderME(model);
            Tokenizer tokenizer = SimpleTokenizer.INSTANCE;

            for (Versiculo versiculo : lista) {
                String[] tokens = tokenizer.tokenize(versiculo.getVer_texto());
                Span[] nameSpans = finder.find(tokens);
                if (nameSpans.length > 0) {
                    resultado.add(Arrays.toString(Span.spansToStrings(nameSpans, tokens)) + " - " + versiculo.toString());
                }

            }

        } catch (IOException ex) {
            Logger.getLogger(VersiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    public void reindex() {
        FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search
                .getFullTextEntityManager(getEntityManager());
        try {
            fullTextEntityManager.createIndexer().optimizeOnFinish(Boolean.TRUE).startAndWait();
        } catch (InterruptedException e) {
            LOG.severe(e.getMessage());
        }
    }

    private List<Versiculo> listAll() {
        return getEntityManager().createQuery("From Versiculo", Versiculo.class).getResultList();
    }
}
