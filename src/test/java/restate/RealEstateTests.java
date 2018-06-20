package restate;

import org.junit.Test;

import static org.junit.Assert.*;

public class RealEstateTests {
    private RestateApplication app;

    public RealEstateTests() {
        this.app = new RestateApplication();
    }

    @Test(expected = IllegalArgumentException.class)
    public void createApartmentWithNullAddress() {
        Apartment apartment = new Apartment(null);
        app.addRealEstate(apartment);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createDuplicateRestates() {
        Address address1 = new Address("Москва", "Тверская", "90", "4");
        Address address2 = new Address("Москва", "Тверская", "90", "4");
        Apartment apartment1 = new Apartment(address1);
        Apartment apartment2 = new Apartment(address1);
        app.addRealEstate(apartment1);
        app.addRealEstate(apartment2);
    }

    @Test
    public void createApartment() {
        Address address = new Address("Москва", "Тверская", "22", "4");
        Apartment apartment = new Apartment(address);
        apartment.setBeds(4);
        apartment.setHasTV(false);
        apartment.setTotalArea(56.9);

        Apartment added = app.addRealEstate(apartment);
        RealEstate found = app.findRealEstateWithAddress(address);

        assertNotNull(added);
        assertNotNull(found);
        assertTrue(found instanceof Apartment);
        assertTrue(Helpers.equals(added, apartment));
        assertTrue(Helpers.equals((Apartment) found, apartment));
    }

    @Test
    public void createHouse() {
        Address address = new Address("Москва", "Тверская", "23", "4");
        House house = new House(address);
        house.setBeds(1);
        house.setHasElectricity(false);
        house.setRooms(12);
        house.setSurveyed(true);
        House added = app.addRealEstate(house);
        RealEstate found = app.findRealEstateWithAddress(address);

        assertNotNull(added);
        assertNotNull(found);
        assertTrue(found instanceof House);
        assertTrue(Helpers.equals(added, house));
        assertTrue(Helpers.equals((House) found, house));
    }

    @Test
    public void createLand() {
        Address address = new Address("Москва", "Тверская", "25", "4");
        Land land = new Land(address);
        land.setArea(22.5);
        Land added = app.addRealEstate(land);
        RealEstate found = app.findRealEstateWithAddress(address);

        assertNotNull(added);
        assertNotNull(found);
        assertTrue(found instanceof Land);
        assertTrue(Helpers.equals(added, land));
        assertTrue(Helpers.equals((Land) found, land));
    }

    @Test
    public void createApartmentWithAllFields() {
        Address address = new Address("Москва", "Тверская", "32", "4");
        Apartment apartment = new Apartment(address);
        apartment.setCoordinate(new Coordinate(75.2, 99.5));

        apartment.setFloor(1);
        apartment.setBeds(2);
        apartment.setBathrooms(1);
        apartment.setRooms(2);
        apartment.setTotalArea(35.9);
        apartment.setHasAlarmSystem(false);
        apartment.setHasTV(true);
        apartment.setHasInternet(true);
        apartment.setNewBuilding(true);

        Apartment added = app.addRealEstate(apartment);
        RealEstate found = app.findRealEstateWithAddress(address);

        assertNotNull(added);
        assertNotNull(found);
        assertTrue(found instanceof Apartment);
        assertTrue(Helpers.equals(added, apartment));
        assertTrue(Helpers.equals((Apartment) found, apartment));
    }

    @Test
    public void createHouseWithAllFields() {
        Address address = new Address("Москва", "Тверская", "33", "4");
        House house = new House(address);
        house.setCoordinate(new Coordinate(72.4, 83.1));

        house.setTotalFloors(4);
        house.setBuiltYear(1994);
        house.setBeds(12);
        house.setBathrooms(4);
        house.setRooms(16);
        house.setHouseArea(406.);
        house.setLandArea(98.);
        house.setSurveyed(true);
        house.setHasGas(false);
        house.setHasElectricity(true);
        house.setHasWater(true);
        house.setHasHeating(true);

        House added = app.addRealEstate(house);
        RealEstate found = app.findRealEstateWithAddress(address);

        assertNotNull(added);
        assertNotNull(found);
        assertTrue(found instanceof House);
        assertTrue(Helpers.equals(added, house));
        assertTrue(Helpers.equals((House) found, house));
    }

    @Test
    public void createLandWithAllFields() {
        Address address = new Address("Москва", "Тверская", "35", "4");
        Land land = new Land(address);
        land.setCoordinate(new Coordinate(64.6, 87.1));

        land.setArea(77.7);
        land.setSurveyed(false);

        Land added = app.addRealEstate(land);
        RealEstate found = app.findRealEstateWithAddress(address);

        assertNotNull(added);
        assertNotNull(found);
        assertTrue(found instanceof Land);
        assertTrue(Helpers.equals(added, land));
        assertTrue(Helpers.equals((Land) found, land));
    }

    @Test
    public void findRestatesInCircle() {

    }

    @Test
    public void findRestatesInPolygon() {

    }
}
