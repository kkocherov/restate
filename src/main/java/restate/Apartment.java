package restate;

public class Apartment extends RealEstate {
    public Integer number;
    public Integer floor;
    public Integer beds;
    public Integer bathrooms;
    public Integer rooms;
    public Double totalArea;
    public Boolean hasAlarmSystem;
    public Boolean hasTV;
    public Boolean hasInternet;

    public Apartment(Address address, Integer number) {
        this.address = address;
        this.number = number;
    }
}
