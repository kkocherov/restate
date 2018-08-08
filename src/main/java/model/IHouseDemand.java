package model;

public interface IHouseDemand extends IDemand {
    Integer getMinFloors();
    void setMinFloor(Integer floors);

    Integer getMinRooms();
    void setMinRooms(Integer rooms);

    Double getMinArea();
    void setMinArea(Double area);

    Integer getMaxFloors();
    void setMaxFloor(Integer floors);

    Integer getMaxRooms();
    void setMaxRooms(Integer rooms);

    Double getMaxArea();
    void setMaxArea(Double area);
}
