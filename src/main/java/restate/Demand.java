package restate;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public abstract class Demand<T extends RealEstate> implements Cloneable {
    Client client;
    Agent agent;
    DealType dealType;
    Integer minPrice;
    Integer maxPrice;
    Boolean active;
    Date publishedDate;
    List<Coordinate> polygon;
    Coordinate circleCenter;
    Double circleRadius;
    Address address;
    Boolean withAnimals;
    Boolean withChildren;
    Boolean married;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Demand<?> demand = (Demand<?>) o;
        return Objects.equals(client, demand.client) &&
                Objects.equals(agent, demand.agent) &&
                dealType == demand.dealType &&
                Objects.equals(minPrice, demand.minPrice) &&
                Objects.equals(maxPrice, demand.maxPrice) &&
                Objects.equals(active, demand.active) &&
                Objects.equals(publishedDate, demand.publishedDate) &&
                Objects.equals(polygon, demand.polygon) &&
                Objects.equals(circleCenter, demand.circleCenter) &&
                Objects.equals(circleRadius, demand.circleRadius) &&
                Objects.equals(address, demand.address) &&
                Objects.equals(withAnimals, demand.withAnimals) &&
                Objects.equals(withChildren, demand.withChildren) &&
                Objects.equals(married, demand.married);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Demand demand = (Demand) super.clone();
        if (demand.client != null){
            demand.client = (Client) demand.client.clone();
        }

        if (demand.agent != null) {
            demand.agent = (Agent) demand.agent.clone();
        }

        if (demand.address != null) {
            demand.address = (Address) demand.address.clone();
        }

        return demand;
    }

    @Override
    public int hashCode() {
        return Objects.hash(client, agent, dealType, minPrice, maxPrice, active, publishedDate, polygon, circleCenter, circleRadius, address, withAnimals, withChildren, married);
    }

    @Override
    public String toString() {
        return "Demand{" +
                "dealType=" + dealType +
                '}';
    }
}
