package de.simsch.core;

/**
 * @author simsch
 */
public class PropertyFileNotFoundException extends RuntimeException {

    public PropertyFileNotFoundException() {
    }

    public PropertyFileNotFoundException(String message) {
        super(message);
    }

    public PropertyFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PropertyFileNotFoundException(Throwable cause) {
        super(cause);
    }

    public PropertyFileNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
