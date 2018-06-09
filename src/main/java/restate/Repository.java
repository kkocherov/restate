package restate;

import java.io.Serializable;

public interface Repository<T, PK extends Serializable>{
    T create(T entity);
    T read(PK id);
    T update(T entity);
    void delete(T entity);
}
