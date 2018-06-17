package restate;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ClientsTests {
    private RestateApplication app;

    public ClientsTests() {
        this.app = new RestateApplication();
    }

    @Test
    public void createValidClient() {
        Client client = new Client();
        List<Contact> contacts = new ArrayList<Contact>();
        Contact contact = Helpers.randomContact();
        contacts.add(contact);
        client.setContacts(contacts);
        Client result = app.addClient(client);

        assertNotNull(result);
        assertNotNull(client.getContacts());
        assertEquals(1, client.getContacts().size());
        assertEquals(client.getContacts().get(0).type, contact.type);
        assertEquals(client.getContacts().get(0).value, contact.value);
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
        List<Contact> contacts = new ArrayList<Contact>();
        Contact contact = new Contact(null, "yay");
        contacts.add(contact);
        client.setContacts(contacts);
        app.addClient(client);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithInvalidContactValue() {
        Client client = new Client();
        List<Contact> contacts = new ArrayList<Contact>();
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
        List<Contact> contacts = new ArrayList<Contact>();
        Contact contact = Helpers.randomContact();
        contacts.add(contact);
        client.setContacts(contacts);
        client = app.addClient(client);

        assertNotNull(client);
        assertEquals("Ivan", client.getFirstName());
        assertEquals("Ivanov", client.getMiddleName());
        assertNull(client.getLastName());

        assertNotNull(client.getContacts());
        assertEquals(1, client.getContacts().size());
        assertEquals(client.getContacts().get(0).type, contact.type);
        assertEquals(client.getContacts().get(0).value, contact.value);
    }

    @Test
    public void createClientWithMultipleContacts() {
        Client client = new Client();
        List<Contact> contacts = new ArrayList<Contact>();

        Contact contact1 = Helpers.randomContact();
        Contact contact2 = Helpers.randomContact();
        Contact contact3 = Helpers.randomContact();

        contacts.add(contact1);
        contacts.add(contact2);
        contacts.add(contact3);

        client.setContacts(contacts);
        client = app.addClient(client);

        assertNotNull(client);
        assertNotNull(client.getContacts());
        assertEquals(3, client.getContacts().size());

        assertEquals(client.getContacts().get(0).type, contact1.type);
        assertEquals(client.getContacts().get(0).value, contact1.value);

        assertEquals(client.getContacts().get(1).type, contact2.type);
        assertEquals(client.getContacts().get(1).value, contact2.value);

        assertEquals(client.getContacts().get(2).type, contact3.type);
        assertEquals(client.getContacts().get(2).value, contact3.value);
    }

    @Test
    public void searchCreatedClient() {
        Client client = new Client();

        client.setLastName("Parapam");
        client.setMiddleName("Pararam");

        List<Contact> contacts = new ArrayList<Contact>();
        Contact contact = Helpers.randomContact();
        contacts.add(contact);
        client.setContacts(contacts);
        app.addClient(client);

        Client result = app.searchClient(contact);
        Client emptyResult = app.searchClient(Helpers.randomContact());

        assertNotNull(result);
        assertNull(emptyResult);
        assertNull(client.getFirstName());
        assertEquals(client.getLastName(), result.getLastName());
        assertEquals(client.getMiddleName(), result.getMiddleName());
        assertEquals(client.getContacts().size(), 1);
        assertEquals(client.getContacts().get(0).value, contact.value);
        assertEquals(client.getContacts().get(0).type, contact.type);
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

        List<Contact> aliceContacts = new ArrayList<Contact>();
        List<Contact> bobContacts = new ArrayList<Contact>();

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

        List<Contact> aliceContacts = new ArrayList<Contact>();
        List<Contact> bobContacts = new ArrayList<Contact>();
        List<Contact> charlieContacts = new ArrayList<Contact>();

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
