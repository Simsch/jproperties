package de.simsch.util;

import java.util.Objects;

/**
 * @author simsch
 */
public class TestSubObject {

    private int property;

    public int getProperty() {
        return property;
    }

    public void setProperty(int property) {
        this.property = property;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestSubObject that = (TestSubObject) o;
        return property == that.property;
    }

    @Override
    public int hashCode() {
        return Objects.hash(property);
    }
}
