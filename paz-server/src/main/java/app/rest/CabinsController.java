package app.rest;

import app.exceptions.PreConditionFailed;
import app.exceptions.ResourceNotFound;
import app.models.Cabin;
import app.models.Rental;
import app.rest.repositories.CabinsRepository;
import app.rest.repositories.RentalsRepositoryJpa;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping("/pazapi/cabins")
public class CabinsController {

    final CabinsRepository cabinsRepository;
    final RentalsRepositoryJpa rentalsRepository;

    @Autowired
    public CabinsController(@Qualifier("cabinsRepositoryJpa") CabinsRepository cabinsRepository, RentalsRepositoryJpa rentalsRepository) {
        this.cabinsRepository = cabinsRepository;
        this.rentalsRepository = rentalsRepository;
    }

    @GetMapping("")
    public List<Cabin> getAllCabins(@RequestParam(required = false) Cabin.CabinType type,
                                    @RequestParam(required = false) String location) {

        if (type != null) {
            return cabinsRepository.findByQuery("Cabin_find_by_type", type);
        }
        if (location != null) {
            return cabinsRepository.findByQuery("Cabin_find_by_locationName", location);
        }
        return cabinsRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cabin getCabin(@PathVariable long id) throws ResourceNotFound {
        if (cabinsRepository.findAll().contains(cabinsRepository.findById(id))) {
            return cabinsRepository.findById(id);
        } else {
            throw new ResourceNotFound("the specified cabin does not exist");
        }
    }

    @GetMapping(path = "{id}/rentals")
    public List<Rental> getAllRentals( String from,
                                       String to,
                                      @PathVariable String id) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        LocalDate fromFormatted = null;
        LocalDate toFormatted = null;
        
        if (from != null){
            fromFormatted = LocalDate.parse(from, formatter);
        }
        
        if (to != null){
            toFormatted = LocalDate.parse(to, formatter);
        }

        return rentalsRepository.findByQuery("Rental_find_by_cabinId_and_period", id, fromFormatted, toFormatted);
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    public ResponseEntity<Cabin> saveCabin(@RequestBody Cabin cabin) {
        Cabin savedCabin = cabinsRepository.save(cabin);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCabin.getId()).toUri();

        return ResponseEntity.created(location).body(savedCabin);
    }

    @PostMapping(value = "/{cabinId}/rentals",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    public ResponseEntity<Rental> saveRentalToCabin(@RequestBody Rental rental, @PathVariable long cabinId) throws PreConditionFailed {
        Cabin selectedCabin = cabinsRepository.findById(cabinId);

        if (selectedCabin == null) {
            throw new PreConditionFailed("There is no match.");
        } else if (rental.getStartDate().compareTo(rental.getEndDate()) > 0) {
            throw new PreConditionFailed("The end date is not after the start date.");
        } else {
            long durationDays = ChronoUnit.DAYS.between(rental.getStartDate(), rental.getEndDate());
            long remainingDays = durationDays % 7;

            if (remainingDays == 0) {
                throw new PreConditionFailed("The duration between start date and end date is not a multiple of 7 (whole weeks).");
            } else {
                Rental newRental = rentalsRepository.save(rental);
                newRental.setCabin(selectedCabin);
                selectedCabin.associateRental(newRental);
                cabinsRepository.save(selectedCabin);
                return ResponseEntity.ok().body(newRental);
            }
        }
    }

    @PutMapping("/{id}")
    public Cabin updateCabin(@RequestBody Cabin cabin, @PathVariable long id) throws PreConditionFailed {
        if (cabin.getId() == id) {
            return cabinsRepository.save(cabin);
        } else {
            throw new PreConditionFailed("Cabin-Id=" + cabin.getId() + "does not math path parameter=" + id);
        }
    }

    @DeleteMapping("/{id}")
    public boolean deleteCabin(@PathVariable long id) throws ResourceNotFound {
        if (cabinsRepository.findAll().contains(cabinsRepository.findById(id))) {
            return cabinsRepository.deleteById(id);
        } else {
            throw new ResourceNotFound("the specified cabin does not exist");
        }
    }


    @GetMapping("/summary")
    @JsonView(Cabin.SummaryView.class)
    public List<Cabin> getCabinSummary() {
        return cabinsRepository.findAll();
    }
}
