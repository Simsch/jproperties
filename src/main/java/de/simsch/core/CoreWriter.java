package de.simsch.core;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author simsch
 */
public class CoreWriter {

    private static final Logger LOG = Logger.getLogger(CoreWriter.class.getName());

    private Properties properties;
    private FileWriter fileWriter;

    private CoreWriter(FileWriter outputStream, CoreReader reader) {
        this.fileWriter = outputStream;
        this.properties = reader.getProperties();
    }

    public static CoreWriter create(Path file) {
        CoreWriter writer;
        try {
            if (!Files.exists(file)) {
                Files.createFile(file);
            }
            CoreReader reader = CoreReader.create(file);
            writer = new CoreWriter(new FileWriter(file.toString()), reader);
        } catch (IOException | NullPointerException e) {
            throw new PropertyFileNotFoundException("Property file could not be found or created.", e);
        }
        return writer;
    }

    public void writeProperty(Map<String, String> keyValues) {
        try {
            for (Map.Entry<String, String> entry : keyValues.entrySet()) {
                properties.setProperty(entry.getKey(), entry.getValue());
            }
            store();
            close();
        } catch (IOException e) {
            LOG.log(Level.WARNING, "Could not write map to file", e);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Given map is null!");
        }
    }

    public void writeProperty(String key, String value) {
        try {
            properties.setProperty(key, value);
            store();
            close();
        } catch (IOException e) {
            LOG.log(Level.WARNING, "Could not write value " + value + " for key " + key, e);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(String.format("Given key (%1s) or value (%2s) is null!", key, value));
        }
    }

    public void deleteProperty(String key) {
        try {
            properties.remove(key);
            store();
            close();
        } catch (IOException e) {
            LOG.log(Level.WARNING, "Could not delete value for key " + key, e);
        }
    }

    private void store() throws IOException {
        properties.store(fileWriter, null);
    }

    private void close() {
        try {
            fileWriter.close();
        } catch (IOException e) {
            LOG.log(Level.INFO, "Could not close file reader. This could cause memory leaks!", e);
        }
    }
}
