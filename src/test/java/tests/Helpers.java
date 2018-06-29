package tests;

import restate.*;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

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

        client.contacts = contacts;
        client.firstName = randomString();
        client.middleName = randomString();
        client.lastName = randomString();

        return client;
    }

    public static Agent randomAgent() {
        Random rand = new Random();
        Agent agent = new Agent();
        Set<Contact> contacts = new HashSet<>();
        contacts.add(randomContact());

        agent.contacts = contacts;
        agent.firstName = randomString();
        agent.middleName = randomString();
        agent.lastName = randomString();
        agent.commission = rand.nextDouble();

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
        apartment.coordinate = randomCoordinate();
        return apartment;
    }

    public static Supply randomSupply() {
        Random rand = new Random();
        Supply supply = new Supply();

        supply.restate = randomApartment();
        supply.agent = randomAgent();
        supply.client = randomClient();

        if (rand.nextBoolean()) {
            supply.dealType = DealType.PURCHASE;
        } else {
            supply.dealType = DealType.RENT;
        }

        supply.price = rand.nextInt(5 * 1000 * 1000);

        return supply;
    }

    public static ApartmentDemand randomApartmentDemand() {
        Random rand = new Random();
        ApartmentDemand demand = new ApartmentDemand();

        demand.agent = randomAgent();
        demand.client = randomClient();

        if (rand.nextBoolean()) {
            demand.dealType = DealType.PURCHASE;
        } else {
            demand.dealType = DealType.RENT;
        }

        return demand;
    }
}
