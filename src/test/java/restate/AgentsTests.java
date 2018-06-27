package restate;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class AgentsTests {
    private RestateApplication app;

    public AgentsTests() {
        this.app = new RestateApplication();
    }

    @Test
    public void createAgentWithData() {
        Agent agent = new Agent();
        agent.setFirstName("Sarah");
        agent.setLastName("Black");
        agent.setCommission(.07);
        Set<Contact> contacts = new HashSet<Contact>();
        Contact contact = Helpers.randomContact();
        contacts.add(contact);
        agent.setContacts(contacts);
        Agent result = app.addAgent(agent);

        assertTrue(Helpers.equals(agent, result));
    }

    @Test(expected = IllegalArgumentException.class)
    public void createAgentWithNegativeCommissionValue() {
        Agent agent = new Agent();
        agent.setCommission(-0.5);
        app.addAgent(agent);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createAgentWithInvalidCommissionValue() {
        Agent agent = new Agent();
        agent.setCommission(1.5);
        app.addAgent(agent);
    }
}
