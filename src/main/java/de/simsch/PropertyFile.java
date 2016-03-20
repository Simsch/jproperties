package de.simsch;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * @author simsch
 */
public class PropertyFile {

    private final Path propertyFile;

    public PropertyFile(Path propertyFile) {
        this.propertyFile = propertyFile;
    }

    public static PropertyFile createExternal(Path propertyFile) {
        return new PropertyFile(propertyFile);
    }

    public static PropertyFile createExternal(String propertyFile) {
        return createExternal(Paths.get(propertyFile));
    }

    public static PropertyFile createExternal(File propertyFile) {
        return createExternal(propertyFile.getAbsolutePath());
    }

    public static PropertyFile createForClasspath(String propertyFile) {
        try {
            return createExternal(Paths.get(ClassLoader.getSystemResource(propertyFile).toURI()));
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(String.format("Given property file %s is invalid!", propertyFile));
        }
    }

    public Path getPath() {
        return propertyFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PropertyFile that = (PropertyFile) o;
        return Objects.equals(propertyFile, that.propertyFile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(propertyFile);
    }

    @Override
    public String toString() {
        return "PropertyFile{propertyFile=" + propertyFile + "}";
    }
}
