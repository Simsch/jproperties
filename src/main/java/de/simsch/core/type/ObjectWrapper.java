package de.simsch.core.type;

import com.google.gson.Gson;

import java.util.Optional;

/**
 * @author simsch
 */
public class ObjectWrapper<T> implements Value<T>, Convertible<T> {

    private final Optional<String> value;
    private final Class<T> clazz;
    private final Gson gson;

    public ObjectWrapper(Optional<String> value, Class<T> clazz) {
        this.value = value;
        this.gson = new Gson();
        this.clazz = clazz;
    }

    @Override
    public Optional<T> parse() {
        Optional<T> returnValue = Optional.empty();
        if (value != null && value.isPresent() && clazz != null) {
            returnValue = Optional.of(gson.fromJson(value.get(), clazz));
        }
        return returnValue;
    }


    @Override
    public String convertToString(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Given object to convert to String is null!");
        }
        return gson.toJson(value);
    }
}
