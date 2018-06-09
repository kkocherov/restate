package restate;


abstract class RealEstate implements Identifiable<Long> {
    Address address;
    Double latitude;
    Double longitude;
}
