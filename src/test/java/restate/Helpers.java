package restate;

import java.util.ArrayList;
import java.util.List;
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
}
