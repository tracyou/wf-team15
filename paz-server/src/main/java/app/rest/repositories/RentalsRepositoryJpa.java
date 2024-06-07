package app.rest.repositories;

import app.models.Rental;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class RentalsRepositoryJpa implements RentalsRepository {
    @PersistenceContext
    protected EntityManager entityManager;

    public Rental save(Rental rental) {
        return this.entityManager.merge(rental);
    }

    public List<Rental> findAll() {
        TypedQuery<Rental> query =
                this.entityManager.createQuery(
                        "select a from Rental a", Rental.class);
        return query.getResultList();
    }

    public Rental findById(long id) {
        return this.entityManager.find(Rental.class, id);
    }

    public boolean deleteById(long id) {
        Rental rental = findById(id);
        entityManager.remove(rental);
        return (rental != null);
    }

    @Override
    public List<Rental> findByQuery(String jpqlName, Object... params) {
        TypedQuery<Rental> query = this.entityManager.createNamedQuery(
                jpqlName, Rental.class);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i + 1, params[i]);
        }
        return query.getResultList();
    }

}
