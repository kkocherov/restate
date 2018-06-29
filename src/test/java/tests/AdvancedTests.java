package tests;

import org.junit.Test;
import restate.Apartment;
import restate.RestateApplication;

import static tests.Helpers.randomApartment;
import static org.junit.Assert.*;

public class AdvancedTests {
    private RestateApplication app;

    public AdvancedTests() {
        this.app = new RestateApplication();
    }

    @Test
    public void immutableRealEstates() {
        Apartment apartment = randomApartment();
        apartment.hasInternet = false;

        Apartment added = app.addRealEstate(apartment);

        assertEquals(apartment, added);
        assertNotSame(apartment, added);

        apartment.hasInternet = true;

        assertNotEquals(apartment, added);
    }

    @Test
    public void deepImmutableRealEstates() {
        Apartment apartment = randomApartment();
        Apartment added = app.addRealEstate(apartment);
        assertEquals(apartment, added);
        assertNotSame(apartment.address, added.address);
    }
}
