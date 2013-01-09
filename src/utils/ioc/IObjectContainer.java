package utils.ioc;

import java.util.Map;
import java.util.Set;

/**
 * Author: Jirka Pénzeš
 * Date: 5.12.12
 * Time: 9:48
 */
public interface IObjectContainer<Identifier> {

    <TClass> TClass bind(Identifier identifier, TClass object);

    <TClass> TClass get(Identifier identifier);

    Set<Map.Entry<Identifier, Object>> entrySet();

}
