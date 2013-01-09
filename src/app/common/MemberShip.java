package app.common;

import infrastructure.DatabaseContext;
import infrastructure.entity.User;
import infrastructure.repository.UserRepository;

/**
 * Author: Jirka Pénzeš
 * Date: 11.12.12
 * Time: 23:58
 */
public class MemberShip {

    private DatabaseContext databaseContext;

    public MemberShip(DatabaseContext databaseContext) {
        this.databaseContext = databaseContext;
    }

    private User getAdministratorUser() {
        UserRepository userRepository = databaseContext.getUserRepository();
        User user = userRepository.findByUserName("admin");
        if (user == null) {
            user = new User("admin", "adminadmin");
            userRepository.store(user);
        }
        return user;
    }

    public boolean authorize(String password) {
        return getAdministratorUser().getPassword().equals(password);
    }

    public void setNewPassword(String newPassword) {
        User user = getAdministratorUser();
        user.setPassword(newPassword);
        databaseContext.getUserRepository().store(user);
    }
}
