package app;

import app.models.Cabin;
import app.models.Rental;
import app.rest.repositories.CabinsRepository;
import app.rest.repositories.RentalsRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class PazServerApplication implements CommandLineRunner {

    @Qualifier("cabinsRepositoryJpa")
    @Autowired
    private CabinsRepository cabinsRepository;

    @Autowired
    private RentalsRepositoryJpa rentalsRepositoryJpa;

    public static void main(String[] args) {
        SpringApplication.run(PazServerApplication.class, args);
    }

    @Transactional
    @Override
    public void run(String... args) {
        System.out.println("Running CommandLine Startup");
        this.createInitialCabins();
    }

    private void createInitialCabins() {
        List<Cabin> cabins = this.cabinsRepository.findAll();
        if (cabins.size() > 0) return;
        System.out.println("Configuring some initial aEvent data");
        int idRental = 31000;

        for (int i = 0; i < 6; i++) {
            Cabin cabin = Cabin.createSampleCabin(0);
            Cabin savedCabin = this.cabinsRepository.save(cabin);

            LocalDate startDate = generateStartDate();
            Rental rental = new Rental((long) (idRental + i), startDate, startDate.plusDays(49));
            rental.associateCabin(savedCabin);
            this.rentalsRepositoryJpa.save(rental);
            savedCabin.associateRental(rental);
        }
    }

    private LocalDate generateStartDate(){
        long minDay = LocalDate.of(2023, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2024, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        System.out.println(randomDate);
        return randomDate;
    }

}
