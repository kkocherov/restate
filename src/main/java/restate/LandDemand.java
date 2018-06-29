package restate;

import java.util.Objects;

public class LandDemand extends Demand<Land> implements Cloneable {
    Double minArea;
    Double maxArea;
    Boolean surveyed;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LandDemand that = (LandDemand) o;
        return Objects.equals(minArea, that.minArea) &&
                Objects.equals(maxArea, that.maxArea) &&
                Objects.equals(surveyed, that.surveyed);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), minArea, maxArea, surveyed);
    }
}
