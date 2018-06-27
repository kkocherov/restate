package restate;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static restate.Helpers.*;

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

        supply.setRealEstate(apartment);
        supply.setType(DealType.PURCHASE);
        supply.setPrice(2 * 1000 * 1000);
        supply.setAgent(agent);
        supply.setClient(client);
        Supply published = app.publishSupply(supply);

        assertTrue(Helpers.equals(published, supply));
    }

    @Test
    public void publishedSupplyMustBeActiveAndHaveADate() {
        Supply supply = new Supply();
        Apartment apartment = randomApartment();
        Client client = randomClient();
        Agent agent = randomAgent();

        app.addRealEstate(apartment);
        app.addClient(client);
        app.addAgent(agent);

        supply.setRealEstate(apartment);
        supply.setType(DealType.RENT);
        supply.setPrice(15 * 1000);
        supply.setAgent(agent);
        supply.setClient(client);
        Supply published = app.publishSupply(supply);

        assertNotNull(published);
        assertTrue(published.getActive());
        assertNotNull(published.getPublishedDate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void publishedSupplyMustHaveClient() {
        Supply supply = new Supply();
        Apartment apartment = randomApartment();
        app.addRealEstate(apartment);

        supply.setRealEstate(apartment);
        supply.setType(DealType.RENT);
        supply.setPrice(15 * 1000);
        app.publishSupply(supply);
    }

    @Test(expected = IllegalArgumentException.class)
    public void publishedSupplyMustHaveRestate() {
        Supply supply = new Supply();
        Client client = randomClient();
        app.addClient(client);

        supply.setClient(client);
        supply.setType(DealType.PURCHASE);
        supply.setPrice(1000 * 1000);
        app.publishSupply(supply);
    }

    @Test(expected = IllegalArgumentException.class)
    public void publishedSupplyMustHavePrice() {
        Supply supply = new Supply();
        Client client = randomClient();
        Apartment apartment = randomApartment();
        app.addRealEstate(apartment);
        app.addClient(client);

        supply.setClient(client);
        supply.setType(DealType.PURCHASE);
        supply.setRealEstate(apartment);
        app.publishSupply(supply);
    }

    @Test(expected = IllegalArgumentException.class)
    public void publishedSupplyMustHaveType() {
        Supply supply = new Supply();
        Client client = randomClient();
        Apartment apartment = randomApartment();
        app.addRealEstate(apartment);
        app.addClient(client);

        supply.setClient(client);
        supply.setPrice(1000 * 1000);
        supply.setRealEstate(apartment);
        app.publishSupply(supply);
    }

    @Test(expected = IllegalArgumentException.class)
    public void restateMustExistWhenPublishingSupply() {
        Supply supply = new Supply();
        Client client = randomClient();
        Apartment apartment = randomApartment();
        app.addClient(client);

        supply.setClient(client);
        supply.setType(DealType.PURCHASE);
        supply.setPrice(4 * 1000 * 1000);
        supply.setRealEstate(apartment);
        app.publishSupply(supply);
    }

    @Test(expected = IllegalArgumentException.class)
    public void clientMustExistWhenPublishingSupply() {
        Supply supply = new Supply();
        Client client = randomClient();
        Apartment apartment = randomApartment();

        supply.setClient(client);
        supply.setType(DealType.PURCHASE);
        supply.setPrice(9 * 1000 * 1000);
        supply.setRealEstate(apartment);

        app.addRealEstate(apartment);
        app.publishSupply(supply);
    }

    @Test(expected = IllegalArgumentException.class)
    public void agentMustExistWhenPublishingSupply() {
        Supply supply = new Supply();
        Client client = randomClient();
        Agent agent = randomAgent();
        Apartment apartment = randomApartment();

        supply.setClient(client);
        supply.setAgent(agent);
        supply.setType(DealType.RENT);
        supply.setPrice(18 * 1000);
        supply.setRealEstate(apartment);

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

        supply.setClient(client);
        supply.setType(DealType.PURCHASE);
        supply.setPrice(9 * 1000 * 1000);
        supply.setRealEstate(apartment);
        Supply result = app.publishSupply(supply);
        result = app.cancelSupply(result);
        assertFalse(result.getActive());
    }

    @Test(expected = IllegalArgumentException.class)
    public void cancelUnpublishedSupply() {
        Supply supply = new Supply();
        Client client = randomClient();
        Apartment apartment = randomApartment();
        app.addRealEstate(apartment);
        app.addClient(client);

        supply.setClient(client);
        supply.setType(DealType.RENT);
        supply.setPrice(25 * 1000);
        supply.setRealEstate(apartment);
        app.cancelSupply(supply);
    }

    @Test
    public void findSuppliesForClient() {
        Supply supply1 = randomSupply();
        Supply supply2 = randomSupply();
        Supply supply3 = randomSupply();

        app.addAgent(supply1.getAgent());
        app.addAgent(supply2.getAgent());
        app.addAgent(supply3.getAgent());

        app.addClient(supply1.getClient());
        app.addClient(supply2.getClient());
        app.addClient(supply3.getClient());

        app.addRealEstate(supply1.getRealEstate());
        app.addRealEstate(supply2.getRealEstate());
        app.addRealEstate(supply3.getRealEstate());

        List<Supply> supplies = app.suppliesForClient(supply1.getClient());
        assertNotNull(supplies);

        assertEquals(app.suppliesForClient(supply1.getClient()).size(), 0);
        assertEquals(app.suppliesForClient(supply2.getClient()).size(), 0);
        assertEquals(app.suppliesForClient(supply3.getClient()).size(), 0);

        app.publishSupply(supply1);

        assertEquals(app.suppliesForClient(supply1.getClient()).size(), 1);
        assertEquals(app.suppliesForClient(supply2.getClient()).size(), 0);
        assertEquals(app.suppliesForClient(supply3.getClient()).size(), 0);


        app.publishSupply(supply3);

        assertEquals(app.suppliesForClient(supply1.getClient()).size(), 1);
        assertEquals(app.suppliesForClient(supply2.getClient()).size(), 0);
        assertEquals(app.suppliesForClient(supply3.getClient()).size(), 1);

        supply2.setClient(supply1.getClient());
        app.publishSupply(supply2);

        assertEquals(app.suppliesForClient(supply1.getClient()).size(), 2);
        assertEquals(app.suppliesForClient(supply2.getClient()).size(), 2);
        assertEquals(app.suppliesForClient(supply3.getClient()).size(), 1);
    }

    @Test
    public void findSuppliesForAgent() {
        Supply supply1 = randomSupply();
        Supply supply2 = randomSupply();

        app.addAgent(supply1.getAgent());
        app.addAgent(supply2.getAgent());

        app.addClient(supply1.getClient());
        app.addClient(supply2.getClient());

        app.addRealEstate(supply1.getRealEstate());
        app.addRealEstate(supply2.getRealEstate());

        List<Supply> supplies = app.suppliesForAgent(supply1.getAgent());
        assertNotNull(supplies);

        assertEquals(app.suppliesForAgent(supply1.getAgent()).size(), 0);
        assertEquals(app.suppliesForAgent(supply2.getAgent()).size(), 0);

        app.publishSupply(supply1);

        assertEquals(app.suppliesForAgent(supply1.getAgent()).size(), 1);
        assertEquals(app.suppliesForAgent(supply2.getAgent()).size(), 0);

        app.publishSupply(supply2);

        assertEquals(app.suppliesForAgent(supply1.getAgent()).size(), 1);
        assertEquals(app.suppliesForAgent(supply2.getAgent()).size(), 1);
    }
}
