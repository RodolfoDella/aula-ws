package br.com.wedosoft.pessoa;

import br.com.wedosoft.global.resource.CrudResource;

import javax.ejb.Stateless;
import javax.ws.rs.Path;

@Stateless
@Path("pessoas")
public class PessoaResource extends CrudResource<PessoaModel, Long> {

}
