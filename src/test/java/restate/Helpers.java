package restate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Helpers {

    public static Contact randomContact() {
        Random rand = new Random();

        if (rand.nextBoolean())
            return new Contact(ContactType.PHONE, "9" + Math.abs(rand.nextLong() % 1000000000));
        else
            return new Contact(ContactType.EMAIL, Long.toString (rand.nextLong () & Long.MAX_VALUE, 36) + "@example.com");
    }

    public static Client randomClient() {
        Random rand = new Random();
        Client client = new Client();
        List<Contact> contacts = new ArrayList<Contact>();
        Contact contact = randomContact();
        contacts.add(contact);
        client.setContacts(contacts);
        return client;
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
        return  equals((RealEstate) a, (RealEstate) b) &&
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
}
