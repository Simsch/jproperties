package de.simsch;

import de.simsch.core.CoreWriter;
import de.simsch.core.type.DateWrapper;
import de.simsch.core.type.ObjectWrapper;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

/**
 * @author simsch
 */
public class JPropertyWriter {

    private final PropertyFile propertyFile;

    private JPropertyWriter(PropertyFile propertyFile) {
        this.propertyFile = propertyFile;
    }

    public static JPropertyWriter create(PropertyFile propertyFile) {
        if (propertyFile == null) {
            throw new IllegalArgumentException("Given propertyFile is invalid!");
        }
        return new JPropertyWriter(propertyFile);
    }

    public JPropertyWriter write(String key, String value) {
        checkKey(key);
        CoreWriter.create(propertyFile.getPath()).writeProperty(key, value);
        return this;
    }

    public JPropertyWriter write(String key, LocalDateTime value) {
        checkKey(key);
        DateWrapper dateWrapper = new DateWrapper(Optional.empty());
        CoreWriter.create(propertyFile.getPath()).writeProperty(key, dateWrapper.convertToString(value));
        return this;
    }

    public <T> JPropertyWriter write(String key, T value, Class<T> clazz) {
        checkKey(key);
        ObjectWrapper<T> objectWrapper = new ObjectWrapper<>(Optional.empty(), clazz);
        CoreWriter.create(propertyFile.getPath()).writeProperty(key, objectWrapper.convertToString(value));
        return this;
    }

    public JPropertyWriter write(Map<String, String> keyValues) {
        keyValues.keySet().stream().forEach(this::checkKey);
        CoreWriter.create(propertyFile.getPath()).writeProperty(keyValues);
        return this;
    }

    public JPropertyWriter delete(String key) {
        checkKey(key);
        CoreWriter.create(propertyFile.getPath()).deleteProperty(key);
        return this;
    }

    private void checkKey(String key) {
        if (key == null || key.trim().isEmpty()) {
            throw new IllegalArgumentException("Key is not allowed to be null!");
        }
    }
}
