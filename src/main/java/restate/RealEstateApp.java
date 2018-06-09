package restate;

import java.io.Serializable;
import java.util.HashMap;

class SimpleInMemoryRepository<T> implements Repository<T, PK> {
    private HashMap<Integer, T> values;

    public T create(T t) {
        values.put(t.hashCode(), t);
        return t;
    }

    public T read(PK id) {
        return values.get(id);
    }

    public T update(T t) {
        values.put(t.id(), t);
        return t;
    }

    public void delete(T t) {
        values.remove(t.id());
    }

    SimpleInMemoryRepository() {
        values = new HashMap<PK, T>();
    }
}


abstract class ClientsRepository implements Repository<Client, Long> {}

abstract class AgentsRepository implements Repository<Agent, Long> {}

abstract class RealtyRepository implements Repository<RealEstate, Long> {}

abstract class SupplyRepository implements Repository<Supply, Long> {}

abstract class DemandRepository implements Repository<Demand, Long> {}

abstract class DealsRepository implements Repository<Deal, Long> {}


public class RealEstateApp {
    ClientsRepository clientsRepository;

    public RealEstateApp(ClientsRepository  clientsRepository) {
        this.clientsRepository = clientsRepository;
    }
}
