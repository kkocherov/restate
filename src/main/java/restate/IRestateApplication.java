package restate;

import java.util.List;
import java.util.Objects;

enum DealType { RENT, PURCHASE }
enum ContactType { EMAIL, PHONE }
enum DealStage { created, deposit, completed, canceled }

class Contact {
    ContactType type;
    String value;

    public Contact(ContactType type, String value) {
        this.type = type;
        this.value = value;
    }

    public ContactType getType() {
        return type;
    }

    public void setType(ContactType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String toString() {
        return "Contact('"+ this.type +"', '"+ this.value +"')";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return  getType() == contact.getType() &&
                Objects.equals(getValue(), contact.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getValue());
    }
}

interface IPerson {
    String getFirstName();
    void setFirstName(String firstName);

    String getMiddleName();
    void setMiddleName(String middleName);

    String getLastName();
    void setLastName(String lastName);

    List<Contact> getContacts();
    void setContacts(List<Contact> contacts);
}

interface IAgent extends IPerson {
    void setCommission(double percent);
    double getCommission();
}

interface IClient extends IPerson {}

interface ISupply {
    void setObject(RealEstate object);
    RealEstate getObject();

    void setPrice(Integer price);
    Integer getPrice();

    void setClient(IClient client);
    IClient getClient();

    void setAgent(IAgent agent);
    IAgent getAgent();

    void setKind(DealType kind);
    DealType getKind();

    void setActive(boolean active);
    boolean getActive();
}

interface IDemand {
    void setRestateType(Class clazz);
    Class getRestateType();

    void setClient(IClient client);
    IClient getClient();

    void setAgent(IAgent agent);
    IAgent getAgent();

    void setKind(DealType kind);
    DealType getKind();

    void setActive(boolean active);
    boolean getActive();
}

interface IDeal {
    ISupply getSupply();
    void setSupply(ISupply supply);

    IDemand getDemand();
    void setDemand(IDemand supply);

    DealStage getStage();
    void setStage(DealStage stage);

    Integer getDeposit();
    void setDeposit(Integer deposit);

    Integer getPrice();
    void setPrice(Integer price);

    int supplyClientCommission();
    int demandClientCommission();

    int supplyAgentReward();
    int demandAgentReward();
    int agencyReward();
}

interface IRestateApplication {
    IClient addClient(IClient client);
    IAgent addAgent(IAgent agent);

    IDemand publishDemand(IDemand demand);
    IDemand cancelDemand(IDemand demand);

    List<IDemand> demandsForClient(IClient client);
    List<IDemand> demandsForAgent(IAgent agent);
    List<IDemand> demandsForSupply(ISupply supply);

    ISupply publishSupply(ISupply supply);
    ISupply cancelSupply(ISupply supply);
    List<ISupply> suppliesForClient(IClient client);
    List<ISupply> suppliesForAgent(IAgent agent);
    List<ISupply> suppliesForDemand(IDemand demand);

    IDeal publishDeal(IDeal deal);
    IDeal makeDeposit(IDeal deal);
    IDeal completeDeal(IDeal deal);
    IDeal cancelDeal(IDeal deal);
}
