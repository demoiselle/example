package estacionamento.service;

import br.gov.frameworkdemoiselle.NotFoundException;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import estacionamento.business.FabricanteBC;
import estacionamento.entity.Fabricante;
import estacionamento.util.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.Serializable;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;

/**
 *
 * @author 70744416353
 */
@Api
@Path("fabricante")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class FabricanteREST implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private FabricanteBC bc;

    /**
     *
     * @return @throws Exception
     */
    @GET
    @Transactional
    public Response findAll() throws Exception {
        return Response.ok().entity(bc.findAll()).build();
    }

    /**
     *
     * @param id
     * @return
     * @throws NotFoundException
     */
    @GET
    @Path("{id}")
    @Transactional
    @ApiOperation(value = "Código",
                  notes = "Busca Fabricante, Modelo e Ano por ID",
                  response = Fabricante.class
    )
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Fabricante inválido"),
        @ApiResponse(code = 404, message = "Fabricante não existe")})
    public Response load(@PathParam("id") Integer id) throws NotFoundException {
        Fabricante result = bc.load(id);

        if (result == null) {
            throw new NotFoundException();
        }

        return Response.ok().entity(result).build();
    }

    /**
     *
     * @param field
     * @param order
     * @param init
     * @param qtde
     * @return
     * @throws NotFoundException
     */
    @GET
    @Path("list/{field}/{order}/{init}/{qtde}")
    @Transactional
    @ApiOperation(value = "Lista com paginação no servidor",
                  notes = "Informe o campo/ordem(asc/desc)/posição do primeiro registro/quantidade de registros"
    )
    public Response list(@PathParam("field") String field, @PathParam("order") String order, @PathParam("init") int init, @PathParam("qtde") int qtde) throws NotFoundException {
        if ((order.equalsIgnoreCase("asc") || order.equalsIgnoreCase("desc")) && (Util.fieldInClass(field, Fabricante.class))) {
            return Response.ok().entity(bc.list(field, order, init, qtde)).build();
        }
        return Response.ok().entity(null).build();
    }

    /**
     *
     * @return @throws NotFoundException
     */
    @GET
    @Path("count")
    @Transactional
    @ApiOperation(value = "Quantidade de registro",
                  notes = "Usado para trabalhar as tabelas com paginação no servidor",
                  response = Integer.class
    )
    public Response count() throws NotFoundException {
        return Response.ok().entity(bc.count()).build();
    }

//    /**
//     *
//     * @param temaId
//     * @return
//     */
//    @GET
//    @Path("ano/{ano}")
//    @Transactional
//    public Response listarPorAno(@NotNull @PathParam("ano") String temaId) {
//        return bc.listarPorAno(temaId);
//    }
//
//    /**
//     *
//     * @param temaId
//     * @return
//     */
//    @GET
//    @Path("listarProdutosUnicosPorTema/{temaId}")
//    @Transactional
//    public Response listarProdutosUnicosPorTema(@NotNull @PathParam("temaId") Long temaId) {
//        return Response.ok().entity(bc.listarProdutosUnicosPorTema(temaId)).build();
//    }
//
//    /**
//     *
//     * @param nomeProduto
//     * @return
//     */
//    @GET
//    @Path("listarProdutosSemFase/{produto}")
//    @Transactional
//    public Response produtosSemFase(@PathParam("produto") String nomeProduto) {
//        return Response.ok().entity(bc.listarProdutosSemFase(nomeProduto)).build();
//    }
}
