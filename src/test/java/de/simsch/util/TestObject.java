package de.simsch.util;

import java.util.Objects;

/**
 * @author simsch
 */
public class TestObject {

    private TestSubObject subObject;
    private String property;

    public TestSubObject getSubObject() {
        return subObject;
    }

    public void setSubObject(TestSubObject subObject) {
        this.subObject = subObject;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestObject that = (TestObject) o;
        return Objects.equals(subObject, that.subObject) &&
                Objects.equals(property, that.property);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subObject, property);
    }
}
