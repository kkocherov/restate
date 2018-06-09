package restate;

public abstract class Supply<T extends RealEstate> implements Identifiable<Long> {
    public T object;
    public Integer price;
    public Client client;
    public Agent agent;
}
