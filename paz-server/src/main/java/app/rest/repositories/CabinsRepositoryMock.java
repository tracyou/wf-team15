package app.rest.repositories;

import app.models.Cabin;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CabinsRepositoryMock implements CabinsRepository {

    private final List<Cabin> cabinList;
    static long id = 30001;

    public CabinsRepositoryMock() {
        this.cabinList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            this.addCabin(id);
            id++;
        }
    }

    private void addCabin(long id) {
        this.cabinList.add(Cabin.createSampleCabin(id));
    }

    @Override
    public List<Cabin> findAll() {
        return this.cabinList;
    }

    @Override
    public Cabin findById(long id) {
        for (Cabin cabin : this.cabinList) {
            if (cabin.getId() == id) {
                return cabin;
            }
        }
        return null;
    }

    @Override
    public Cabin save(Cabin cabin) {
        if (cabin.getId() == 0) {
            cabin.setId(id);
//            cabin.se("The fantastic event - " + id);
            this.cabinList.add(cabin);
            id++;
        } else {
            Cabin extraCabin = findById(cabin.getId());
            int index = cabinList.indexOf(extraCabin);
            cabinList.set(index, cabin);
        }
        return cabin;
    }

    @Override
    public boolean deleteById(long id) {
        for (Cabin cabin : this.cabinList) {
            if (cabin.getId() == id) {
                this.cabinList.remove(cabin);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Cabin> findByQuery(String jpqlName, Object... params) {
        return null;
    }
}
