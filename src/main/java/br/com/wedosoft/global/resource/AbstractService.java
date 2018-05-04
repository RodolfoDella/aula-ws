package br.com.wedosoft.global.resource;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;

public abstract class AbstractService<T, PK> {

    @PersistenceContext
    private EntityManager em;

    private Class<T> tClass;

    public T save(T entity) {
        return em.merge(entity);
    }

    public void delete(PK id) {
        T entity = em.getReference(getClazz(), id);
        if (entity != null) {
            em.remove(entity);
        }
    }

    public void deleteOrThrow(PK id) {
        T entity = em.getReference(getClazz(), id);
        if (entity != null) {
            em.remove(entity);
        } else {
            throw new EntityNotFoundException("Entidade com id " + id.toString() + " não encontrada.");
        }
    }

    private Class<T> getClazz() {
        if (tClass == null) {
            ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
            tClass = (Class<T>) type.getActualTypeArguments()[0];
        }
        return tClass;
    }

}
