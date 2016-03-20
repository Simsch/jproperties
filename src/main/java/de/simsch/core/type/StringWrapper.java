package de.simsch.core.type;

import java.util.Optional;

/**
 * @author simsch
 */
public class StringWrapper implements Value<String> {

    private final Optional<String> value;

    public StringWrapper(Optional<String> value) {
        this.value = value;
    }

    @Override
    public Optional<String> parse() {
        return value;
    }
}
