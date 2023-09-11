package org.endava.automation.testing.Utils;

import java.io.File;
import java.io.IOException;

public class FileHelper {

    public static String getMavenBaseDirectory() {
        try {
            String currentDir = new File(".").getCanonicalPath();
            if (new File(currentDir + "/pom.xml").exists()) {
                return currentDir;
            } else {
                throw new RuntimeException("Unable to determine Maven base directory");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error determining Maven base directory", e);
        }
    }


    public static File getSourceFileFromTestClass(Class<?> clazz, String baseDir) {
        String relativePath = "/src/test/java/";
        String packagePath = clazz.getPackage().getName().replace('.', '/');
        String fileName = clazz.getSimpleName() + ".java";
        return new File(baseDir + relativePath + packagePath + "/" + fileName);
    }


}
