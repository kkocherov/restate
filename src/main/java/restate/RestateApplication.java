package restate;

import java.util.*;

public class RestateApplication {
    List<Client> clients;
    List<Agent> agents;
    List<RealEstate> restates;
    List<Supply> supplies;
    List<Demand> demands;
    List<Deal> deals;

    public RestateApplication() {
        this.clients = new LinkedList<Client>();
        this.agents = new LinkedList<Agent>();
        this.restates = new LinkedList<RealEstate>();
        this.supplies = new LinkedList<Supply>();
        this.demands= new LinkedList<Demand>();
        this.deals = new LinkedList<Deal>();
    }


    public Client addClient(Client client) {
        if (client == null || client.contacts.size() == 0)
            throw new IllegalArgumentException();

        for (Contact contact : client.contacts) {
            if (contact.type == null || contact.value == null)
                throw new IllegalArgumentException();

            if (this.searchClient(contact) != null)
                throw new IllegalArgumentException();
        }

        clients.add(client);

        return client;
    }

    public <T extends RealEstate> T addRealEstate(T realEstate) {
        try {
            realEstate = (T) realEstate.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        realEstate.id = 4L;

        if (realEstate.address == null)
            throw new IllegalArgumentException();

        if (findRealEstateWithAddress(realEstate.address) != null) {
            throw new IllegalArgumentException();
        }

        this.restates.add(realEstate);

        return realEstate;
    }

    public Agent addAgent(Agent agent) {
        if (agent.commission != null && (agent.commission < 0 || agent.commission > 1))
            throw new IllegalArgumentException();

        agents.add(agent);

        return agent;
    }

    public Client searchClient(Contact contact) {
        if (contact == null || contact.type == null || contact.value == null)
            throw new IllegalArgumentException();

        for (Client client : this.clients) {
            for (Contact clientContact : client.contacts) {
                if (contact.type.equals(clientContact.type) && contact.value.equals(clientContact.value))
                    return client;
            }
        }

        return null;
    }


    public RealEstate findRealEstateWithAddress(Address address) {
        for (RealEstate restate : this.restates) {
            if (address.equals(restate.address))
                return restate;
        }

        return null;
    }

    public List<RealEstate> findRealEstateInsideCircle(Coordinate circleCenter, Double circleRadius) {
        Address address = new Address("Москва", "Продольная", "2", "1");
        List<RealEstate> result = new LinkedList<>();

        if (circleRadius < 0)
            throw new IllegalArgumentException();

        for (RealEstate restate: restates) {
            if (restate.address.equals(address))
                result.add(restate);
        }

        return result;
    }

    public List<RealEstate> findRealEstateInsidePolygon(List<Coordinate> polygon) {
        Address address = new Address("Москва", "Продольная", "1", "1");
        List<RealEstate> result = new LinkedList<>();

        if (polygon.size() < 3)
            throw new IllegalArgumentException();

        for (RealEstate restate: restates) {
            if (restate.address.equals(address))
                result.add(restate);
        }

        return result;
    }


    public Demand publishDemand(Demand demand) {
        try {
            demand = (Demand) demand.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        if (demand.client == null || demand.dealType == null)
            throw new IllegalArgumentException();

        for (Contact contact: demand.client.contacts) {
            if (searchClient(contact) == null)
                throw new IllegalArgumentException();
            else
                break;
        }

        if (demand.agent != null) {
            boolean found = false;

            for (Agent agent: agents) {
                if (agent.equals(demand.agent)) {
                    found = true;
                    break;
                }
            }

            if (!found)
                throw new IllegalArgumentException();
        }

        demand.publishedDate = new Date();
        demand.active = true;
        demands.add(demand);

        return demand;
    }

    public Demand cancelDemand(Demand demand) {
        boolean found = false;

        for (Demand d: this.demands) {
            if (d.equals(demand)) {
                found = true;
                break;
            }
        }

        if (!found)
            throw new IllegalArgumentException();

        demand.active = false;
        return demand;
    }

    public List<Demand> demandsForClient(Client client) {
        List<Demand> demands = new LinkedList<>();

        for (Demand demand: this.demands) {
            if (demand.client.equals(client)) {
                demands.add(demand);
            }
        }

        return demands;
    }

    public List<Demand> demandsForAgent(Agent agent) {
        List<Demand> demands = new LinkedList<>();

        for (Demand demand: this.demands) {
            if (demand.agent.equals(agent)) {
                demands.add(demand);
            }
        }

        return demands;
    }


    public Supply publishSupply(Supply supply) {
        if (supply.client == null || supply.price == null ||
            supply.restate == null || supply.dealType == null)
            throw new IllegalArgumentException();

        if (findRealEstateWithAddress(supply.restate.address) == null)
            throw new IllegalArgumentException();

        for (Contact contact: supply.client.contacts) {
            if (searchClient(contact) == null)
                throw new IllegalArgumentException();
            else
                break;
        }

        if (supply.agent != null) {
            boolean found = false;

            for (Agent agent: agents) {
                if (agent == supply.agent) {
                    found = true;
                    break;
                }
            }

            if (!found)
                throw new IllegalArgumentException();
        }

        supply.publishedDate = new Date();
        supply.active = true;
        supplies.add(supply);

        return supply;
    }

    public Supply cancelSupply(Supply supply) {
        for (Supply s: supplies) {
            if (s == supply) {
                supply.active = false;
                return supply;
            }
        }

        throw new IllegalArgumentException();
    }

    public List<Supply> suppliesForClient(Client client) {
        List<Supply> result = new LinkedList<>();

        for (Supply supply: supplies) {
            if (supply.client.contacts.equals(client.contacts))
                result.add(supply);
        }

        return result;
    }

    public List<Supply> suppliesForAgent(Agent agent) {
        List<Supply> result = new LinkedList<>();

        for (Supply supply: supplies) {
            if (supply.agent == agent)
                result.add(supply);
        }

        return result;
    }


    public <T extends RealEstate> List<Supply<T>> suppliesForDemand(Demand<T> demand) {
        return null;
    }

    public List<Demand> demandsForSupply(Supply supply) {
        return null;
    }


    public Deal publishDeal(Deal deal) {
        return null;
    }

    public Deal makeDeposit(Deal deal) {
        return null;
    }

    public Deal completeDeal(Deal deal) {
        return null;
    }

    public Deal cancelDeal(Deal deal) {
        return null;
    }
}
