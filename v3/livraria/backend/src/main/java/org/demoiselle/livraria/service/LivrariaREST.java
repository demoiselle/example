package org.demoiselle.livraria.service;

import org.demoiselle.livraria.tenant.Livraria;
import io.swagger.annotations.Api;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.demoiselle.jee.core.api.crud.Result;
import org.demoiselle.jee.crud.AbstractREST;
import org.demoiselle.jee.crud.Search;

@Api("v1/Livrarias")
@Path("v1/livrarias")
//@Authenticated
public class LivrariaREST extends AbstractREST< Livraria, String> {

    @GET
    @Override
    @Transactional
    @Search(fields = {"id", "description"}) // Escolha quais campos serão passados para o frontend
    public Result find() {
        return bc.find();
    }

}
