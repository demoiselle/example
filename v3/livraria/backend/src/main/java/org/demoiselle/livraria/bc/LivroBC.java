package org.demoiselle.livraria.bc;

import java.util.List;
import javax.inject.Inject;
import org.demoiselle.livraria.entity.Livro;
import org.demoiselle.jee.crud.AbstractBusiness;
import org.demoiselle.livraria.dao.MensagemDAO;
import org.demoiselle.livraria.tenant.Mensagem;
import org.demoiselle.livraria.tenant.User;

public class LivroBC extends AbstractBusiness< Livro, String> {

    @Inject
    private MensagemDAO mdao;

    @Inject
    private UserBC ubc;

    @Override
    public Livro persist(Livro entity) {

        List<User> luser = (List<User>) ubc.find().getContent();

        Livro livro = super.persist(entity);

        for (User user : luser) {
            Mensagem mem = new Mensagem();
            mem.setAtivo(true);
            mem.setComando(user.getFirstName() + " temos um Livro novo, o " + livro.getDescricao());
            mem.setTipo("EMAIL");
            mdao.persist(mem);
        }

        return livro;
    }

}
