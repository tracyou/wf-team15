package app.rest.repositories;

import app.models.Cabin;

import java.util.List;

public interface CabinsRepository {
    List<Cabin> findAll();

    Cabin findById(long id);

    Cabin save(Cabin aEvent);

    boolean deleteById(long id);
    List<Cabin> findByQuery(String jpqlName, Object... params);

}
