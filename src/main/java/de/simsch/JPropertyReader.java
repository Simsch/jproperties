package de.simsch;

import de.simsch.core.CoreReader;
import de.simsch.core.type.*;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;

/**
 * @author simsch
 */
public class JPropertyReader {

    private final CoreReader coreReader;

    private JPropertyReader(PropertyFile propertyFile) {
        this.coreReader = CoreReader.create(propertyFile.getPath());
    }

    public static JPropertyReader create(PropertyFile propertyFile) {
        if (propertyFile == null) {
            throw new IllegalArgumentException("Given propertyFile %s is invalid!");
        }
        return new JPropertyReader(propertyFile);
    }

    public Optional<Integer> readInteger(String key) {
        return new IntegerWrapper(read(key)).parse();
    }

    public Optional<Double> readDouble(String key, Locale locale) {
        return new DoubleWrapper(read(key), locale).parse();
    }

    public Optional<Double> readDouble(String key) {
        return readDouble(key, Locale.getDefault());
    }

    public Optional<Long> readLong(String key) {
        return new LongWrapper(read(key)).parse();
    }

    public Optional<String> readString(String key) {
        return new StringWrapper(read(key)).parse();
    }

    public Optional<LocalDateTime> readDate(String key) {
        return new DateWrapper(read(key)).parse();
    }

    private Optional<String> read(String key) {
        return coreReader.getValue(key);
    }
}
