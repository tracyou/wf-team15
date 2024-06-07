package app.models;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Cabin_find_by_type", query = "SELECT c FROM Cabin c WHERE c.type=?1"),
        @NamedQuery(name = "Cabin_find_by_locationName", query = "SELECT c FROM Cabin c WHERE c.location LIKE ?1"),
        @NamedQuery(name = "Rental_find_by_cabinId_and_period", query = "SELECT r FROM Rental r WHERE (r.cabin.id = ?1 AND r.startDate >= ?2) OR (r.cabin.id = ?1 AND r.endDate <= ?3)")
})
public class Cabin {
    public interface SummaryView {
    }
    @Id
    @SequenceGenerator(name = "Cabins_ids", initialValue = 20001)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Cabins_ids")
    @Column(name = "id", nullable = false)
    @JsonView(SummaryView.class)
    private Long id = 0L;

    @JsonView(SummaryView.class)
    CabinType type;
    String location;
    @JsonView(SummaryView.class)
    String description;
    String image;
    int pricePerWeek;
    @JsonView(SummaryView.class)
    int numAvailable;

    @OneToMany(mappedBy = "cabin")
//    @JoinColumn(name = "rental_rental_id")
    public List<Rental> rentals = new ArrayList<>();

    public Cabin(long id, CabinType type, String location, String description, String image, int pricePerWeek, int numAvailable) {
        this.id = id;
        this.type = type;
        this.location = location;
        this.description = description;
        this.image = image;
        this.pricePerWeek = pricePerWeek;
        this.numAvailable = numAvailable;
    }

    public Cabin(long id, CabinType type) {
        this.id = id;
        this.type = type;
    }

    public Cabin() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CabinType getType() {
        return type;
    }

    public void setType(CabinType type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public static void setLocation(String[] location) {
        Location = location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPricePerWeek() {
        return pricePerWeek;
    }

    public void setPricePerWeek(int pricePerWeek) {
        this.pricePerWeek = pricePerWeek;
    }

    public int getNumAvailable() {
        return numAvailable;
    }

    public void setNumAvailable(int numAvailable) {
        this.numAvailable = numAvailable;
    }

    public static String[] getImages() {
        return Images;
    }

    public static void setImages(String[] images) {
        Images = images;
    }

    public boolean associateRental(Rental rental) {
        if (!rentals.contains(rental)) {
            rentals.add(rental);
            return true;
        }
        return false;
    }

    public boolean dissociateRental(Rental rental) {
        if (rentals.contains(rental)) {
            rentals.remove(rental);
            rental.setCabin(null);
            return true;
        }
        return false;
    }


    public static Cabin createSampleCabin(long pId) {

        long id = pId;
        CabinType type = randomType();
        String location = randomLocation();
        String description = descriptionGenerator(type);

        if (type == CabinType.BeachGear) {
            description = null;
        }

        String image = randomImage();
        int price = (int) Math.round(Math.random() * (2500 - 100) + 100);
        int available = (int) Math.round(Math.random() * (50 - 1) + 1);

        return new Cabin(id, type, location, description, image, price, available);
    }

    static String[] Images =
            {"https://www.novascotia.com/sites/default/files/inline-images/ARGYLER%20LODGE%201920x1080.jpg",
                    "https://www.surfenbeachstrandhuisjes.nl/uploads/images/820x530/IMG_6043.jpg",
                    "https://w0.peakpx.com/wallpaper/700/114/HD-wallpaper-cabin-in-the-tropics-ocean-tropics-sky-cabin.jpg",
                    "https://www.boutique-homes.com/storage/images/vacation-rentals/europe/norway/sea-cabins/cabins_for_rent_norway_002.jpg?s=f&t=2",
                    "https://www.kiamacoast.com.au/surf-beach-holiday-park/wp-content/uploads/2017/02/NEGphotography_Surf-Beach-Cabin-25-Admiral_7.jpg"};

    private static String randomImage() {
        return Cabin.Images[(int) Math.round(Math.random() * (4))];
    }

    static String descriptionGenerator(CabinType type) {
        String[] word = {"colourful", "modern", "spacy", "comfortable", "white", "cosy", "characteristics"};

        return word[(int) Math.round(Math.random() * (6))] + " " + type + " model-" + Math.round(Math.random() * (5 - 1) + 1);
    }


    static CabinType randomType() {
        int number = (int) Math.round(Math.random() * (5 - 1) + 1);

        if (number == 1) {
            return CabinType.BeachGear;
        } else if (number == 2) {
            return CabinType.SmallDayTime;
        } else if (number == 3) {
            return CabinType.SmallLodge;
        } else if (number == 4) {
            return CabinType.LargeLodge;
        } else {
            return CabinType.FamilyLodge;
        }
    }

    public enum CabinType {
        BeachGear,
        SmallDayTime,
        SmallLodge,
        LargeLodge,
        FamilyLodge
    }

    static String[] Location = {
            "Turquoise Coast, Turkey",
            "Big Sur, California",
            "Antrim Coast, Northern Ireland",
            "Malabar Coast, India",
            "Skeleton Coast, Namibia",
            "Garden Route, South Africa",
            "Great Ocean Road, Australia",
            "Nā Pali Coast, Hawaii",
            "Riviera Maya, Mexico",
            "Dalmatian Coast, Croatia",
            "Raja Ampat, Indonesia"
    };

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setId(Long id) {
        this.id = id;
    }

    static String randomLocation() {
        return Location[(int) Math.round(Math.random() * (10))];
    }

}
