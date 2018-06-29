package tests;

import org.junit.Test;
import restate.*;

import java.util.List;

import static org.junit.Assert.*;
import static tests.Helpers.*;

public class SupplyTests {
    private RestateApplication app;

    public SupplyTests() {
        this.app = new RestateApplication();
    }

    @Test
    public void publishSimpleSupply() {
        Supply supply = new Supply();
        Apartment apartment = randomApartment();
        Client client = randomClient();
        Agent agent = randomAgent();

        app.addRealEstate(apartment);
        app.addClient(client);
        app.addAgent(agent);

        supply.restate = apartment;
        supply.dealType = DealType.PURCHASE;
        supply.price = 2 * 1000 * 1000;
        supply.agent = agent;
        supply.client = client;
        Supply published = app.publishSupply(supply);

        assertEquals(published, supply);
    }

    @Test
    public void publishedSupplyMustBeActiveAndHaveADate() {
        Supply supply = new Supply();
        Apartment apartment = randomApartment();
        Client client = randomClient();
        Agent agent = randomAgent();

        apartment.id = 4L;

        app.addRealEstate(apartment);
        app.addClient(client);
        app.addAgent(agent);

        supply.restate = apartment;
        supply.dealType = DealType.RENT;
        supply.price = 15 * 1000;
        supply.agent = agent;
        supply.client = client;
        Supply published = app.publishSupply(supply);

        assertNotNull(published);
        assertTrue(published.active);
        assertNotNull(published.publishedDate);
    }

    @Test(expected = IllegalArgumentException.class)
    public void publishedSupplyMustHaveClient() {
        Supply supply = new Supply();
        Apartment apartment = randomApartment();
        app.addRealEstate(apartment);

        supply.restate = apartment;
        supply.dealType = DealType.RENT;
        supply.price = 15 * 1000;
        app.publishSupply(supply);
    }

    @Test(expected = IllegalArgumentException.class)
    public void publishedSupplyMustHaveRestate() {
        Supply supply = new Supply();
        Client client = randomClient();
        app.addClient(client);

        supply.client = client;
        supply.dealType = DealType.PURCHASE;
        supply.price = 1000 * 1000;
        app.publishSupply(supply);
    }

    @Test(expected = IllegalArgumentException.class)
    public void publishedSupplyMustHavePrice() {
        Supply supply = new Supply();
        Client client = randomClient();
        Apartment apartment = randomApartment();
        app.addRealEstate(apartment);
        app.addClient(client);

        supply.client = client;
        supply.dealType = DealType.PURCHASE;
        supply.restate = apartment;
        app.publishSupply(supply);
    }

    @Test(expected = IllegalArgumentException.class)
    public void publishedSupplyMustHaveType() {
        Supply supply = new Supply();
        Client client = randomClient();
        Apartment apartment = randomApartment();
        app.addRealEstate(apartment);
        app.addClient(client);

        supply.client = client;
        supply.price = 1000 * 1000;
        supply.restate = apartment;
        app.publishSupply(supply);
    }

    @Test(expected = IllegalArgumentException.class)
    public void restateMustExistWhenPublishingSupply() {
        Supply supply = new Supply();
        Client client = randomClient();
        Apartment apartment = randomApartment();
        app.addClient(client);

        supply.client = client;
        supply.dealType = DealType.PURCHASE;
        supply.price = 4 * 1000 * 1000;
        supply.restate = apartment;
        app.publishSupply(supply);
    }

    @Test(expected = IllegalArgumentException.class)
    public void clientMustExistWhenPublishingSupply() {
        Supply supply = new Supply();
        Client client = randomClient();
        Apartment apartment = randomApartment();

        supply.client = client;
        supply.dealType = DealType.PURCHASE;
        supply.price = 9 * 1000 * 1000;
        supply.restate = apartment;

        app.addRealEstate(apartment);
        app.publishSupply(supply);
    }

    @Test(expected = IllegalArgumentException.class)
    public void agentMustExistWhenPublishingSupply() {
        Supply supply = new Supply();
        Client client = randomClient();
        Agent agent = randomAgent();
        Apartment apartment = randomApartment();

        supply.client = client;
        supply.agent = agent;
        supply.dealType = DealType.RENT;
        supply.price = 18 * 1000;
        supply.restate = apartment;

        app.addRealEstate(apartment);
        app.addClient(client);

        app.publishSupply(supply);
    }

    @Test
    public void cancelPublishedSupply() {
        Supply supply = new Supply();
        Client client = randomClient();
        Apartment apartment = randomApartment();
        app.addRealEstate(apartment);
        app.addClient(client);

        supply.client = client;
        supply.dealType = DealType.PURCHASE;
        supply.price = 9 * 1000 * 1000;
        supply.restate = apartment;
        Supply result = app.publishSupply(supply);
        result = app.cancelSupply(result);
        assertFalse(result.active);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cancelUnpublishedSupply() {
        Supply supply = new Supply();
        Client client = randomClient();
        Apartment apartment = randomApartment();
        app.addRealEstate(apartment);
        app.addClient(client);

        supply.client = client;
        supply.dealType = DealType.RENT;
        supply.price = 25 * 1000;
        supply.restate = apartment;
        app.cancelSupply(supply);
    }

    @Test
    public void findSuppliesForClient() {
        Supply supply1 = randomSupply();
        Supply supply2 = randomSupply();
        Supply supply3 = randomSupply();

        app.addAgent(supply1.agent);
        app.addAgent(supply2.agent);
        app.addAgent(supply3.agent);

        app.addClient(supply1.client);
        app.addClient(supply2.client);
        app.addClient(supply3.client);

        app.addRealEstate(supply1.restate);
        app.addRealEstate(supply2.restate);
        app.addRealEstate(supply3.restate);

        List<Supply> supplies = app.suppliesForClient(supply1.client);
        assertNotNull(supplies);

        assertEquals(app.suppliesForClient(supply1.client).size(), 0);
        assertEquals(app.suppliesForClient(supply2.client).size(), 0);
        assertEquals(app.suppliesForClient(supply3.client).size(), 0);

        app.publishSupply(supply1);

        assertEquals(app.suppliesForClient(supply1.client).size(), 1);
        assertEquals(app.suppliesForClient(supply2.client).size(), 0);
        assertEquals(app.suppliesForClient(supply3.client).size(), 0);


        app.publishSupply(supply3);

        assertEquals(app.suppliesForClient(supply1.client).size(), 1);
        assertEquals(app.suppliesForClient(supply2.client).size(), 0);
        assertEquals(app.suppliesForClient(supply3.client).size(), 1);

        supply2.client = supply1.client;
        app.publishSupply(supply2);

        assertEquals(app.suppliesForClient(supply1.client).size(), 2);
        assertEquals(app.suppliesForClient(supply2.client).size(), 2);
        assertEquals(app.suppliesForClient(supply3.client).size(), 1);
    }

    @Test
    public void findSuppliesForAgent() {
        Supply supply1 = randomSupply();
        Supply supply2 = randomSupply();

        app.addAgent(supply1.agent);
        app.addAgent(supply2.agent);

        app.addClient(supply1.client);
        app.addClient(supply2.client);

        app.addRealEstate(supply1.restate);
        app.addRealEstate(supply2.restate);

        List<Supply> supplies = app.suppliesForAgent(supply1.agent);
        assertNotNull(supplies);

        assertEquals(app.suppliesForAgent(supply1.agent).size(), 0);
        assertEquals(app.suppliesForAgent(supply2.agent).size(), 0);

        app.publishSupply(supply1);

        assertEquals(app.suppliesForAgent(supply1.agent).size(), 1);
        assertEquals(app.suppliesForAgent(supply2.agent).size(), 0);

        app.publishSupply(supply2);

        assertEquals(app.suppliesForAgent(supply1.agent).size(), 1);
        assertEquals(app.suppliesForAgent(supply2.agent).size(), 1);
    }
}
