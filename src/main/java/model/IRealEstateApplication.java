package model;

public interface IRealEstateApplication {
    IAddress createAddress(String city, String street, String house, String number);
    ICoordinate createCoordinate(double latitude, double longitude);

    IClient createClient(String firstName, String middleName, String lastName, String email, String phone);
    IAgent createAgent(String firstName, String middleName, String lastName, int dealShare);

    void deleteClient(IClient client);
    void deleteAgent(IAgent agent);

    IApartment createApartment(IAddress address, ICoordinate coordinates, int floor, int rooms, Double area);
    IHouse createHouse(IAddress address, ICoordinate coordinates, int floors, int rooms, Double area);
    ILand createLand(IAddress address, ICoordinate coordinates, Double area);
    void deleteRealEstate(IRealEstate realEstate);

    ISupply createSupply(IClient client, IAgent agent, IRealEstate realEstate, int price);
    void deleteSupply(ISupply supply);

    IApartmentDemand createApartmentDemand(IClient client, IAgent agent, IAddress address, int minPrice, int maxPrice);
    IHouseDemand createHouseDemand(IClient client, IAgent agent, IAddress address, int minPrice, int maxPrice);
    ILandDemand createLandDemand(IClient client, IAgent agent, IAddress address, int minPrice, int maxPrice);
    void deleteDemand(IDemand demand);

    IDeal createDeal(ISupply supply, IDemand demand);

    int supplyClientPayment(IDeal deal);
    int demandClientPayment(IDeal deal);
    int supplyAgentReward(IDeal deal);
    int demandAgentReward(IDeal deal);
    int companyReward(IDeal deal);
}
