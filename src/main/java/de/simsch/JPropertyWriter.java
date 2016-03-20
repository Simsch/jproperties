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

    private final CoreWriter writer;

    private JPropertyWriter(PropertyFile propertyFile) {
        this.writer = CoreWriter.create(propertyFile.getPath());
    }

    public static JPropertyWriter create(PropertyFile propertyFile) {
        if (propertyFile == null) {
            throw new IllegalArgumentException("Given propertyFile is invalid!");
        }
        return new JPropertyWriter(propertyFile);
    }

    public boolean write(String key, String value) {
        checkKey(key);
        return writer.writeProperty(key, value);
    }

    public boolean write(String key, LocalDateTime value) {
        checkKey(key);
        DateWrapper dateWrapper = new DateWrapper(Optional.empty());
        return writer.writeProperty(key, dateWrapper.convertToString(value));
    }

    public <T> boolean write(String key, T value, Class<T> clazz) {
        checkKey(key);
        ObjectWrapper<T> objectWrapper = new ObjectWrapper<>(Optional.empty(), clazz);
        return writer.writeProperty(key, objectWrapper.convertToString(value));
    }

    public boolean write(Map<String, String> keyValues) {
        keyValues.keySet().stream().forEach(this::checkKey);
        return writer.writeProperty(keyValues);
    }

    public boolean delete(String key) {
        checkKey(key);
        return writer.deleteProperty(key);
    }

    private void checkKey(String key) {
        if (key == null || key.trim().isEmpty()) {
            throw new IllegalArgumentException("Key is not allowed to be null!");
        }
    }

    public void close() {
        writer.close();
    }
}
