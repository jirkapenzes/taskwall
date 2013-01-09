package resources;

import java.awt.*;
import java.net.URL;

/**
 * Author: Jirka Pénzeš
 * Date: 5.12.12
 * Time: 10:12
 */
public class Resources {

    private static Resources resource = new Resources();
    private static final String resourceRootDirectory = "/resources/images/";

    public static Image getImage(String resourceName) {
        URL url = resource.getClass().getResource(resourceRootDirectory + resourceName);
        System.out.println(url);
        return Toolkit.getDefaultToolkit().getImage(url);
    }
}
