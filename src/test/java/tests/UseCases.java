package tests;

import org.junit.Test;
import restate.*;

import java.util.List;

import static tests.Helpers.*;

public class UseCases {
    @Test
    public void wholeProcess() {
        RestateApplication app = new RestateApplication();

        Client seller = randomClient();
        seller = app.addClient(seller);
        Agent agent1 = randomAgent();
        agent1 = app.addAgent(agent1);

        Supply<Apartment> supply = new Supply<>();
        Apartment apartment = new Apartment(new Address("Москва", "Тверская", "2", "24"));
        supply.restate = apartment;
        supply.dealType = DealType.PURCHASE;
        supply.price = 2 * 1000 * 1000;
        supply.agent = agent1;
        supply.client = seller;

        app.addRealEstate(apartment);
        app.publishSupply(supply);

        Client buyer = randomClient();
        buyer = app.addClient(buyer);
        Agent agent2 = randomAgent();
        agent2 = app.addAgent(agent2);

        Demand demand = new ApartmentDemand();
        demand.dealType = DealType.PURCHASE;
        demand.agent = agent2;
        demand.client = buyer;
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

        app.addClient(supply.client);
        app.addAgent(supply.agent);
        app.addRealEstate(supply.restate);
        app.publishSupply(supply);

        Demand<Apartment> demand = new ApartmentDemand();
        List<Supply<Apartment>> supplies = app.suppliesForDemand(demand);
    }
}
