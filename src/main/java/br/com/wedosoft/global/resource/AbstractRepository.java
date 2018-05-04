package br.com.wedosoft.global.resource;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractRepository<T, PK> {

    @PersistenceContext
    private EntityManager em;

    private Class<T> tClass;

    public Page<T> findAll(String contexto, Pageable pageable, List<SearchCriteria> search, List<SortCriteria> sort) {

        ResultList<T> content = findAll(pageable.getFirstResult(), pageable.getMaxResults(), search, sort);

        Page<T> page = Page.<T>builder()
                .content(content.getListElements())
                .firstResult(pageable.getFirstResult())
                .maxResults(pageable.getMaxResults())
                .numberOfElements(content.getListElements().size())
                .numberOfListElements(content.getNumberOfListElements())
                .linkAnterior(contexto)
                .build();
        return page;
    }

    private ResultList<T> findAll(Integer firstResult, Integer maxResults, List<SearchCriteria> search, List<SortCriteria> sort) {
        CriteriaQuery query;

        if (search.isEmpty()) {
            query = createQuerySemFiltro(sort);
        } else {
            query = createQueryComFiltro(search, sort);
        }

        TypedQuery<T> typedQuery = em.createQuery(query);
        ResultList<T> results = new ResultList<T>();
        results.setNumberOfListElements(typedQuery.getResultList().size());

        if (firstResult != null) {
            typedQuery.setFirstResult(firstResult);
        }

        if (maxResults != null) {
            typedQuery.setMaxResults(maxResults);
        }

        results.setListElements(typedQuery.getResultList());

        return results;
    }

    public T find(PK id) {
        T entity = em.find(getClazz(), id);
        return entity;
    }

    public T findOrThrow(PK id) {
        T entity = find(id);
        if (entity == null) {
            throw new EntityNotFoundException("Entidade com id " + id.toString() + " não encontrada.");
        }
        return entity;
    }

    public T findReferenceOrThrow(PK id) {
        T entity = em.getReference(getClazz(), id);
        return entity;
    }

    protected Class<T> getClazz() {
        if (tClass == null) {
            ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
            tClass = (Class<T>) type.getActualTypeArguments()[0];
        }
        return tClass;
    }

    public CriteriaQuery<T> createQuerySemFiltro(List<SortCriteria> sorts) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(getClazz());
        Root root = query.from(getClazz());

        if (!sorts.isEmpty()) {
            List<Order> orders = getListOrders(builder, root, sorts);
            query.orderBy(orders);
        }

        return query;
    }

    private CriteriaQuery<T> createQueryComFiltro(List<SearchCriteria> params, List<SortCriteria> sorts) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(getClazz());
        Root root = query.from(getClazz());

        Predicate predicate = getSearchPredicate(builder, root, params);
        query.where(predicate);

        if (!sorts.isEmpty()) {
            List<Order> orders = getListOrders(builder, root, sorts);
            query.orderBy(orders);
        }

        return query;
    }

    private Predicate getSearchPredicate(CriteriaBuilder builder, Root root, List<SearchCriteria> params) {
        Predicate predicate = builder.conjunction();
        for (SearchCriteria param : params) {
            Path path = getFieldFilters(param, root);

            if (param.getOperation().equalsIgnoreCase(">")) {
                predicate = builder.and(predicate,
                        builder.greaterThanOrEqualTo(path,
                                param.getValue().toString()));
            } else if (param.getOperation().equalsIgnoreCase("<")) {
                predicate = builder.and(predicate,
                        builder.lessThanOrEqualTo(path,
                                param.getValue().toString()));
            } else if (param.getOperation().equalsIgnoreCase(":")) {
                if (path.getJavaType() == String.class) {
                    predicate = builder.and(predicate,
                            builder.like(builder.lower(path),
                                    "%" + param.getValue().toString().toLowerCase() + "%"));
                } else {
                    predicate = builder.and(predicate,
                            builder.equal(path, param.getValue()));
                }
            }
        }
        return predicate;
    }

    private List<Order> getListOrders (CriteriaBuilder builder, Root root, List<SortCriteria> sorts) {
        List<Order> orderList = new ArrayList<>();

        Predicate predicate = builder.conjunction();

        for (SortCriteria sort : sorts) {
            //Path path = getFieldOrdering(sort, root);

            if (sort.getOrder().startsWith("-")) {
                orderList.add(builder.desc(root.get(sort.getKey())));
            } else {
                orderList.add(builder.asc(root.get(sort.getKey())));
            }
        }

        return orderList;
    }

    private Path getFieldFilters(SearchCriteria param, Root root) {
        Pattern pattern = Pattern.compile("(\\w+\\.)+(\\w+)$");
        Matcher matcher = pattern.matcher(param.getKey());

        //Verifica se o campo para filtrar é uma classe de relacionamento
        return getPathFields(root, matcher, param.getKey(), param, null);

    }

    private Path getFieldOrdering(SortCriteria sort, Root root) {
        Pattern pattern = Pattern.compile("(\\w+\\.)+(\\w+)$");
        Matcher matcher = pattern.matcher(sort.getKey());

        //Verifica se o campo para ordenar é uma classe de relacionamento
        return getPathFields(root, matcher, sort.getKey(), null, sort);

    }

    private Path getPathFields(Root root, Matcher matcher, String key, SearchCriteria param, SortCriteria sorts) {
        Path path = null;

        if (matcher.find()) {
            String caminhoCompletoParaCampo = matcher.group(0);
            String[] caminhoParaOCampoOrganizadoEmList = caminhoCompletoParaCampo.split("[.]");

            for (String campo : caminhoParaOCampoOrganizadoEmList) {
                if (path == null) {
                    path = root.get(campo);
                } else {
                    path = path.get(campo);
                }
            }

        } else {
            path = root.get(key);
        }

        return path;
    }
}
