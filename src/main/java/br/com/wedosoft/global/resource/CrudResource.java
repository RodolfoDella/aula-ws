package br.com.wedosoft.global.resource;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public abstract class CrudResource<T extends EntityId, PK> {

    @Inject
    private AbstractRepository<T, PK> repository;

    @Inject
    private AbstractService<T, PK> service;

    @GET
    public Response findAll(
            @QueryParam("firstResult") Integer firstResult,
            @QueryParam("maxResults") Integer maxResults,
            @QueryParam("search") String search,
            @QueryParam("order") String orderBy,
            @Context UriInfo contexto) {

        Pageable pageable = Pageable.of(firstResult, maxResults);
        List<SearchCriteria> params = new ArrayList<SearchCriteria>();
        List<SortCriteria> sorts = new ArrayList<SortCriteria>();

        if (search != null) {
            params = searchList(search);
        }

        if (orderBy != null) {
            sorts = sortList(orderBy);
        }

        Page<T> page = repository.findAll(contexto.getRequestUri().toString(), pageable, params, sorts);

        return Response.ok(page).build();
    }

    @GET
    @Path(value = "{id}")
    public Response find(@PathParam(value = "id") PK id) {
        return Response.ok(repository.findOrThrow(id)).build();
    }

    @POST
    public Response create(T entidade, @Context UriInfo info) {
        T entidade_ = service.save(entidade);
        UriBuilder builder = info.getAbsolutePathBuilder();
        builder.path(entidade_.getId().toString());
        return Response.created(builder.build()).entity(entidade_).build();
    }

    @DELETE
    @Path(value = "{id}")
    public Response delete(@PathParam(value = "id") PK id) {
        service.deleteOrThrow(id);
        return Response.noContent().build();
    }

    @PUT
    @Path(value = "{id}")
    public Response update(@PathParam(value = "id") PK id, T entidade) {
        service.save(entidade);
        entidade = repository.findReferenceOrThrow(id);
        return Response.ok().entity(entidade).build();
        //return Response.noContent().build();
    }

    @PATCH
    @Path(value = "{id}")
    public Response patchCustomer(@PathParam("id") PK id, ObjectPatch patch) {
        T entidade = repository.find(id);
        patch.apply(entidade);
        service.save(entidade);
        return Response.ok().entity(entidade).build();
        //return Response.noContent().build();
    }

    private List<SearchCriteria> searchList (String string) {
        List<SearchCriteria> list = new ArrayList<SearchCriteria>();

        Pattern pattern = Pattern.compile("(\\w.+?)(:|<|>)([\\w ]+?),");
        Matcher matcher = pattern.matcher(string + ",");
        while (matcher.find()) {
            list.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));
        }

        return list;
    }

    private List<SortCriteria> sortList (String string) {
        List<SortCriteria> list = new ArrayList<SortCriteria>();

        if (!string.substring(0,1).equals("+") && !string.substring(0,1).equals("-")) {
            string = "+"+string;
        }

        string = string.replaceAll("(,(?![+-]))",",+");

        // "([+-])([\\w+\\.]+?)," Regex para pegar string de subclasse
        Pattern pattern = Pattern.compile("([+-])([\\w]+?),");
        Matcher matcher = pattern.matcher(string + ",");

        while (matcher.find()) {
            list.add(new SortCriteria(matcher.group(1),matcher.group(2)));
        }

        return list;
    }
}
