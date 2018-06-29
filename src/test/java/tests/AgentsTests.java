package tests;

import org.junit.Test;
import restate.Agent;
import restate.Contact;
import restate.RestateApplication;

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
        agent.firstName = "Sarah";
        agent.lastName = "Black";
        agent.commission = .07;
        Set<Contact> contacts = new HashSet<Contact>();
        Contact contact = Helpers.randomContact();
        contacts.add(contact);
        agent.contacts = contacts;
        Agent result = app.addAgent(agent);

        assertEquals(agent, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createAgentWithNegativeCommissionValue() {
        Agent agent = new Agent();
        agent.commission = -0.5;
        app.addAgent(agent);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createAgentWithInvalidCommissionValue() {
        Agent agent = new Agent();
        agent.commission = 1.5;
        app.addAgent(agent);
    }
}
