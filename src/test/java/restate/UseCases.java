package restate;

import org.junit.Test;

public class UseCases {
    @Test
    public void case1() {
        RestateApplication app = new RestateApplication();

        Client seller = new Client();
        seller = app.addClient(seller);
        Agent agent1 = new Agent();
        agent1 = app.addAgent(agent1);

        Supply supply = new Supply();
        supply.setObject(new Apartment(new Address("Москва", "Тверская", "2", "24")));
        supply.setKind(DealKind.PURCHASE);
        supply.setPrice(2 * 1000 * 1000);
        supply.setAgent(agent1);
        supply.setClient(seller);
        app.publishSupply(supply);

        Client buyer = new Client();
        buyer = app.addClient(buyer);
        Agent agent2 = new Agent();
        agent2 = app.addAgent(agent2);

        Demand demand = new Demand();
        demand.setKind(DealKind.PURCHASE);
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
}
