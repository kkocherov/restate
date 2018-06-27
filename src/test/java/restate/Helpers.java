package restate;

import java.util.*;

public class Helpers {
    public static String randomString() {
        Random rand = new Random();
        return Long.toString (rand.nextLong () & Long.MAX_VALUE, 36);
    }

    public static Contact randomContact() {
        Random rand = new Random();

        if (rand.nextBoolean())
            return new Contact(ContactType.PHONE, "9" + Math.abs(rand.nextLong() % 1000000000));
        else
            return new Contact(ContactType.EMAIL, randomString() + "@example.com");
    }

    public static Client randomClient() {
        Client client = new Client();
        Set<Contact> contacts = new HashSet<Contact>();
        contacts.add(randomContact());

        client.setContacts(contacts);
        client.setFirstName(randomString());
        client.setMiddleName(randomString());
        client.setLastName(randomString());

        return client;
    }

    public static Agent randomAgent() {
        Random rand = new Random();
        Agent agent = new Agent();
        Set<Contact> contacts = new HashSet<>();
        contacts.add(randomContact());

        agent.setContacts(contacts);
        agent.setFirstName(randomString());
        agent.setMiddleName(randomString());
        agent.setLastName(randomString());
        agent.setCommission(rand.nextDouble());

        return agent;
    }

    public static Address randomAddress() {
        return new Address(randomString(), randomString(), randomString(), randomString());
    }

    public static Coordinate randomCoordinate() {
        Random rand = new Random();
        return new Coordinate(rand.nextDouble() * 90, (rand.nextDouble() - .5) * 360);
    }

    public static Apartment randomApartment() {
        Apartment apartment = new Apartment(randomAddress());
        apartment.setCoordinate(randomCoordinate());
        return apartment;
    }

    public static Supply randomSupply() {
        Random rand = new Random();
        Supply supply = new Supply();

        supply.setRealEstate(randomApartment());
        supply.setAgent(randomAgent());
        supply.setClient(randomClient());

        if (rand.nextBoolean()) {
            supply.setType(DealType.PURCHASE);
        } else {
            supply.setType(DealType.RENT);
        }

        supply.setPrice(rand.nextInt(5 * 1000 * 1000));

        return supply;
    }

    public static boolean equals(RealEstate a, RealEstate b) {
        return  Objects.equals(a.getAddress(), b.getAddress()) &&
                Objects.equals(a.getCoordinate(), b.getCoordinate());
    }

    public static boolean equals(Apartment a, Apartment b) {
        return  equals((RealEstate) a, (RealEstate) b) &&
                Objects.equals(a.getFloor(), b.getFloor()) &&
                Objects.equals(a.getBeds(), b.getBeds()) &&
                Objects.equals(a.getBathrooms(), b.getBathrooms()) &&
                Objects.equals(a.getRooms(), b.getRooms()) &&
                Objects.equals(a.getTotalArea(), b.getTotalArea()) &&
                Objects.equals(a.getHasAlarmSystem(), b.getHasAlarmSystem()) &&
                Objects.equals(a.getHasTV(), b.getHasTV()) &&
                Objects.equals(a.getHasInternet(), b.getHasInternet()) &&
                Objects.equals(a.getNewBuilding(), b.getNewBuilding());
    }

    public static boolean equals(Land a, Land b) {
        return  equals((RealEstate) a, (RealEstate) b) &&
                Objects.equals(a.getSurveyed(), b.getSurveyed()) &&
                Objects.equals(a.getArea(), b.getArea());
    }

    public static boolean equals(House a, House b) {
        return equals((RealEstate) a, (RealEstate) b) &&
                Objects.equals(a.getTotalFloors(), b.getTotalFloors()) &&
                Objects.equals(a.getBuiltYear(), b.getBuiltYear()) &&
                Objects.equals(a.getBeds(), b.getBeds()) &&
                Objects.equals(a.getBathrooms(), b.getBathrooms()) &&
                Objects.equals(a.getRooms(), b.getRooms()) &&
                Objects.equals(a.getHouseArea(), b.getHouseArea()) &&
                Objects.equals(a.getLandArea(), b.getLandArea()) &&
                Objects.equals(a.getSurveyed(), b.getSurveyed()) &&
                Objects.equals(a.getHasGas(), b.getHasGas()) &&
                Objects.equals(a.getHasElectricity(), b.getHasElectricity()) &&
                Objects.equals(a.getHasWater(), b.getHasWater()) &&
                Objects.equals(a.getHasHeating(), b.getHasHeating());
    }

    public static boolean equals(Person a, Person b) {
        return  Objects.equals(a.getFirstName(), b.getFirstName()) &&
                Objects.equals(a.getMiddleName(), b.getMiddleName()) &&
                Objects.equals(a.getLastName(), b.getLastName()) &&
                Objects.equals(a.getContacts(), b.getContacts());
    }

    public static boolean equals(Agent a, Agent b) {
        return  equals((Person) a, (Person) b) && Objects.equals(a.getCommission(), b.getCommission());
    }

    public static boolean equals(Supply a, Supply b) {
        return  equals(a.getRealEstate(), b.getRealEstate()) &&
                Objects.equals(a.getPrice(), b.getPrice()) &&
                equals(a.getClient(), b.getClient()) &&
                equals(a.getAgent(), b.getAgent()) &&
                a.getType() == b.getType() &&
                Objects.equals(a.getActive(), b.getActive()) &&
                Objects.equals(a.getPublishedDate(), b.getPublishedDate());
    }
}
