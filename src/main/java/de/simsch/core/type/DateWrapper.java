package de.simsch.core.type;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author simsch
 */
public class DateWrapper implements Value<LocalDateTime>, Convertible<LocalDateTime> {

    private static final Logger LOG = Logger.getLogger(DoubleWrapper.class.getName());

    private final Optional<String> value;

    public DateWrapper(Optional<String> value) {
        this.value = value;
    }

    @Override
    public Optional<LocalDateTime> parse() {
        Optional<LocalDateTime> returnValue = Optional.empty();
        if (value != null && value.isPresent()) {
            try {
                returnValue = Optional.of(LocalDateTime.parse(value.get(), DateTimeFormatter.ISO_DATE_TIME));
            } catch(DateTimeParseException e) {
                LOG.log(Level.INFO, "Unparsable string " + value.get(), e);
            }
        }
        return returnValue;
    }

    @Override
    public String convertToString(LocalDateTime value) {
        if (value == null) {
            throw new IllegalArgumentException("Given date to convert to String is null!");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        return formatter.format(value);
    }
}
