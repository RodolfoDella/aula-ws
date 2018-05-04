package br.com.wedosoft.imagem;

import br.com.wedosoft.global.resource.CrudResource;

import javax.ejb.Stateless;
import javax.ws.rs.Path;

@Stateless
@Path("imagens")
public class ImagemResource extends CrudResource<ImagemModel, Long> {

}
