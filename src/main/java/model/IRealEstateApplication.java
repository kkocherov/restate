package model;

public interface IRealEstateApplication {
    IAddress createAddress(String city, String street, String house, String number);
    ICoordinate createCoordinate(double latitude, double longitude);

    IClient createClient(String firstName, String middleName, String lastName, String email, String phone);
    IAgent createAgent(String firstName, String middleName, String lastName, Integer dealShare);

    IApartment createApartment(IAddress address, ICoordinate coordinates, Integer floor, Integer rooms, Double area);
    IHouse createHouse(IAddress address, ICoordinate coordinates, Integer floors, Integer rooms, Double area);
    ILand createLand(IAddress address, ICoordinate coordinates, Double area);

    ISupply createSupply(IClient client, IAgent agent, IRealEstate realEstate, Integer price);

    IApartmentDemand createApartmentDemand(IClient client, IAgent agent, IAddress address, Integer minPrice, Integer maxPrice);
    IHouseDemand createHouseDemand(IClient client, IAgent agent, IAddress address, Integer minPrice, Integer maxPrice);
    ILandDemand createLandDemand(IClient client, IAgent agent, IAddress address, Integer minPrice, Integer maxPrice);

    IDeal createDeal(ISupply supply, IDemand demand);

    int supplyClientPayment(IDeal deal);
    int demandClientPayment(IDeal deal);
    int supplyAgentReward(IDeal deal);
    int demandAgentReward(IDeal deal);
    int companyReward(IDeal deal);
}
