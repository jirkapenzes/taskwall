package infrastructure;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import infrastructure.entity.Board;
import infrastructure.entity.User;

/**
 * Author: Jirka Pénzeš
 * Date: 5.12.12
 * Time: 11:37
 */
public class ObjectDatabase {

    private final String DatabaseName = "taskwall.data";
    private ObjectContainer database;

    public ObjectContainer open() {
        if (database != null)
            close();

        EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
        config.common().objectClass(Board.class).cascadeOnUpdate(true);
        config.common().objectClass(User.class).cascadeOnUpdate(true);
        database = Db4oEmbedded.openFile(config, DatabaseName);
        return database;
    }

    public void commit() {
        database.commit();
    }

    public void close() {
        database.close();
    }

    public void commitAndClose() {
        commit();
        close();
    }
}
