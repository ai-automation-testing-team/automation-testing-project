package org.endava.automation.testing.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    public static String readFromProperties(String propertyPath, String variableName) {
        String result = "";
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(propertyPath);
            prop.load(input);
            result = prop.getProperty(variableName);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
