package restate;

enum DealState { created, deposit, completed, canceled }

public class Deal<T extends RealEstate> {
    public Supply<T> supply;
    public Demand<T> demand;
    public DealState state;
    public Integer deposit;
    public Integer price;
}
