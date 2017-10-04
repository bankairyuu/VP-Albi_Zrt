package backend.repositories;

import play.db.jpa.JPAApi;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by herperger on 2/21/17.
 */
public class GenericRepository<T> {

    private final JPAApi jpaApi;

    protected Class<T> entityClass;

    public GenericRepository(Class<T> entityClass, JPAApi jpaApi) {
        this.jpaApi = jpaApi;
        this.entityClass = entityClass;
    }

    protected EntityManager em() {
        return jpaApi.em();
    }

    public List<T> findAll() {
        CriteriaBuilder cb = em().getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> rootEntry = cq.from(entityClass);
        CriteriaQuery<T> all = cq.select(rootEntry);

        final TypedQuery<T> query = em().createQuery(all);
        return query.getResultList();
    }

    public T findById(Object id) {
        return em().find(entityClass, id);
    }

    public T save(T t) {
        return em().merge(t);
    }

    public void delete(T t) {
        em().remove(t);
    }
}
