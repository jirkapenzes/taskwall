package utils.ioc;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Author: Jirka Pénzeš
 * Date: 5.12.12
 * Time: 9:49
 */
public class ObjectContainer<Identifier> implements IObjectContainer<Identifier> {

    private final Map<Identifier, Object> objectMap;

    public ObjectContainer() {
        objectMap = new HashMap<Identifier, Object>();
    }

    @Override
    public <TClass> TClass bind(Identifier identifier, TClass object) {
        objectMap.put(identifier, object);
        return object;
    }

    @Override
    public <TClass> TClass get(Identifier identifier) {
        return (TClass) objectMap.get(identifier);
    }

    @Override
    public Set<Map.Entry<Identifier, Object>> entrySet() {
        return objectMap.entrySet();
    }
}