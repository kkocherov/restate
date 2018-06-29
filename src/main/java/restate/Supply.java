package restate;

import java.util.Date;
import java.util.Objects;

public class Supply<T extends RealEstate> implements Cloneable {
    T restate;
    Integer price;
    Client client;
    Agent agent;
    DealType dealType;
    Boolean active;
    Date publishedDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supply<?> supply = (Supply<?>) o;
        return Objects.equals(restate, supply.restate) &&
                Objects.equals(price, supply.price) &&
                Objects.equals(client, supply.client) &&
                Objects.equals(agent, supply.agent) &&
                dealType == supply.dealType &&
                Objects.equals(active, supply.active) &&
                Objects.equals(publishedDate, supply.publishedDate);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Supply supply = (Supply) super.clone();
        supply.client = (Client) supply.client.clone();
        supply.agent = (Agent) supply.agent.clone();
        supply.restate = (RealEstate) supply.restate.clone();
        return supply;
    }

    @Override
    public int hashCode() {
        return Objects.hash(restate, price, client, agent, dealType, active, publishedDate);
    }

    @Override
    public String toString() {
        return "Supply{" +
                "tests=" + restate +
                ", price=" + price +
                ", dealType=" + dealType +
                '}';
    }
}
