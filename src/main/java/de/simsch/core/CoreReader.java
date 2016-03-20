package de.simsch.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Properties;

/**
 * @author simsch
 */
public class CoreReader {

    private Properties properties;

    private CoreReader(Properties properties) {
        this.properties = properties;
    }

    public static CoreReader create(Path file) {
        Properties properties = new Properties();
        try {
            FileReader fileReader = new FileReader(file.toString());
            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                properties.load(bufferedReader);
            }
        } catch (IOException | NullPointerException e) {
            throw new PropertyFileNotFoundException("Property file could not be found.", e);
        }
        return new CoreReader(properties);
    }

    public Optional<String> getValue(String property) {
        if (property == null || property.trim().isEmpty()) {
            throw new IllegalArgumentException("The parameter 'property' must be set");
        }
        Optional<String> value = Optional.empty();
        String props = properties.getProperty(property);
        if (props != null) {
            value = Optional.of(props);
        }
        return value;
    }

    protected Properties getProperties() {
        return properties;
    }
}
