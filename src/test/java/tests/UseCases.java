package tests;

import org.junit.Test;
import restate.RealEstateApplication;
import model.*;
import static org.junit.Assert.*;

public class UseCases
{
    @Test(expected = IllegalArgumentException.class)
    public void CreateClientWithoutContacts() {
        IRealEstateApplication app = new RealEstateApplication();
        app.createClient(null, "Ольга", null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateClientWithEmptyContacts()
    {
        IRealEstateApplication app = new RealEstateApplication();
        app.createClient(null, "Ольга", null, "", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateAgentWithoutFirstName()
    {
        IRealEstateApplication app = new RealEstateApplication();
        app.createAgent(null, "agent", "agent", 50);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateAgentWithoutMiddleName()
    {
        IRealEstateApplication app = new RealEstateApplication();
        app.createAgent("agent", null, "agent", 50);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateAgentWithoutLastName()
    {
        IRealEstateApplication app = new RealEstateApplication();
        app.createAgent("agent", "agent", null, 50);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateAgentWithEmptyFirstName()
    {
        IRealEstateApplication app = new RealEstateApplication();
        app.createAgent("", "agent", "agent", 50);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateAgentWithEmptyMiddleName()
    {
        IRealEstateApplication app = new RealEstateApplication();
        app.createAgent("agent", "", "agent", 50);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateAgentWithEmptyLastName()
    {
        IRealEstateApplication app = new RealEstateApplication();
        app.createAgent("agent", "agent", "", 50);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateAgentWithInvalidDealShare()
    {
        IRealEstateApplication app = new RealEstateApplication();
        app.createAgent("agent", "agent", "agent", 200);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateAgentWithNegativeDealShare()
    {
        IRealEstateApplication app = new RealEstateApplication();
        app.createAgent("agent", "agent", "agent", -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateDemandWithoutClient()
    {
        IRealEstateApplication app = new RealEstateApplication();
        IAgent agent1 = app.createAgent("dfsesesef", "Ольга", "asdasd", 50);
        app.createApartmentDemand(null, agent1, null, 0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateDemandWithoutAgent()
    {
        IRealEstateApplication app = new RealEstateApplication();
        IClient client1 = app.createClient(null, "Ольга", null, null, "54-58-37");
        app.createApartmentDemand(client1, null, null, 0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateSupplyWithoutAgent()
    {
        IRealEstateApplication app = new RealEstateApplication();
        IClient client1 = app.createClient(null, "Ольга", null, null, "54-58-37");
        IApartment apartment = app.createApartment(null, null, 2, 4, 79.9);
        app.createSupply(client1, null, apartment, 1000000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateSupplyWithoutClient()
    {
        IRealEstateApplication app = new RealEstateApplication();
        IAgent agent1 = app.createAgent("dfsesesef", "Ольга", "asdasd", 50);
        IApartment apartment = app.createApartment(null, null, 2, 4, 79.9);
        app.createSupply(null, agent1, apartment, 1000000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateSupplyWithoutRealEstate()
    {
        IRealEstateApplication app = new RealEstateApplication();
        IAgent agent1 = app.createAgent("dfsesesef", "Ольга", "asdasd", 50);
        IClient client1 = app.createClient(null, "Ольга", null, null, "54-58-37");
        app.createSupply(client1, agent1, null, 1000000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateDealWithoutDemand()
    {
        IRealEstateApplication app = new RealEstateApplication();

        IClient client1 = app.createClient(null, "Ольга", null, null, "54-58-37");
        IAgent agent1 = app.createAgent("dfsesesef", "Ольга", "asdasd", 50);
        IApartment apartment = app.createApartment(null, null, 2, 4, 79.9);
        ISupply supply = app.createSupply(client1, agent1, apartment, 1000000);

        app.createDeal(supply, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateDealWithoutSupply()
    {
        IRealEstateApplication app = new RealEstateApplication();

        IClient client1 = app.createClient(null, "Ольга", null, null, "54-58-37");
        IAgent agent1 = app.createAgent("dfsesesef", "Ольга", "asdasd", 50);
        IApartment apartment = app.createApartment(null, null, 2, 4, 79.9);
        IDemand demand = app.createApartmentDemand(client1, agent1, null, 0, 100);

        app.createDeal(null, demand);
    }

    @Test(expected = IllegalStateException.class)
    public void SupplyCannotBePartOfMoreThanOneDeal()
    {
        IRealEstateApplication app = new RealEstateApplication();

        IClient client1 = app.createClient(null, "Ольга", null, null, "54-58-37");
        IAgent agent1 = app.createAgent("dfsesesef", "Ольга", "asdasd", 50);
        IClient client2 = app.createClient(null, "Ольга", null, null, "54-58-37");
        IAgent agent2 = app.createAgent("dfsesesef", "Ольга", "asdasd", 50);

        IApartment apartment = app.createApartment(null, null, 2, 4, 79.9);
        ISupply supply = app.createSupply(client1, agent1, apartment, 1000000);

        IApartmentDemand demand1 = app.createApartmentDemand(client2, agent2, null, 0, 0);
        IApartmentDemand demand2 = app.createApartmentDemand(client2, agent2, null, 0, 0);

        app.createDeal(supply, demand1);
        app.createDeal(supply, demand2);
    }

    @Test(expected = IllegalStateException.class)
    public void DemandCannotBePartOfMoreThanOneDeal()
    {
        IRealEstateApplication app = new RealEstateApplication();

        IClient client1 = app.createClient(null, "Ольга", null, null, "54-58-37");
        IAgent agent1 = app.createAgent("dfsesesef", "Ольга", "asdasd", 50);
        IClient client2 = app.createClient(null, "Ольга", null, null, "54-58-37");
        IAgent agent2 = app.createAgent("dfsesesef", "Ольга", "asdasd", 50);

        IApartment apartment = app.createApartment(null, null, 2, 4, 79.9);
        ISupply supply1 = app.createSupply(client1, agent1, apartment, 1000000);
        ISupply supply2 = app.createSupply(client1, agent1, apartment, 1000000);

        IApartmentDemand demand = app.createApartmentDemand(client2, agent2, null, 0, 0);

        app.createDeal(supply1, demand);
        app.createDeal(supply2, demand);
    }

    @Test(expected = IllegalStateException.class)
    public void DealCalculations()
    {
        IRealEstateApplication app = new RealEstateApplication();

        IClient client1 = app.createClient(null, "Ольга", null, null, "54-58-37");
        IAgent agent1 = app.createAgent("dfsesesef", "Ольга", "asdasd", 50);
        IClient client2 = app.createClient(null, "Ольга", null, null, "54-58-37");
        IAgent agent2 = app.createAgent("dfsesesef", "Ольга", "asdasd", 50);

        IApartment apartment = app.createApartment(null, null, 2, 4, 79.9);
        ISupply supply = app.createSupply(client1, agent1, apartment, 1000000);

        IApartmentDemand demand = app.createApartmentDemand(client2, agent2, null, 0, 0);

        IDeal deal = app.createDeal(supply, demand);

        assertEquals(app.supplyClientPayment(deal), 46000);
        assertEquals(app.demandClientPayment(deal), 30000);
        assertEquals(app.supplyAgentReward(deal), 23000);
        assertEquals(app.demandAgentReward(deal), 15000);
        assertEquals(app.companyReward(deal), 38000);
    }

    @Test(expected = IllegalStateException.class)
    public void CannotDeleteClientWithSupply() {
        IRealEstateApplication app = new RealEstateApplication();
        IClient client1 = app.createClient(null, "Ольга", null, null, "54-58-37");
        IAgent agent1 = app.createAgent("dfsesesef", "Ольга", "asdasd", 50);
        IApartment apartment = app.createApartment(null, null, 2, 4, 79.9);
        ISupply supply1 = app.createSupply(client1, agent1, apartment, 1000000);
        app.deleteClient(client1);
    }

    @Test(expected = IllegalStateException.class)
    public void CannotDeleteAgentWithSupply()
    {
        IRealEstateApplication app = new RealEstateApplication();
        IClient client1 = app.createClient(null, "Ольга", null, null, "54-58-37");
        IAgent agent1 = app.createAgent("dfsesesef", "Ольга", "asdasd", 50);
        IApartment apartment = app.createApartment(null, null, 2, 4, 79.9);
        ISupply supply1 = app.createSupply(client1, agent1, apartment, 1000000);
        app.deleteAgent(agent1);
    }

    @Test(expected = IllegalStateException.class)
    public void CannotRealEstateWithSupply()
    {
        IRealEstateApplication app = new RealEstateApplication();
        IClient client1 = app.createClient(null, "Ольга", null, null, "54-58-37");
        IAgent agent1 = app.createAgent("dfsesesef", "Ольга", "asdasd", 50);
        IApartment apartment = app.createApartment(null, null, 2, 4, 79.9);
        ISupply supply1 = app.createSupply(client1, agent1, apartment, 1000000);
        app.deleteRealEstate(apartment);
    }

    @Test(expected = IllegalStateException.class)
    public void CannotDeleteClientWithDemand()
    {
        IRealEstateApplication app = new RealEstateApplication();
        IClient client1 = app.createClient(null, "Ольга", null, null, "54-58-37");
        IAgent agent1 = app.createAgent("dfsesesef", "Ольга", "asdasd", 50);
        IApartmentDemand demand = app.createApartmentDemand(client1, agent1, null, 0, 0);
        app.deleteClient(client1);
    }

    @Test(expected = IllegalStateException.class)
    public void CannotDeleteAgentWithDemand()
    {
        IRealEstateApplication app = new RealEstateApplication();
        IClient client1 = app.createClient(null, "Ольга", null, null, "54-58-37");
        IAgent agent1 = app.createAgent("dfsesesef", "Ольга", "asdasd", 50);
        IApartmentDemand demand = app.createApartmentDemand(client1, agent1, null, 0, 0);
        app.deleteAgent(agent1);
    }

    @Test(expected = IllegalStateException.class)
    public void CannotDeleteSupplyWithDeal()
    {
        IRealEstateApplication app = new RealEstateApplication();

        IClient client1 = app.createClient(null, "Ольга", null, null, "54-58-37");
        IAgent agent1 = app.createAgent("dfsesesef", "Ольга", "asdasd", 50);
        IClient client2 = app.createClient(null, "Ольга", null, null, "54-58-37");
        IAgent agent2 = app.createAgent("dfsesesef", "Ольга", "asdasd", 50);

        IApartment apartment = app.createApartment(null, null, 2, 4, 79.9);
        ISupply supply1 = app.createSupply(client1, agent1, apartment, 1000000);

        IApartmentDemand demand = app.createApartmentDemand(client2, agent2, null, 0, 0);

        app.createDeal(supply1, demand);
        app.deleteSupply(supply1);
    }

    @Test(expected = IllegalStateException.class)
    public void CannotDeleteDemandWithDeal()
    {
        IRealEstateApplication app = new RealEstateApplication();

        IClient client1 = app.createClient(null, "Ольга", null, null, "54-58-37");
        IAgent agent1 = app.createAgent("dfsesesef", "Ольга", "asdasd", 50);
        IClient client2 = app.createClient(null, "Ольга", null, null, "54-58-37");
        IAgent agent2 = app.createAgent("dfsesesef", "Ольга", "asdasd", 50);

        IApartment apartment = app.createApartment(null, null, 2, 4, 79.9);
        ISupply supply1 = app.createSupply(client1, agent1, apartment, 1000000);

        IApartmentDemand demand = app.createApartmentDemand(client2, agent2, null, 0, 0);

        app.createDeal(supply1, demand);
        app.deleteDemand(demand);
    }
}
