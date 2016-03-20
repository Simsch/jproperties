package de.simsch.core.type;

import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.typeCompatibleWith;
import static org.junit.Assert.assertThat;

/**
 * @author simsch
 */
public class StringWrapperTest {

    private static final String EXPECTED_VALUE = "Hello World";

    @Test
    public void testSuperInterface() throws Exception {
        StringWrapper stringWrapper = new StringWrapper(Optional.of(EXPECTED_VALUE));
        assertThat(stringWrapper.getClass(), typeCompatibleWith(Value.class));
    }

    @Test
    public void testParse_Working() throws Exception {
        StringWrapper stringWrapper = new StringWrapper(Optional.of(EXPECTED_VALUE));
        Optional<String> value = stringWrapper.parse();
        assertThat(value, notNullValue());
        assertThat(value.isPresent(), is(true));
        assertThat(value.get(), equalTo(EXPECTED_VALUE));
    }

    @Test
    public void testParse_NullGiven() throws Exception {
        StringWrapper stringWrapper = new StringWrapper(null);
        Optional<String> value = stringWrapper.parse();
        assertThat(value, nullValue());
    }

    @Test
    public void testParse_EmptyValueGiven() throws Exception {
        StringWrapper stringWrapper = new StringWrapper(Optional.empty());
        Optional<String> value = stringWrapper.parse();
        assertThat(value, notNullValue());
        assertThat(value.isPresent(), is(false));
    }

    @Test
    public void testParse_NullValueGiven() throws Exception {
        StringWrapper stringWrapper = new StringWrapper(Optional.ofNullable(null));
        Optional<String> value = stringWrapper.parse();
        assertThat(value, notNullValue());
        assertThat(value.isPresent(), is(false));
    }

    @Test
    public void testParse_EmptyStringValueGiven() throws Exception {
        StringWrapper stringWrapper = new StringWrapper(Optional.of(""));
        Optional<String> value = stringWrapper.parse();
        assertThat(value, notNullValue());
        assertThat(value.isPresent(), is(true));
        assertThat(value.get(), isEmptyOrNullString());
    }
}