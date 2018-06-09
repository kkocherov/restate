import org.junit.Test;
import restate.Address;
import restate.Apartment;

public class ApartmentTest {
    @Test
    public void createApartments() {
        Apartment apartment = new Apartment(new Address("Россия", "Москва", "Тверская", "2"), 24);
    }
}
