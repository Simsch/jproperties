package de.simsch.core.type;

import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.typeCompatibleWith;
import static org.junit.Assert.assertThat;

/**
 * @author simsch
 */
public class IntegerWrapperTest {

    private static final String EXPECTED_VALUE = String.valueOf(Integer.MAX_VALUE);

    @Test
    public void testSuperInterface() throws Exception {
        IntegerWrapper integerWrapper = new IntegerWrapper(Optional.of(EXPECTED_VALUE));
        assertThat(integerWrapper.getClass(), typeCompatibleWith(Value.class));
    }

    @Test
    public void testParse_Working() throws Exception {
        IntegerWrapper integerWrapper = new IntegerWrapper(Optional.of(EXPECTED_VALUE));
        Optional<Integer> value = integerWrapper.parse();
        assertThat(value, notNullValue());
        assertThat(value.isPresent(), is(true));
        assertThat(value.get(), equalTo(Integer.valueOf(EXPECTED_VALUE)));
    }

    @Test
    public void testParse_NullGiven() throws Exception {
        IntegerWrapper integerWrapper = new IntegerWrapper(null);
        Optional<Integer> value = integerWrapper.parse();
        assertThat(value, equalTo(Optional.empty()));
    }

    @Test
    public void testParse_EmptyValueGiven() throws Exception {
        IntegerWrapper integerWrapper = new IntegerWrapper(Optional.empty());
        Optional<Integer> value = integerWrapper.parse();
        assertThat(value, notNullValue());
        assertThat(value.isPresent(), is(false));
    }

    @Test
    public void testParse_NullValueGiven() throws Exception {
        IntegerWrapper integerWrapper = new IntegerWrapper(Optional.ofNullable(null));
        Optional<Integer> value = integerWrapper.parse();
        assertThat(value, notNullValue());
        assertThat(value.isPresent(), is(false));
    }
}