package restate;

import org.junit.Test;

import java.util.List;

import static restate.Helpers.randomAgent;
import static restate.Helpers.randomClient;
import static restate.Helpers.randomSupply;

public class UseCases {
    @Test
    public void wholeProcess() {
        RestateApplication app = new RestateApplication();

        Client seller = randomClient();
        seller = app.addClient(seller);
        Agent agent1 = randomAgent();
        agent1 = app.addAgent(agent1);

        Supply supply = new Supply();
        Apartment apartment = new Apartment(new Address("Москва", "Тверская", "2", "24"));
        supply.setRealEstate(apartment);
        supply.setType(DealType.PURCHASE);
        supply.setPrice(2 * 1000 * 1000);
        supply.setAgent(agent1);
        supply.setClient(seller);

        app.addRealEstate(apartment);
        app.publishSupply(supply);

        Client buyer = randomClient();
        buyer = app.addClient(buyer);
        Agent agent2 = randomAgent();
        agent2 = app.addAgent(agent2);

        Demand demand = new Demand();
        demand.setKind(DealType.PURCHASE);
        demand.setAgent(agent2);
        demand.setClient(buyer);
        demand = app.publishDemand(demand);

        Deal deal = new Deal();
        deal.setSupply(supply);
        deal.setDemand(demand);
        deal.setPrice(2 * 1000 * 1000);
        deal.setStage(DealStage.completed);
        deal = app.publishDeal(deal);
        app.completeDeal(deal);
    }

    @Test
    public void findSuppliesForDemand() {
        RestateApplication app = new RestateApplication();

        Supply supply = randomSupply();

        app.addClient(supply.getClient());
        app.addAgent(supply.getAgent());
        app.addRealEstate(supply.getRealEstate());
        app.publishSupply(supply);

        Demand demand =  new Demand();
        demand.setRestateType(Apartment.class);

        List<Supply> supplies = app.suppliesForDemand(demand);
    }
}
