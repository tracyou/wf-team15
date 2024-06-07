package app.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Rental {
    @Id
    @Column(name = "rental_id", nullable = false)
    @SequenceGenerator(name = "Rental_ids", initialValue = 31000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Rental_ids")
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "cabin_id")
    @JsonIgnore
    public Cabin cabin;

    private enum status {
        REQUESTED,
        APPROVED,
        DECLINED,
        PAID,
        FULFILLED,
        CANCELLED,
        BLOCK
    }

    public Rental() {

    }

    public Rental(Long id, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Cabin getCabin() {
        return cabin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCabin(Cabin cabin) {
        this.cabin = cabin;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean associateCabin(Cabin cabin) {
        if (cabin != null && this.cabin == null) {
            setCabin(cabin);
            return true;
        }
        return false;
    }

}
