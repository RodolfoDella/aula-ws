package br.com.wedosoft.municipio;

import br.com.wedosoft.global.resource.CrudResource;

import javax.ejb.Stateless;
import javax.ws.rs.Path;

@Stateless
@Path("municipios")
public class MunicipioResource extends CrudResource<MunicipioModel, Long> {

}
