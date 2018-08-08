package restate;

import model.*;

/**
 * Класс, реализующий бизнес-логику приложения. Используется при автоматизированном тестировании.
 *
 * Реализуйте все методы данного класса в соответствии с требованиями задания.
 * Для этого вам понадобится реализовать интерфейсы, которые распологаются в модуле model
 *
 * Рекомендуется ознакомиться с содержимым тестов {@link tests.UseCases}. Большинство тестов направлены на проверку
 * корректной обработки исключительных ситуаций. Это в свою очередь означает, что реализуемые методы должны
 * выбрасывать исключения определенных типов: {@link IllegalArgumentException} или {@link IllegalStateException}.
 */
public class RealEstateApplication implements IRealEstateApplication {
    public IAddress createAddress(String city, String street, String house, String number) {
        return null;
    }

    public ICoordinate createCoordinate(double latitude, double longitude) {
        return null;
    }

    public IClient createClient(String firstName, String middleName, String lastName, String email, String phone) {
        return null;
    }

    public IAgent createAgent(String firstName, String middleName, String lastName, int dealShare) {
        return null;
    }

    public void deleteClient(IClient client) {

    }

    public void deleteAgent(IAgent agent) {

    }

    public IApartment createApartment(IAddress address, ICoordinate coordinates, int floor, int rooms, Double area) {
        return null;
    }

    public IHouse createHouse(IAddress address, ICoordinate coordinates, int floors, int rooms, Double area) {
        return null;
    }

    public ILand createLand(IAddress address, ICoordinate coordinates, Double area) {
        return null;
    }

    public void deleteRealEstate(IRealEstate realEstate) {

    }

    public ISupply createSupply(IClient client, IAgent agent, IRealEstate realEstate, int price) {
        return null;
    }

    public void deleteSupply(ISupply supply) {

    }

    public IApartmentDemand createApartmentDemand(IClient client, IAgent agent, IAddress address, int minPrice, int maxPrice) {
        return null;
    }

    public IHouseDemand createHouseDemand(IClient client, IAgent agent, IAddress address, int minPrice, int maxPrice) {
        return null;
    }

    public ILandDemand createLandDemand(IClient client, IAgent agent, IAddress address, int minPrice, int maxPrice) {
        return null;
    }

    public void deleteDemand(IDemand demand) {

    }

    public IDeal createDeal(ISupply supply, IDemand demand) {
        return null;
    }

    public int supplyClientPayment(IDeal deal) {
        return 0;
    }

    public int demandClientPayment(IDeal deal) {
        return 0;
    }

    public int supplyAgentReward(IDeal deal) {
        return 0;
    }

    public int demandAgentReward(IDeal deal) {
        return 0;
    }

    public int companyReward(IDeal deal) {
        return 0;
    }
}
