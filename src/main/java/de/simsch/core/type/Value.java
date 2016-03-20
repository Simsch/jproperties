package de.simsch.core.type;

import java.util.Optional;

/**
 * @author simsch
 */
public interface Value<T> {

    Optional<T> parse();

}
