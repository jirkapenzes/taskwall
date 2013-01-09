package app.model;

import infrastructure.entity.Configuration;
import main.MainApplication;

import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 20.12.12
 * Time: 10:58
 */
public class ConfigurationModel {

    private static ConfigurationModel instance;
    private static final Object lock = new Object();

    private Configuration configuration;

    private ConfigurationModel() {
        configuration = load();
    }


    public static ConfigurationModel getInstance() {
        synchronized (lock) {
            if (instance == null)
                instance = new ConfigurationModel();
        }
        return instance;
    }

    public void saveChanges() {
        MainApplication.getDatabase().getConfigurationRepository().store(configuration);
        MainApplication.getDatabase().getConfigurationRepository().commit();
    }


    private Configuration load() {
        List<Configuration> configurations = MainApplication.getDatabase().getConfigurationRepository().getAll();
        if (configurations.isEmpty())
            return new Configuration();
        return configurations.get(0);
    }


    public Configuration getConfiguration() {
        return configuration;
    }
}
