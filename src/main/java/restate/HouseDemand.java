package restate;

import java.util.Objects;

public class HouseDemand extends Demand<House> implements Cloneable {
    Integer minTotalFloors;
    Integer maxTotalFloors;
    Integer minBuiltYear;
    Integer maxBuiltYear;
    Integer minBeds;
    Integer maxBeds;
    Integer minBathrooms;
    Integer maxBathrooms;
    Integer minRooms;
    Integer maxRooms;
    Double minHouseArea;
    Double maxHouseArea;
    Double minLandArea;
    Double maxLandArea;
    Boolean surveyed;
    Boolean hasGas;
    Boolean hasElectricity;
    Boolean hasWater;
    Boolean hasHeating;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        HouseDemand that = (HouseDemand) o;
        return Objects.equals(minTotalFloors, that.minTotalFloors) &&
                Objects.equals(maxTotalFloors, that.maxTotalFloors) &&
                Objects.equals(minBuiltYear, that.minBuiltYear) &&
                Objects.equals(maxBuiltYear, that.maxBuiltYear) &&
                Objects.equals(minBeds, that.minBeds) &&
                Objects.equals(maxBeds, that.maxBeds) &&
                Objects.equals(minBathrooms, that.minBathrooms) &&
                Objects.equals(maxBathrooms, that.maxBathrooms) &&
                Objects.equals(minRooms, that.minRooms) &&
                Objects.equals(maxRooms, that.maxRooms) &&
                Objects.equals(minHouseArea, that.minHouseArea) &&
                Objects.equals(maxHouseArea, that.maxHouseArea) &&
                Objects.equals(minLandArea, that.minLandArea) &&
                Objects.equals(maxLandArea, that.maxLandArea) &&
                Objects.equals(surveyed, that.surveyed) &&
                Objects.equals(hasGas, that.hasGas) &&
                Objects.equals(hasElectricity, that.hasElectricity) &&
                Objects.equals(hasWater, that.hasWater) &&
                Objects.equals(hasHeating, that.hasHeating);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), minTotalFloors, maxTotalFloors, minBuiltYear, maxBuiltYear, minBeds, maxBeds, minBathrooms, maxBathrooms, minRooms, maxRooms, minHouseArea, maxHouseArea, minLandArea, maxLandArea, surveyed, hasGas, hasElectricity, hasWater, hasHeating);
    }
}
