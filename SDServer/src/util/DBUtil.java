/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Bodin Todorovic
 */
public class DBUtil {
    private Properties properties;

    public DBUtil() throws FileNotFoundException, IOException {
        properties = new Properties();
        properties.load(new FileInputStream("db.config"));
    }
    
    public String vratiUrl() {
        return properties.getProperty(Constants.URL);
    }
    
    public String vratiUsername() {
        return properties.getProperty(Constants.USERNAME);
    }
    
    public String vratiPassword() {
        return properties.getProperty(Constants.PASSWORD);
    }
            
}
