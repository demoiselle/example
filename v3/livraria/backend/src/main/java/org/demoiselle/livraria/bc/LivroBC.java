package org.demoiselle.livraria.bc;

import java.util.List;
import javax.inject.Inject;
import org.demoiselle.livraria.entity.Livro;
import org.demoiselle.jee.crud.AbstractBusiness;
import org.demoiselle.livraria.entity.User;

public class LivroBC extends AbstractBusiness< Livro, String> {

    @Inject
    private UserBC ubc;

    @Override
    public Livro persist(Livro entity) {

        List<User> luser = (List<User>) ubc.find().getContent();

        Livro livro = super.persist(entity);

        return livro;
    }

}
