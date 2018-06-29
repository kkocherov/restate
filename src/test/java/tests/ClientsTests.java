package tests;

import org.junit.Test;
import restate.Client;
import restate.Contact;
import restate.ContactType;
import restate.RestateApplication;

import java.util.HashSet;
import java.util.Set;

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
        client.contacts = contacts;
        Client result = app.addClient(client);

        assertEquals(client, result);
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
        client.contacts = contacts;
        app.addClient(client);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithInvalidContactValue() {
        Client client = new Client();
        Set<Contact> contacts = new HashSet<>();
        Contact contact = new Contact(ContactType.EMAIL, null);
        contacts.add(contact);
        client.contacts = contacts;
        app.addClient(client);
    }

    @Test
    public void createValidClientWithData() {
        Client client = new Client();
        client.firstName = "Ivan";
        client.middleName = "Ivanov";
        Set<Contact> contacts = new HashSet<>();
        Contact contact = Helpers.randomContact();
        contacts.add(contact);
        client.contacts = contacts;
        Client result = app.addClient(client);

        assertEquals(client, result);
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

        client.contacts = contacts;
        Client result = app.addClient(client);
        assertEquals(client, result);
    }

    @Test
    public void searchCreatedClient() {
        Client client = new Client();

        client.lastName = "Parapam";
        client.middleName = "Pararam";

        Set<Contact> contacts = new HashSet<>();
        Contact contact = Helpers.randomContact();
        contacts.add(contact);
        client.contacts = contacts;
        app.addClient(client);

        Client result = app.searchClient(contact);
        Client emptyResult = app.searchClient(Helpers.randomContact());

        assertNotNull(result);
        assertNull(emptyResult);
        assertEquals(client, result);
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

        alice.contacts = aliceContacts;
        bob.contacts = bobContacts;

        app.addClient(alice);
        app.addClient(bob);
    }

    @Test
    public void searchClientsTest() {
        Client alice = new Client();
        Client bob = new Client();
        Client charlie = new Client();

        alice.firstName = "Alice";
        bob.firstName = "Bob";
        charlie.firstName = "Charlie";

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

        alice.contacts = aliceContacts;
        bob.contacts = bobContacts;
        charlie.contacts = charlieContacts;

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

        assertEquals(alice.firstName, alice1.firstName);
        assertEquals(alice.firstName, alice2.firstName);
        assertEquals(alice.firstName, alice3.firstName);

        assertEquals(bob.firstName, bob1.firstName);
        assertEquals(bob.firstName, bob2.firstName);

        assertEquals(charlie.firstName, charlie1.firstName);
        assertEquals(charlie.firstName, charlie2.firstName);
    }
}
