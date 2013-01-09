package infrastructure.repository;

import com.db4o.ObjectContainer;
import infrastructure.entity.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 20.12.12
 * Time: 10:55
 */
public class ConfigurationRepository extends Repository<Configuration> {

    public ConfigurationRepository(ObjectContainer objectContainer) {
        super(objectContainer);
    }

    @Override
    public List<Configuration> getAll() {
        return new ArrayList<Configuration>(getObjectContainer().query(Configuration.class));
    }
}
