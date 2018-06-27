package restate;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import restate.Helpers;

import static org.junit.Assert.*;

public class ClientsTests {
    private RestateApplication app;

    public ClientsTests() {
        this.app = new RestateApplication();
    }

    @Test
    public void createValidClient() {
        Client client = new Client();
        Set<Contact> contacts = new HashSet<Contact>();
        Contact contact = Helpers.randomContact();
        contacts.add(contact);
        client.setContacts(contacts);
        Client result = app.addClient(client);

        assertTrue(Helpers.equals(client, result));
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithoutContact() {
        Client client = new Client();
        app.addClient(client);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createNullClient() {
        app.addClient(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithInvalidContactType() {
        Client client = new Client();
        Set<Contact> contacts = new HashSet<>();
        Contact contact = new Contact(null, "yay");
        contacts.add(contact);
        client.setContacts(contacts);
        app.addClient(client);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithInvalidContactValue() {
        Client client = new Client();
        Set<Contact> contacts = new HashSet<>();
        Contact contact = new Contact(ContactType.EMAIL, null);
        contacts.add(contact);
        client.setContacts(contacts);
        app.addClient(client);
    }

    @Test
    public void createValidClientWithData() {
        Client client = new Client();
        client.setFirstName("Ivan");
        client.setMiddleName("Ivanov");
        Set<Contact> contacts = new HashSet<>();
        Contact contact = Helpers.randomContact();
        contacts.add(contact);
        client.setContacts(contacts);
        Client result = app.addClient(client);

        assertTrue(Helpers.equals(client, result));
    }

    @Test
    public void createClientWithMultipleContacts() {
        Client client = new Client();
        Set<Contact> contacts = new HashSet<>();

        Contact contact1 = Helpers.randomContact();
        Contact contact2 = Helpers.randomContact();
        Contact contact3 = Helpers.randomContact();

        contacts.add(contact1);
        contacts.add(contact2);
        contacts.add(contact3);

        client.setContacts(contacts);
        Client result = app.addClient(client);
        assertTrue(Helpers.equals(client, result));
    }

    @Test
    public void searchCreatedClient() {
        Client client = new Client();

        client.setLastName("Parapam");
        client.setMiddleName("Pararam");

        Set<Contact> contacts = new HashSet<>();
        Contact contact = Helpers.randomContact();
        contacts.add(contact);
        client.setContacts(contacts);
        app.addClient(client);

        Client result = app.searchClient(contact);
        Client emptyResult = app.searchClient(Helpers.randomContact());

        assertNotNull(result);
        assertNull(emptyResult);
        assertTrue(Helpers.equals(client, result));
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidClientSearch() {
        app.searchClient(new Contact(null, "asdadasd"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void anotherInvalidClientSearch() {
        app.searchClient(new Contact(ContactType.EMAIL, null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void searchWIthNullContact() {
        app.searchClient(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void disallowDuplicateClients() {
        Client alice = new Client();
        Client bob = new Client();

        Set<Contact> aliceContacts = new HashSet<>();
        Set<Contact> bobContacts = new HashSet<>();

        Contact aliceContact = new Contact(ContactType.EMAIL, "1234");
        Contact bobContact = new Contact(ContactType.EMAIL, "1234");

        aliceContacts.add(aliceContact);
        bobContacts.add(bobContact);

        alice.setContacts(aliceContacts);
        bob.setContacts(bobContacts);

        app.addClient(alice);
        app.addClient(bob);
    }

    @Test
    public void searchClientsTest() {
        Client alice = new Client();
        Client bob = new Client();
        Client charlie = new Client();

        alice.setFirstName("Alice");
        bob.setFirstName("Bob");
        charlie.setFirstName("Charlie");

        Set<Contact> aliceContacts = new HashSet<>();
        Set<Contact> bobContacts = new HashSet<>();
        Set<Contact> charlieContacts = new HashSet<>();

        Contact alicePhone = new Contact(ContactType.PHONE, "95555555555");
        Contact aliceAdditionalPhone = new Contact(ContactType.PHONE, "95555555556");
        Contact aliceEmail = new Contact(ContactType.EMAIL, "alice@example.com");

        Contact bobPhone = new Contact(ContactType.PHONE, "alice@example.com");
        Contact bobEmail = new Contact(ContactType.EMAIL, "95555555556");

        Contact charliePhone = new Contact(ContactType.PHONE, "95555555557");
        Contact charlieEmail = new Contact(ContactType.EMAIL, "charlie@example.com");

        aliceContacts.add(alicePhone);
        aliceContacts.add(aliceAdditionalPhone);
        aliceContacts.add(aliceEmail);

        bobContacts.add(bobPhone);
        bobContacts.add(bobEmail);

        charlieContacts.add(charliePhone);
        charlieContacts.add(charlieEmail);

        alice.setContacts(aliceContacts);
        bob.setContacts(bobContacts);
        charlie.setContacts(charlieContacts);

        alice = app.addClient(alice);
        bob = app.addClient(bob);
        charlie = app.addClient(charlie);

        Client alice1 = app.searchClient(alicePhone);
        Client alice2 = app.searchClient(aliceAdditionalPhone);
        Client alice3 = app.searchClient(new Contact(aliceEmail.type, aliceEmail.value));

        Client bob1 = app.searchClient(bobEmail);
        Client bob2 = app.searchClient(bobPhone);

        Client charlie1 = app.searchClient(charlieEmail);
        Client charlie2 = app.searchClient(charliePhone);

        assertNotNull(alice);
        assertNotNull(bob);
        assertNotNull(charlie);

        assertNotNull(alice1);
        assertNotNull(alice2);
        assertNotNull(alice3);
        assertNotNull(bob1);
        assertNotNull(bob2);
        assertNotNull(charlie1);
        assertNotNull(charlie2);

        assertEquals(alice.getFirstName(), alice1.getFirstName());
        assertEquals(alice.getFirstName(), alice2.getFirstName());
        assertEquals(alice.getFirstName(), alice3.getFirstName());

        assertEquals(bob.getFirstName(), bob1.getFirstName());
        assertEquals(bob.getFirstName(), bob2.getFirstName());

        assertEquals(charlie.getFirstName(), charlie1.getFirstName());
        assertEquals(charlie.getFirstName(), charlie2.getFirstName());
    }
}
