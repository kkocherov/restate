package model;

public interface IHouse extends IRealEstate {
    Integer getFloors();
    void setFloors(Integer floors);

    Integer getRooms();
    void setRooms(Integer rooms);

    Double getArea();
    void setArea(Double area);
}
