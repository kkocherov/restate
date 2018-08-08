package model;

public interface IApartment extends IRealEstate {
    Integer getFloor();
    void setFloor(Integer floor);

    Integer getRooms();
    void setRooms(Integer rooms);

    Double getArea();
    void setArea(Double area);
}
