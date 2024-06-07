package app.rest.repositories;

import app.models.Cabin;
import app.models.Rental;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CabinsRepositoryJpa implements CabinsRepository {
    @PersistenceContext
    protected EntityManager entityManager;

    public Cabin save(Cabin cabin) {
        return this.entityManager.merge(cabin);
    }

    public List<Cabin> findAll() {
        TypedQuery<Cabin> query =
                this.entityManager.createQuery(
                        "select a from Cabin a", Cabin.class);
        return query.getResultList();
    }

    public Cabin findById(long id) {
        return this.entityManager.find(Cabin.class, id);
    }

    public boolean deleteById(long id) {
        Cabin cabin = findById(id);

        if (cabin != null) {
            List<Rental> rentals = cabin.getRentals();
            for (Rental rental : rentals) {
                rental.setCabin(null);
            }

            entityManager.remove(cabin);
            return true;
        }

        return false;
    }

    @Override
    public List<Cabin> findByQuery(String jpqlName, Object... params) {
        TypedQuery<Cabin> query = entityManager.createNamedQuery(jpqlName, Cabin.class);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i + 1, params[i]);
        }
        return query.getResultList();
    }


}
