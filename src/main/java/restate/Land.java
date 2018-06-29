package restate;

import java.util.Objects;

public class Land extends RealEstate implements Cloneable {
    Double area;
    Boolean surveyed; // проведено межевание

    public Land(Address address) {
        super(address);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Land land = (Land) o;
        return Double.compare(land.area, area) == 0 &&
                Objects.equals(surveyed, land.surveyed);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), area, surveyed);
    }

    @Override
    public String toString() {
        return "Land{" +
                "area=" + area +
                ", surveyed=" + surveyed +
                '}';
    }
}
