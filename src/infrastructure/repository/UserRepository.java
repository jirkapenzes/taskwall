package infrastructure.repository;

import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;
import infrastructure.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 5.12.12
 * Time: 11:27
 */
public class UserRepository extends Repository<User> {

    public UserRepository(ObjectContainer objectContainer) {
        super(objectContainer);
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<User>(getObjectContainer().query(User.class));
    }

    public User findByUserName(final String userName) {
        List<User> result = getObjectContainer().query(new Predicate<User>() {
            @Override
            public boolean match(User user) {
                return user.getUserName().equals(userName);
            }
        });

        if (result.isEmpty())
            return null;

        return result.get(0);
    }
}
