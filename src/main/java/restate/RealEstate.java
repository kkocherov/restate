package restate;

import java.util.Objects;

public abstract class RealEstate implements Cloneable {
    Address address;
    Coordinate coordinate;
    Long id;

    public RealEstate(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RealEstate that = (RealEstate) o;
        return Objects.equals(address, that.address) &&
                Objects.equals(coordinate, that.coordinate);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        RealEstate restate = (RealEstate) super.clone();

        if (this.coordinate != null) {
            restate.coordinate = (Coordinate) this.coordinate.clone();
        }

        if (this.address != null) {
            restate.address = (Address) this.address.clone();
        }

        return restate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, coordinate);
    }
}
