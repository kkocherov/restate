package restate;

import java.util.Objects;

public class Address implements Cloneable {
    public String city;
    public String street;
    public String house;
    public String apartment;

    public Address(String city, String street, String house, String apartment) {
        this.city = city;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
    }

    public Address(String city, String street, String house) {
        this(city, street, house, null);
    }

    public Address(String city, String street) {
        this(city, street, null, null);
    }

    public Address(String city) {
        this(city, null, null, null);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(city, address.city) &&
                Objects.equals(street, address.street) &&
                Objects.equals(house, address.house) &&
                Objects.equals(apartment, address.apartment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, house, apartment);
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", house='" + house + '\'' +
                ", apartment='" + apartment + '\'' +
                '}';
    }
}
