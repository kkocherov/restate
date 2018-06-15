package restate;

import java.util.List;

class Supply {
    public void setObject(RealEstate object) {

    }

    public RealEstate getObject() {
        return null;
    }

    public void setPrice(Integer price) {

    }

    public Integer getPrice() {
        return null;
    }

    public void setClient(Client client) {

    }

    public Client getClient() {
        return null;
    }

    public void setAgent(Agent agent) {

    }

    public Agent getAgent() {
        return null;
    }

    public void setKind(DealKind kind) {

    }

    public DealKind getKind() {
        return null;
    }

    public void setActive(boolean active) {

    }

    public boolean getActive() {
        return false;
    }
}

class Demand {
    public void setRestateType(Class clazz) {

    }

    public Class getRestateType() {
        return null;
    }

    public void setClient(Client client) {

    }

    public Client getClient() {
        return null;
    }

    public void setAgent(Agent agent) {

    }

    public Agent getAgent() {
        return null;
    }

    public void setKind(DealKind kind) {

    }

    public DealKind getKind() {
        return null;
    }

    public void setActive(boolean active) {

    }

    public boolean getActive() {
        return false;
    }
}

class Deal {
    public Supply getSupply() {
        return null;
    }

    public void setSupply(Supply supply) {

    }

    public Demand getDemand() {
        return null;
    }

    public void setDemand(Demand supply) {

    }

    public DealStage getStage() {
        return null;
    }

    public void setStage(DealStage stage) {

    }

    public Integer getDeposit() {
        return null;
    }

    public void setDeposit(Integer deposit) {

    }

    public Integer getPrice() {
        return null;
    }

    public void setPrice(Integer price) {

    }

    public int supplyClientCommission() {
        return 0;
    }

    public int demandClientCommission() {
        return 0;
    }

    public int supplyAgentReward() {
        return 0;
    }

    public int demandAgentReward() {
        return 0;
    }

    public int agencyReward() {
        return 0;
    }
}

class Client {
    public String getFirstName() {
        return null;
    }

    public void setFirstName(String firstName) {

    }

    public String getMiddleName() {
        return null;
    }

    public void setMiddleName(String middleName) {

    }

    public String getLastName() {
        return null;
    }

    public void setLastName(String lastName) {

    }

    public List<Contact> getContacts() {
        return null;
    }

    public void setContacts(List<Contact> contacts) {

    }
}

class Agent {
    public String getFirstName() {
        return null;
    }

    public void setFirstName(String firstName) {

    }

    public String getMiddleName() {
        return null;
    }

    public void setMiddleName(String middleName) {

    }

    public String getLastName() {
        return null;
    }

    public void setLastName(String lastName) {

    }

    public List<Contact> getContacts() {
        return null;
    }

    public void setContacts(List<Contact> contacts) {

    }

    public void setCommission(double percent) {

    }

    public double getCommission() {
        return 0;
    }
}

class RestateApplication {
    public Client addClient(Client client) {
        return null;
    }

    public Client findClient(Contact contact) {
        return null;
    }

    public Agent addAgent(Agent agent) {
        return null;
    }

    public Demand publishDemand(Demand demand) {
        return null;
    }

    public Demand cancelDemand(Demand demand) {
        return null;
    }

    public List<Demand> demandsForClient(Client client) {
        return null;
    }

    public List<Demand> demandsForAgent(Agent agent) {
        return null;
    }

    public List<Demand> demandsForSupply(Supply supply) {
        return null;
    }

    public Supply publishSupply(Supply supply) {
        return null;
    }

    public Supply cancelSupply(Supply supply) {
        return null;
    }

    public List<Supply> suppliesForClient(Client client) {
        return null;
    }

    public List<Supply> suppliesForAgent(Agent agent) {
        return null;
    }

    public List<Supply> suppliesForDemand(Demand demand) {
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
