package restate;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

class Coordinate {
    public double latitude;
    public double longitude;

    public Coordinate(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}

class Address {
    public String city;
    public String street;
    public String house;
    public String apartnemt;

    public Address(String city, String street, String house, String apartnemt) {
        this.city = city;
        this.street = street;
        this.house = house;
        this.apartnemt = apartnemt;
    }
}

abstract class RealEstate {
    protected Address address;
    protected Coordinate coordinate;

    public RealEstate(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}

class House extends RealEstate {
    private Integer totalFloors;
    private Integer builtYear;
    private Integer beds;
    private Integer bathrooms;
    private Integer rooms;
    private Double houseArea; // площадь дома
    private Double landArea; // площадь участка
    private Boolean surveyed; // проведено межевание
    private Boolean hasGas;
    private Boolean hasElectricity;
    private Boolean hasWater;
    private Boolean hasHeating; // отопление

    public House(Address address) {
        super(address);
    }

    public Integer getTotalFloors() {
        return totalFloors;
    }

    public void setTotalFloors(Integer totalFloors) {
        this.totalFloors = totalFloors;
    }

    public Integer getBuiltYear() {
        return builtYear;
    }

    public void setBuiltYear(Integer builtYear) {
        this.builtYear = builtYear;
    }

    public Integer getBeds() {
        return beds;
    }

    public void setBeds(Integer beds) {
        this.beds = beds;
    }

    public Integer getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(Integer bathrooms) {
        this.bathrooms = bathrooms;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public Double getHouseArea() {
        return houseArea;
    }

    public void setHouseArea(Double houseArea) {
        this.houseArea = houseArea;
    }

    public Double getLandArea() {
        return landArea;
    }

    public void setLandArea(Double landArea) {
        this.landArea = landArea;
    }

    public Boolean getSurveyed() {
        return surveyed;
    }

    public void setSurveyed(Boolean surveyed) {
        this.surveyed = surveyed;
    }

    public Boolean getHasGas() {
        return hasGas;
    }

    public void setHasGas(Boolean hasGas) {
        this.hasGas = hasGas;
    }

    public Boolean getHasElectricity() {
        return hasElectricity;
    }

    public void setHasElectricity(Boolean hasElectricity) {
        this.hasElectricity = hasElectricity;
    }

    public Boolean getHasWater() {
        return hasWater;
    }

    public void setHasWater(Boolean hasWater) {
        this.hasWater = hasWater;
    }

    public Boolean getHasHeating() {
        return hasHeating;
    }

    public void setHasHeating(Boolean hasHeating) {
        this.hasHeating = hasHeating;
    }
}

class Apartment extends RealEstate {
    private Integer floor;
    private Integer beds;
    private Integer bathrooms;
    private Integer rooms;
    private Double totalArea;
    private Boolean hasAlarmSystem; // домофон
    private Boolean hasTV;
    private Boolean hasInternet;
    private Boolean newBuilding; // в новостройке

    public Apartment(Address address) {
        super(address);
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getBeds() {
        return beds;
    }

    public void setBeds(Integer beds) {
        this.beds = beds;
    }

    public Integer getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(Integer bathrooms) {
        this.bathrooms = bathrooms;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public Double getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(Double totalArea) {
        this.totalArea = totalArea;
    }

    public Boolean getHasAlarmSystem() {
        return hasAlarmSystem;
    }

    public void setHasAlarmSystem(Boolean hasAlarmSystem) {
        this.hasAlarmSystem = hasAlarmSystem;
    }

    public Boolean getHasTV() {
        return hasTV;
    }

    public void setHasTV(Boolean hasTV) {
        this.hasTV = hasTV;
    }

    public Boolean getHasInternet() {
        return hasInternet;
    }

    public void setHasInternet(Boolean hasInternet) {
        this.hasInternet = hasInternet;
    }

    public Boolean getNewBuilding() {
        return newBuilding;
    }

    public void setNewBuilding(Boolean newBuilding) {
        this.newBuilding = newBuilding;
    }
}

class Land extends RealEstate {
    private double area;
    private Boolean surveyed; // проведено межевание

    public Land(Address address) {
        super(address);
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public Boolean getSurveyed() {
        return surveyed;
    }

    public void setSurveyed(Boolean surveyed) {
        this.surveyed = surveyed;
    }
}

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

class Person {
    protected String firstName;
    protected String middleName;
    protected String lastName;
    protected List<Contact> contacts;

    public Person() {
        this.contacts = new LinkedList<Contact>();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}

class Client extends Person {
    public Client() {
        super();
    }
}

class Agent extends Person {
    public void setCommission(double percent) { }

    public double getCommission() {
        return 0;
    }

    public Agent() {
        super();
    }
}

class RestateApplication {
    List<Client> clients;
    List<RealEstate> restates;

    public RestateApplication() {
        this.clients = new LinkedList<Client>();
        this.restates = new LinkedList<RealEstate>();
    }

    public Client addClient(Client client) {
        if (client == null || client.getContacts().size() == 0)
            throw new IllegalArgumentException();

        for (Contact contact : client.getContacts()) {
            if (contact.type == null || contact.value == null)
                throw new IllegalArgumentException();

            if (this.searchClient(contact) != null)
                throw new IllegalArgumentException();
        }

        clients.add(client);

        return client;
    }

    public Client searchClient(Contact contact) {
        if (contact == null || contact.type == null || contact.value == null)
            throw new IllegalArgumentException();

        for (Client client : this.clients) {
            for (Contact clientContact : client.getContacts()) {
                if (contact.type.equals(clientContact.type) && contact.value.equals(clientContact.value))
                    return client;
            }
        }

        return null;
    }

    public <T extends RealEstate> T addRealEstate(T restate) {
        if (restate.getAddress() == null)
            throw new IllegalArgumentException();

        if (findRealEstateWithAddress(restate.getAddress()) != null) {
            throw new IllegalArgumentException();
        }

        this.restates.add(restate);

        return restate;
    }

    public RealEstate findRealEstateWithAddress(Address address) {
        for (RealEstate restate : this.restates) {
            Address addr = restate.getAddress();
            if (addr.city.equals(address.city)   && addr.street.equals(address.street) &&
                addr.house.equals(address.house) && addr.apartnemt.equals(address.apartnemt)) {
                return restate;
            }
        }

        return null;
    }

    public RealEstate findRealEstateInsideCircle(Coordinate circleCenter, Double circleRadius) {
        return null;
    }

    public RealEstate findRealEstateInsidePolygon(List<Coordinate> polygon) {
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
