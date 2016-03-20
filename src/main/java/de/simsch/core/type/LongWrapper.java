package de.simsch.core.type;

import java.util.Optional;

/**
 * @author simsch
 */
public class LongWrapper implements Value<Long> {

    private static final String NUMERIC_ONLY_REGEX = "[^\\d]";

    private final Optional<String> value;

    public LongWrapper(Optional<String> value) {
        this.value = value;
    }

    @Override
    public Optional<Long> parse() {
        Optional<Long> returnValue = Optional.empty();
        if (value != null && value.isPresent()) {
            String newValue = value.get().replaceAll(NUMERIC_ONLY_REGEX, "");
            if ( !newValue.isEmpty()) {
                returnValue = Optional.of(Long.valueOf(newValue));
            }
        }
        return returnValue;
    }
}
