package org.demoiselle.biblia.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import org.demoiselle.biblia.entity.Versiculo;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
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
        BrazilianAnalyzer analyzer = new BrazilianAnalyzer();
        FullTextEntityManager fullTextEm = Search.getFullTextEntityManager(getEntityManager());
        QueryBuilder qb = fullTextEm.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Versiculo.class)
                .get();

        org.apache.lucene.search.Query luceneQuery;

        if (nome.split(" ").length > 1) {
            luceneQuery = qb.phrase()
                    .onField("livNome")
                    .andField("verTexto").boostedTo(2)
                    .andField("vrsNome")
                    .andField("tesNome")
                    .sentence(nome).createQuery();
        } else {
            luceneQuery = qb.keyword()
                    .onField("livNome")
                    .andField("verTexto").boostedTo(2)
                    .andField("vrsNome")
                    .andField("tesNome")
                    .matching(nome).createQuery();
        }

        QueryScorer queryScorer = new QueryScorer(luceneQuery);
        Highlighter highlighter = new Highlighter(queryScorer);
        SimpleFragmenter fragmenter = new SimpleFragmenter(60);
        highlighter.setTextFragmenter(fragmenter);

        FullTextQuery fullTextQuery = fullTextEm.createFullTextQuery(luceneQuery, Versiculo.class);
        fullTextQuery.setProjection(FullTextQuery.SCORE, FullTextQuery.THIS);

        fullTextQuery.setSort(Sort.RELEVANCE).getResultList().forEach((object) -> {
            try {
                StringBuilder sb = new StringBuilder();
                Float score = (Float) ((Object[]) object)[0];
                Versiculo versiculo = (Versiculo) ((Object[]) object)[1];
                ResponseFTS fts = new ResponseFTS();
                fts.setIdOrigem(versiculo.getId());
                fts.setOrigem(versiculo.getTesNome());
                fts.setNome(versiculo.getLivNome() + " cap. " + versiculo.getVerCapitulo() + " ver. " + versiculo.getVerVersiculo());
                fts.setOcorrencias(score);

                sb.append(versiculo.getVerTexto() == null ? " " : versiculo.getVerTexto() + " ");

                fts.setTexto("");

                String[] linhas = highlighter.getBestFragments(analyzer, "", sb.toString(), 10);

                for (String linha : linhas) {
                    fts.setTexto(" ..." + linha + " " + fts.getTexto());
                }

                lista.add(fts);
            } catch (IOException | InvalidTokenOffsetsException ex) {
                Logger.getLogger(VersiculoDAO.class.getName()).log(Level.SEVERE, null, ex);

            }
        });

        return lista;

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

}
