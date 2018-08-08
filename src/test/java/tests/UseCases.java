package tests;

import org.junit.Test;
import restate.RealEstateApplication;
import model.*;
import static org.junit.Assert.*;

public class UseCases {
    @Test
    public void wholeProcess() {
        IRealEstateApplication app = new RealEstateApplication();

        IClient client1 = app.createClient(null, "Ольга", null, null, "54-58-37");
        IAgent agent1 = app.createAgent("dfsesesef", "Ольга", "asdasd", 50);
        IClient client2 = app.createClient(null, "Ольга", null, null, "54-58-37");
        IAgent agent2 = app.createAgent("dfsesesef", "Ольга", "asdasd", 50);

        assertNotNull(client1);
        assertNotNull(agent1);
        assertNotNull(client2);
        assertNotNull(agent2);

        IAddress apartmentAddress = app.createAddress("Москва", "Тверская", "2", "24");
        assertNotNull(apartmentAddress);

        IApartment apartment = app.createApartment(apartmentAddress, null, 2, 4, 79.9);
        assertNotNull(apartment);

        ISupply supply = app.createSupply(client1, agent1, apartment, 1000000);
        assertNotNull(supply);

        IAddress demandAddress = app.createAddress("Москва", "Тверская", null, null);
        assertNotNull(demandAddress);

        IApartmentDemand demand = app.createApartmentDemand(client2, agent2, demandAddress,0, 0);
        assertNotNull(demand);

        IDeal deal = app.createDeal(supply, demand);

        assertNotNull(deal);
        assertSame(app.supplyClientPayment(deal), 46000);
        assertSame(app.demandClientPayment(deal), 30000);
        assertSame(app.supplyAgentReward(deal), 23000);
        assertSame(app.demandAgentReward(deal), 15000);
        assertSame(app.companyReward(deal), 38000);
    }
}
