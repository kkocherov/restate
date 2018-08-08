package model;

public interface IDeal {
    IDemand getDemand();
    void setDemand(IDemand demand);

    ISupply getSupply();
    void setSupply(ISupply supply);
}
