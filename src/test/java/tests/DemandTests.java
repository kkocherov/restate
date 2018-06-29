package tests;

import org.junit.Test;
import restate.*;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static tests.Helpers.*;

public class DemandTests {
    private RestateApplication app;

    public DemandTests() {
        this.app = new RestateApplication();
    }

    @Test(expected = IllegalArgumentException.class)
    public void publishDemandWithoutClient() {
        ApartmentDemand demand = new ApartmentDemand();
        demand.agent = randomAgent();
        demand.dealType = DealType.PURCHASE;
        app.publishDemand(demand);
    }

    @Test(expected = IllegalArgumentException.class)
    public void publishDemandWithoutDealType() {
        ApartmentDemand demand = new ApartmentDemand();
        demand.client = randomClient();
        demand.agent = randomAgent();
        app.publishDemand(demand);
    }

    @Test
    public void publishSimpleDemand() {
        ApartmentDemand demand = randomApartmentDemand();
        demand.minFloor = 4;
        demand.address = new Address("Питер", "Барнаульская");
        app.addClient(demand.client);
        app.addAgent(demand.agent);
        Demand published = app.publishDemand(demand);
        assertNotNull(published.publishedDate);
        assertTrue(published.active);
    }

    @Test(expected = IllegalArgumentException.class)
    public void clientMustExistWhenPublishingDemand() {
        ApartmentDemand demand = new ApartmentDemand();

        demand.client = randomClient();
        demand.dealType = DealType.PURCHASE;

        app.publishDemand(demand);
    }

    @Test(expected = IllegalArgumentException.class)
    public void agentMustExistWhenPublishingDemand() {
        ApartmentDemand demand = new ApartmentDemand();

        demand.client = randomClient();
        demand.agent = randomAgent();
        demand.dealType = DealType.PURCHASE;

        app.addClient(demand.client);
        app.publishDemand(demand);
    }

    @Test
    public void cancelPublishedDemand() {
        Demand demand = new ApartmentDemand();
        Client client = randomClient();

        app.addClient(client);

        demand.client = client;
        demand.dealType = DealType.PURCHASE;
        Demand published = app.publishDemand(demand);
        published = app.cancelDemand(published);
        assertFalse(published.active);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cancelNonexistentDemand() {
        Demand demand = new ApartmentDemand();
        demand.client = randomClient();
        demand.dealType = DealType.PURCHASE;
        app.cancelDemand(demand);
    }

    @Test
    public void findDemandsForClient() {
        Demand demand1 = randomApartmentDemand();
        Demand demand2 = randomApartmentDemand();
        Demand demand3 = randomApartmentDemand();

        app.addAgent(demand1.agent);
        app.addAgent(demand2.agent);
        app.addAgent(demand3.agent);

        app.addClient(demand1.client);
        app.addClient(demand2.client);
        app.addClient(demand3.client);

        List<Demand> demands = app.demandsForClient(demand1.client);
        assertNotNull(demands);

        assertEquals(app.demandsForClient(demand1.client).size(), 0);
        assertEquals(app.demandsForClient(demand2.client).size(), 0);
        assertEquals(app.demandsForClient(demand3.client).size(), 0);

        app.publishDemand(demand1);

        assertEquals(app.demandsForClient(demand1.client).size(), 1);
        assertEquals(app.demandsForClient(demand2.client).size(), 0);
        assertEquals(app.demandsForClient(demand3.client).size(), 0);

        app.publishDemand(demand3);

        assertEquals(app.demandsForClient(demand1.client).size(), 1);
        assertEquals(app.demandsForClient(demand2.client).size(), 0);
        assertEquals(app.demandsForClient(demand3.client).size(), 1);

        demand2.client = demand1.client;
        app.publishDemand(demand2);

        assertEquals(app.demandsForClient(demand1.client).size(), 2);
        assertEquals(app.demandsForClient(demand2.client).size(), 2);
        assertEquals(app.demandsForClient(demand3.client).size(), 1);
    }

    @Test
    public void findSuppliesForAgent() {
        Demand demand1 = randomApartmentDemand();
        Demand demand2 = randomApartmentDemand();

        app.addAgent(demand1.agent);
        app.addAgent(demand2.agent);

        app.addClient(demand1.client);
        app.addClient(demand2.client);

        List<Demand> demands = app.demandsForAgent(demand1.agent);
        assertNotNull(demands);

        assertEquals(app.demandsForAgent(demand1.agent).size(), 0);
        assertEquals(app.demandsForAgent(demand2.agent).size(), 0);

        app.publishDemand(demand1);

        assertEquals(app.demandsForAgent(demand1.agent).size(), 1);
        assertEquals(app.demandsForAgent(demand2.agent).size(), 0);

        app.publishDemand(demand2);

        assertEquals(app.demandsForAgent(demand1.agent).size(), 1);
        assertEquals(app.demandsForAgent(demand2.agent).size(), 1);
    }
}
