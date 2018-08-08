package model;

public interface IRealEstate {
    IAddress getAddress();
    void setAddress(IAddress address);

    ICoordinate getCoordinates();
    void setCoordinates(ICoordinate coordinates);
}