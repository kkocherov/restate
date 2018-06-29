package restate;

import java.util.Objects;

public class House extends RealEstate implements Cloneable {
    Integer totalFloors;
    Integer builtYear;
    Integer beds;
    Integer bathrooms;
    Integer rooms;
    Double houseArea; // площадь дома
    Double landArea; // площадь участка
    Boolean surveyed; // проведено межевание
    Boolean hasGas;
    Boolean hasElectricity;
    Boolean hasWater;
    Boolean hasHeating; // отопление

    public House(Address address) {
        super(address);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        House house = (House) o;
        return Objects.equals(totalFloors, house.totalFloors) &&
                Objects.equals(builtYear, house.builtYear) &&
                Objects.equals(beds, house.beds) &&
                Objects.equals(bathrooms, house.bathrooms) &&
                Objects.equals(rooms, house.rooms) &&
                Objects.equals(houseArea, house.houseArea) &&
                Objects.equals(landArea, house.landArea) &&
                Objects.equals(surveyed, house.surveyed) &&
                Objects.equals(hasGas, house.hasGas) &&
                Objects.equals(hasElectricity, house.hasElectricity) &&
                Objects.equals(hasWater, house.hasWater) &&
                Objects.equals(hasHeating, house.hasHeating);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), totalFloors, builtYear, beds, bathrooms, rooms, houseArea, landArea, surveyed, hasGas, hasElectricity, hasWater, hasHeating);
    }

    @Override
    public String toString() {
        return "House{" +
                "address=" + address +
                '}';
    }
}
