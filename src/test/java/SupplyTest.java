import org.junit.Test;
import restate.*;

public class SupplyTest {
    @Test
    public void appartmentSupplyTest() {
        Client seller = new Client();
        Agent agent1 = new Agent();
        Supply<Apartment> supply = new Apartment.Supply();
        supply.object = new Apartment(new Address("Россия", "Москва", "Тверская", "2"), 24);
        supply.price = 2 * 1000 * 1000;
        supply.agent = agent1;
        supply.client = seller;

        Client buyer = new Client();
        Agent agent2 = new Agent();
        Apartment.Demand demand = new Apartment.Demand();
        demand.agent = agent2;
        demand.client = buyer;

        Deal deal = new Deal();
        deal.supply = supply;
        deal.demand = demand;
        deal.price = 2 * 1000 * 1000;
    }
}
