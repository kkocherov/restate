package restate;

import java.util.Objects;

public class ApartmentDemand extends Demand<Apartment> implements Cloneable {
    Integer minFloor;
    Integer maxFloor;
    Integer minBeds;
    Integer maxBeds;
    Integer minBathrooms;
    Integer maxBathrooms;
    Integer minRooms;
    Integer maxRooms;
    Double  minTotalArea;
    Double  maxTotalArea;
    Boolean hasAlarmSystem;
    Boolean hasTV;
    Boolean hasInternet;
    Boolean newBuilding;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ApartmentDemand that = (ApartmentDemand) o;
        return Objects.equals(minFloor, that.minFloor) &&
                Objects.equals(maxFloor, that.maxFloor) &&
                Objects.equals(minBeds, that.minBeds) &&
                Objects.equals(maxBeds, that.maxBeds) &&
                Objects.equals(minBathrooms, that.minBathrooms) &&
                Objects.equals(maxBathrooms, that.maxBathrooms) &&
                Objects.equals(minRooms, that.minRooms) &&
                Objects.equals(maxRooms, that.maxRooms) &&
                Objects.equals(minTotalArea, that.minTotalArea) &&
                Objects.equals(maxTotalArea, that.maxTotalArea) &&
                Objects.equals(hasAlarmSystem, that.hasAlarmSystem) &&
                Objects.equals(hasTV, that.hasTV) &&
                Objects.equals(hasInternet, that.hasInternet) &&
                Objects.equals(newBuilding, that.newBuilding);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), minFloor, maxFloor, minBeds, maxBeds, minBathrooms, maxBathrooms, minRooms, maxRooms, minTotalArea, maxTotalArea, hasAlarmSystem, hasTV, hasInternet, newBuilding);
    }
}
