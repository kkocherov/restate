package restate;

import org.junit.Test;

import static org.junit.Assert.*;

public class RealEstateTests {
    private RestateApplication app;

    public RealEstateTests() {
        this.app = new RestateApplication();
    }

    @Test
    public void createApartment() {
        Address address = new Address("Москва", "Тверская", "22", "4");
        Apartment apartment = new Apartment(address);
        apartment.setBeds(4);
        apartment.setHasTV(false);
        apartment.setTotalArea(56.9);
        Apartment added = app.addRealEstate(apartment);

        assertNotNull(added);
        assertNull(added.getBathrooms());
        assertNull(added.getNewBuilding());
        assertEquals(apartment.getBeds(), added.getBeds());
        assertEquals(apartment.getTotalArea(), added.getTotalArea());
        assertEquals(apartment.getHasTV(), added.getHasTV());

        assertEquals(apartment.getAddress().city, added.getAddress().city);
        assertEquals(apartment.getAddress().street, added.getAddress().street);
        assertEquals(apartment.getAddress().city, added.getAddress().house);
        assertEquals(apartment.getAddress().apartnemt, added.getAddress().apartnemt);
    }
}
