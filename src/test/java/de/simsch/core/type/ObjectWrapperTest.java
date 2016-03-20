package de.simsch.core.type;

import com.google.gson.Gson;
import de.simsch.util.TestObject;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.typeCompatibleWith;
import static org.junit.Assert.assertThat;

/**
 * @author Simon Schober <simon.schober@live.com>
 */
public class ObjectWrapperTest {

    private String expectedJson;

    @Before
    public void setUp() throws Exception {
        expectedJson = new Gson().toJson(new TestObject());
    }

    @Test
    public void testSuperInterface_Value() throws Exception {
        ObjectWrapper<Object> objectWrapper = new ObjectWrapper<>(Optional.empty(), Object.class);
        assertThat(objectWrapper.getClass(), typeCompatibleWith(Value.class));
    }

    @Test
    public void testSuperInterface_Convertible() throws Exception {
        ObjectWrapper<Object> objectWrapper = new ObjectWrapper<>(Optional.empty(), Object.class);
        assertThat(objectWrapper.getClass(), typeCompatibleWith(Convertible.class));
    }

    @Test
    public void testParse_Working() throws Exception {
        ObjectWrapper<TestObject> objectWrapper = new ObjectWrapper<>(Optional.of(expectedJson), TestObject.class);
        Optional<TestObject> value = objectWrapper.parse();
        assertThat(value, notNullValue());
        assertThat(value.isPresent(), is(true));
        assertThat(value.get(), equalTo(new TestObject()));
    }

    @Test
    public void testParse_NullGiven() throws Exception {
        ObjectWrapper<TestObject> dateWrapper = new ObjectWrapper<>(null, TestObject.class);
        Optional<TestObject> value = dateWrapper.parse();
        assertThat(value, equalTo(Optional.empty()));
    }

    @Test
    public void testParse_EmptyValueGiven() throws Exception {
        ObjectWrapper<TestObject> dateWrapper = new ObjectWrapper<>(Optional.empty(), TestObject.class);
        Optional<TestObject> value = dateWrapper.parse();
        assertThat(value, equalTo(Optional.empty()));
    }

    @Test
    public void testParse_NullValueGiven() throws Exception {
        ObjectWrapper<TestObject> dateWrapper = new ObjectWrapper<>(Optional.ofNullable(null), TestObject.class);
        Optional<TestObject> value = dateWrapper.parse();
        assertThat(value, equalTo(Optional.empty()));
    }

    @Test
    public void testParse_NullClassGiven() throws Exception {
        ObjectWrapper<TestObject> dateWrapper = new ObjectWrapper<>(Optional.of(expectedJson), null);
        Optional<TestObject> value = dateWrapper.parse();
        assertThat(value, equalTo(Optional.empty()));
    }

    @Test
    public void testConvertToString_Working() throws Exception {
        ObjectWrapper<TestObject> dateWrapper = new ObjectWrapper<>(Optional.empty(), TestObject.class);
        String value = dateWrapper.convertToString(new TestObject());
        assertThat(value, equalTo(expectedJson));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertToString_NullValue() throws Exception {
        ObjectWrapper<TestObject> dateWrapper = new ObjectWrapper<>(Optional.empty(), TestObject.class);
        dateWrapper.convertToString(null);
    }
}