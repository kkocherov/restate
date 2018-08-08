package model;

public interface IApartmentDemand extends IDemand {
    Integer getMinFloor();
    void setMinFloor(Integer floor);

    Integer getMinRooms();
    void setMinRooms(Integer rooms);

    Double getMinArea();
    void setMinArea(Double area);

    Integer getMaxFloor();
    void setMaxFloor(Integer floor);

    Integer getMaxRooms();
    void setMaxRooms(Integer rooms);

    Double getMaxArea();
    void setMaxArea(Double area);
}
