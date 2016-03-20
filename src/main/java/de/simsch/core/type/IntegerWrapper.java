package de.simsch.core.type;

import java.util.Optional;

/**
 * @author simsch
 */
public class IntegerWrapper implements Value<Integer> {

    private final Optional<String> value;

    public IntegerWrapper(Optional<String> value) {
        this.value = value;
    }

    @Override
    public Optional<Integer> parse() {
        Optional<Integer> returnValue = Optional.empty();
        if (value != null && value.isPresent()) {
            returnValue = Optional.of(Integer.valueOf(value.get()));
        }
        return returnValue;
    }
}
