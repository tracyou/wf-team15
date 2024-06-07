package app.rest.repositories;

import app.models.Rental;

import java.util.List;

public interface RentalsRepository {

    Rental save(Rental rental);

    List<Rental> findAll();

    Rental findById(long id);

    boolean deleteById(long id);

    List<Rental> findByQuery(String jpqlName, Object... params); // finds all instances from a named JPQL-query

}
