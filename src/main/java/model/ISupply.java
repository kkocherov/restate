package model;

public interface ISupply {
    IClient getClient();
    void setClient(IClient client);

    IAgent getAgent();
    void setAgent(IAgent agent);

    IRealEstate getRealEstate();
    void setRealEstate(IRealEstate agent);

    int getPrice();
    void setPrice(int price);
}
