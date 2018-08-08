package model;

public interface IDemand {
    IAddress getAddress();
    void setAddress(IAddress address);

    IClient getClient();
    void setClient(IClient client);

    IAgent getAgent();
    void setAgent(IAgent agent);

    Integer getMinPrice();
    void setMinPrice(Integer minPrice);

    Integer getMaxPrice();
    void setMaxPrice(Integer maxPrice);
}
