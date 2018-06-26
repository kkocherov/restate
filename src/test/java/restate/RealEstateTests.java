package restate;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

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
        Address address = new Address("Москва", "Продольная", "2", "1");
        Apartment apartment = new Apartment(address);
        apartment.setCoordinate(new Coordinate(65.511475, 57.149278));

        List<RealEstate> restates = app.findRealEstateInsideCircle(new Coordinate(65.511575, 57.142278), 2000.);
        assertNotNull(restates);

        for (RealEstate restate: restates) {
            if (restate instanceof Apartment) {
                if (Helpers.equals(apartment, restate))
                    // we haven't added our apartment yet, so it is error if we find it here
                    fail();
            }
        }

        app.addRealEstate(apartment);
        restates = app.findRealEstateInsideCircle(new Coordinate(65.511575, 57.142278), 2000.);

        for (RealEstate restate: restates) {
            if (restate instanceof Apartment) {
                if (Helpers.equals(apartment, restate))
                    // got it! test passed
                    return;
            }
        }

        // if we got here, our apartment was not found
        fail();
    }

    @Test
    public void findRestatesInPolygon() {
        Address address = new Address("Москва", "Продольная", "1", "1");
        Apartment apartment = new Apartment(address);
        apartment.setCoordinate(new Coordinate(65.544434, 57.138475));

        List<Coordinate> polygon = new LinkedList<>();
        polygon.add(new Coordinate(65.409851, 57.113131));
        polygon.add(new Coordinate(65.426331, 57.224441));
        polygon.add(new Coordinate(65.737381, 57.212172));
        polygon.add(new Coordinate(65.692749, 57.062764));

        List<RealEstate> restates = app.findRealEstateInsidePolygon(polygon);
        assertNotNull(restates);

        for (RealEstate restate: restates) {
            if (restate instanceof Apartment) {
                if (Helpers.equals(apartment, restate))
                    // we haven't added our apartment yet, so it is error if we find it here
                    fail();
            }
        }

        app.addRealEstate(apartment);
        restates = app.findRealEstateInsidePolygon(polygon);

        for (RealEstate restate: restates) {
            if (restate instanceof Apartment) {
                if (Helpers.equals(apartment, restate))
                    // got it! test passed
                    return;
            }
        }

        // if we got here, our apartment was not found
        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void findRestatesInInvalidPolygon() {
        List<Coordinate> polygon = new LinkedList<>();
        polygon.add(new Coordinate(65.409851, 57.113131));
        app.findRealEstateInsidePolygon(polygon);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findRestatesInAnotherInvalidPolygon() {
        List<Coordinate> polygon = new LinkedList<>();
        polygon.add(new Coordinate(65.409851, 57.113131));
        polygon.add(new Coordinate(65.426331, 57.224441));
        app.findRealEstateInsidePolygon(polygon);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findRestatesInInvalidCircle() {
        app.findRealEstateInsideCircle(new Coordinate(65.409851, 57.113131), -20.);
    }
}
