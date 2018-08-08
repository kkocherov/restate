package restate;

import model.*;

public class RealEstateApplication implements IRealEstateApplication {
    @Override
    public IAddress createAddress(String city, String street, String house, String number) {
        return null;
    }

    @Override
    public ICoordinate createCoordinate(double latitude, double longitude) {
        return null;
    }

    @Override
    public IClient createClient(String firstName, String middleName, String lastName, String email, String phone) {
        return null;
    }

    @Override
    public IAgent createAgent(String firstName, String middleName, String lastName, Integer dealShare) {
        return null;
    }

    @Override
    public IApartment createApartment(IAddress address, ICoordinate coordinates, Integer floor, Integer rooms, Double area) {
        return null;
    }

    @Override
    public IHouse createHouse(IAddress address, ICoordinate coordinates, Integer floors, Integer rooms, Double area) {
        return null;
    }

    @Override
    public ILand createLand(IAddress address, ICoordinate coordinates, Double area) {
        return null;
    }

    @Override
    public ISupply createSupply(IClient client, IAgent agent, IRealEstate realEstate, Integer price) {
        return null;
    }

    @Override
    public IApartmentDemand createApartmentDemand(IClient client, IAgent agent, IAddress address, Integer minPrice, Integer maxPrice) {
        return null;
    }

    @Override
    public IHouseDemand createHouseDemand(IClient client, IAgent agent, IAddress address, Integer minPrice, Integer maxPrice) {
        return null;
    }

    @Override
    public ILandDemand createLandDemand(IClient client, IAgent agent, IAddress address, Integer minPrice, Integer maxPrice) {
        return null;
    }

    @Override
    public IDeal createDeal(ISupply supply, IDemand demand) {
        return null;
    }

    @Override
    public int supplyClientPayment(IDeal deal) {
        return 0;
    }

    @Override
    public int demandClientPayment(IDeal deal) {
        return 0;
    }

    @Override
    public int supplyAgentReward(IDeal deal) {
        return 0;
    }

    @Override
    public int demandAgentReward(IDeal deal) {
        return 0;
    }

    @Override
    public int companyReward(IDeal deal) {
        return 0;
    }
}
