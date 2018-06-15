package restate;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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
        app.addClient(client);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithoutContact() {
        Client client = new Client();
        app.addClient(client);
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
}
