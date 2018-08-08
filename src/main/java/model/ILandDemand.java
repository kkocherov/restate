package model;

public interface ILandDemand extends IDemand {
    Double getMinArea();
    void setMinArea(Double area);

    Double getMaxArea();
    void setMaxArea(Double area);
}
