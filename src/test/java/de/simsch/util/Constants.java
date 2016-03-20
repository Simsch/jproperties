package de.simsch.util;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author simsch
 */
public interface Constants {

    String INTEGER_VALUE = "integerValue";
    String DOUBLE_VALUE  = "doubleValue";
    String LONG_VALUE    = "longValue";
    String STRING_VALUE  = "stringValue";
    String DATE_VALUE    = "dateValue";

    static Path getPropertiesFile() throws URISyntaxException {
        return Paths.get(ClassLoader.getSystemResource("test.properties").toURI());
    }

    static Path getPropertiesFileForWriting() throws URISyntaxException {
        return Paths.get(ClassLoader.getSystemResource("testWrite.properties").toURI());
    }
}
