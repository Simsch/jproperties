package de.simsch.core.type;

/**
 * @author Simon Schober <simon.schober@live.com>
 */
public interface Convertible<T> {

    String convertToString(T value);
}
