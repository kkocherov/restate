package tests;

import org.junit.Test;
import restate.*;

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
        apartment.beds = 4;
        apartment.hasTV = false;
        apartment.totalArea = 56.9;

        Apartment added = app.addRealEstate(apartment);
        RealEstate found = app.findRealEstateWithAddress(address);

        assertNotNull(added);
        assertNotNull(found);
        assertEquals(added, apartment);
        assertEquals(found, apartment);
    }

    @Test
    public void createHouse() {
        Address address = new Address("Москва", "Тверская", "23", "4");
        House house = new House(address);
        house.beds = 1;
        house.hasElectricity = false;
        house.rooms = 12;
        house.surveyed = true;
        House added = app.addRealEstate(house);
        RealEstate found = app.findRealEstateWithAddress(address);

        assertNotNull(added);
        assertNotNull(found);
        assertEquals(added, house);
        assertEquals(found, house);
    }

    @Test
    public void createLand() {
        Address address = new Address("Москва", "Тверская", "25", "4");
        Land land = new Land(address);
        land.area = 22.5;
        Land added = app.addRealEstate(land);
        RealEstate found = app.findRealEstateWithAddress(address);

        assertNotNull(added);
        assertNotNull(found);
        assertEquals(added, land);
        assertEquals(found, land);
    }

    @Test
    public void createApartmentWithAllFields() {
        Address address = new Address("Москва", "Тверская", "32", "4");
        Apartment apartment = new Apartment(address);

        apartment.coordinate = new Coordinate(75.2, 99.5);
        apartment.floor = 1;
        apartment.beds = 2;
        apartment.bathrooms = 1;
        apartment.rooms = 2;
        apartment.totalArea = 35.9;
        apartment.hasAlarmSystem = false;
        apartment.hasTV = true;
        apartment.hasInternet = true;
        apartment.newBuilding = true;

        Apartment added = app.addRealEstate(apartment);
        RealEstate found = app.findRealEstateWithAddress(address);

        assertNotNull(added);
        assertNotNull(found);
        assertEquals(added, apartment);
        assertEquals(found, apartment);
    }

    @Test
    public void createHouseWithAllFields() {
        Address address = new Address("Москва", "Тверская", "33", "4");
        House house = new House(address);
        house.coordinate = new Coordinate(72.4, 83.1);

        house.totalFloors = 4;
        house.builtYear = 1994;
        house.beds = 12;
        house.bathrooms = 4;
        house.rooms = 16;
        house.houseArea = 406.;
        house.landArea = 98.;
        house.surveyed = true;
        house.hasGas = false;
        house.hasElectricity = true;
        house.hasWater = true;
        house.hasHeating = true;

        House added = app.addRealEstate(house);
        RealEstate found = app.findRealEstateWithAddress(address);

        assertNotNull(added);
        assertNotNull(found);
        assertEquals(added, house);
        assertEquals(found, house);
    }

    @Test
    public void createLandWithAllFields() {
        Address address = new Address("Москва", "Тверская", "35", "4");
        Land land = new Land(address);
        land.coordinate = new Coordinate(64.6, 87.1);

        land.area = 77.7;
        land.surveyed = false;

        Land added = app.addRealEstate(land);
        RealEstate found = app.findRealEstateWithAddress(address);

        assertNotNull(added);
        assertNotNull(found);
        assertEquals(added, land);
        assertEquals(found, land);
    }

    @Test
    public void findRestatesInCircle() {
        Address address = new Address("Москва", "Продольная", "2", "1");
        Apartment apartment = new Apartment(address);
        apartment.coordinate = new Coordinate(65.511475, 57.149278);

        List<RealEstate> restates = app.findRealEstateInsideCircle(new Coordinate(65.511575, 57.142278), 2000.);
        assertNotNull(restates);

        for (RealEstate restate: restates) {
            if (restate instanceof Apartment) {
                if (apartment.equals(restate))
                    // we haven't added our apartment yet, so it is error if we find it here
                    fail();
            }
        }

        app.addRealEstate(apartment);
        restates = app.findRealEstateInsideCircle(new Coordinate(65.511575, 57.142278), 2000.);

        for (RealEstate restate: restates) {
            if (restate instanceof Apartment) {
                if (apartment.equals(restate))
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
        apartment.coordinate = new Coordinate(65.544434, 57.138475);

        List<Coordinate> polygon = new LinkedList<>();
        polygon.add(new Coordinate(65.409851, 57.113131));
        polygon.add(new Coordinate(65.426331, 57.224441));
        polygon.add(new Coordinate(65.737381, 57.212172));
        polygon.add(new Coordinate(65.692749, 57.062764));

        List<RealEstate> restates = app.findRealEstateInsidePolygon(polygon);
        assertNotNull(restates);

        for (RealEstate restate: restates) {
            if (restate instanceof Apartment) {
                if (apartment.equals(restate))
                    // we haven't added our apartment yet, so it is error if we find it here
                    fail();
            }
        }

        app.addRealEstate(apartment);
        restates = app.findRealEstateInsidePolygon(polygon);

        for (RealEstate restate: restates) {
            if (restate instanceof Apartment) {
                if (apartment.equals(restate))
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
