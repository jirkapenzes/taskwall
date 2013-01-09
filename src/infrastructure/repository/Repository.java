package infrastructure.repository;

import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;
import infrastructure.entity.Entity;

import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 7.12.12
 * Time: 22:44
 */
public abstract class Repository<E extends Entity> {

    private ObjectContainer objectContainer;

    public Repository(ObjectContainer objectContainer) {
        this.objectContainer = objectContainer;
    }

    public void store(E entity) {
        objectContainer.store(entity);
    }

    public void delete(E entity) {
        objectContainer.delete(entity);
    }

    private E findById(final int id) throws Exception {
        List<E> result = search(new Predicate<E>() {
            @Override
            public boolean match(E e) {
                return e.getId() == id;
            }
        });

        if (result.isEmpty())
            throw new Exception("Entity not found");

        return result.get(0);
    }

    public abstract List<E> getAll();

    public void commit() {
        objectContainer.commit();
    }

    protected List<E> search(Predicate<E> predicate) {
        return objectContainer.query(predicate);
    }

    public ObjectContainer getObjectContainer() {
        return objectContainer;
    }
}
