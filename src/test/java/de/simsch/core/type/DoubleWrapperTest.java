package de.simsch.core.type;

import org.junit.Test;

import java.util.Locale;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.typeCompatibleWith;
import static org.junit.Assert.assertThat;

/**
 * @author simsch
 */
public class DoubleWrapperTest {

    private static final String EXPECTED_VALUE = "100.123";
    private static final Locale TEST_LOCALE    = Locale.ENGLISH;

    @Test
    public void testSuperInterface() throws Exception {
        DoubleWrapper doubleWrapper = new DoubleWrapper(Optional.of(EXPECTED_VALUE), TEST_LOCALE);
        assertThat(doubleWrapper.getClass(), typeCompatibleWith(Value.class));
    }

    @Test
    public void testParse_Working() throws Exception {
        DoubleWrapper doubleWrapper = new DoubleWrapper(Optional.of(EXPECTED_VALUE), TEST_LOCALE);
        Optional<Double> value = doubleWrapper.parse();
        assertThat(value, notNullValue());
        assertThat(value.isPresent(), is(true));
        assertThat(value.get(), equalTo(Double.valueOf(EXPECTED_VALUE)));
    }

    @Test
    public void testParse_NullGiven() throws Exception {
        DoubleWrapper doubleWrapper = new DoubleWrapper(null, TEST_LOCALE);
        Optional<Double> value = doubleWrapper.parse();
        assertThat(value, equalTo(Optional.empty()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParse_NullLocaleGiven() throws Exception {
        new DoubleWrapper(Optional.of(EXPECTED_VALUE), null);
    }

    @Test
    public void testParse_EmptyValueGiven() throws Exception {
        DoubleWrapper doubleWrapper = new DoubleWrapper(Optional.empty(), TEST_LOCALE);
        Optional<Double> value = doubleWrapper.parse();
        assertThat(value, notNullValue());
        assertThat(value.isPresent(), is(false));
    }

    @Test
    public void testParse_NullValueGiven() throws Exception {
        DoubleWrapper doubleWrapper = new DoubleWrapper(Optional.ofNullable(null), TEST_LOCALE);
        Optional<Double> value = doubleWrapper.parse();
        assertThat(value, notNullValue());
        assertThat(value.isPresent(), is(false));
    }

}