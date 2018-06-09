package restate;

public abstract class Demand<T> implements Identifiable<Long> {
    public Agent agent;
    public Client client;
}
