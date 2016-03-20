package de.simsch.core.type;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * @author simsch
 */
public class DoubleWrapper implements Value<Double> {

    private static final Logger LOG = Logger.getLogger(DoubleWrapper.class.getName());

    private final Optional<String> value;
    private final NumberFormat numberFormat;

    public DoubleWrapper(Optional<String> value, Locale locale) {
        if (locale == null) {
            throw new IllegalArgumentException("Given value for locale was null!");
        }
        this.value = value;
        this.numberFormat = NumberFormat.getNumberInstance(locale);
    }

    @Override
    public Optional<Double> parse() {
        Optional<Double> returnValue = Optional.empty();
        if (value != null && value.isPresent()) {
            try {
                Number number = numberFormat.parse(value.get());
                returnValue = Optional.of(number.doubleValue());
            } catch (ParseException e) {
                LOG.warning("Value -" + value.get() + "- could not be parsed into double value");
            }
        }
        return returnValue;
    }
}
