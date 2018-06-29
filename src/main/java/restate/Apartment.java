package restate;

import java.util.Objects;

public class Apartment extends RealEstate implements Cloneable {
    Integer floor;
    Integer beds; // спальные места
    Integer bathrooms;
    Integer rooms;
    Boolean hasAlarmSystem; // домофон
    Boolean hasTV;
    Boolean hasInternet;
    Boolean newBuilding; // в новостройке
    Double totalArea;

    public Apartment(Address address) {
        super(address);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Apartment apartment = (Apartment) o;
        return Objects.equals(floor, apartment.floor) &&
                Objects.equals(beds, apartment.beds) &&
                Objects.equals(bathrooms, apartment.bathrooms) &&
                Objects.equals(rooms, apartment.rooms) &&
                Objects.equals(hasAlarmSystem, apartment.hasAlarmSystem) &&
                Objects.equals(hasTV, apartment.hasTV) &&
                Objects.equals(hasInternet, apartment.hasInternet) &&
                Objects.equals(newBuilding, apartment.newBuilding) &&
                Objects.equals(totalArea, apartment.totalArea);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), floor, beds, bathrooms, rooms, hasAlarmSystem, hasTV, hasInternet, newBuilding, totalArea);
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "address=" + address +
                '}';
    }
}
