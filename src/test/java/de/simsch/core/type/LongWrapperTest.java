package de.simsch.core.type;

import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.typeCompatibleWith;
import static org.junit.Assert.assertThat;

/**
 * @author simsch
 */
public class LongWrapperTest {

    private static final String EXPECTED_VALUE = String.valueOf(Long.MAX_VALUE);

    @Test
    public void testSuperInterface() throws Exception {
        LongWrapper longWrapper = new LongWrapper(Optional.of(EXPECTED_VALUE));
        assertThat(longWrapper.getClass(), typeCompatibleWith(Value.class));
    }

    @Test
    public void testParse_Working() throws Exception {
        LongWrapper longWrapper = new LongWrapper(Optional.of(EXPECTED_VALUE));
        Optional<Long> value = longWrapper.parse();
        assertThat(value, notNullValue());
        assertThat(value.isPresent(), is(true));
        assertThat(value.get(), equalTo(Long.valueOf(EXPECTED_VALUE)));
    }

    @Test
    public void testParse_NullGiven() throws Exception {
        LongWrapper longWrapper = new LongWrapper(null);
        Optional<Long> value = longWrapper.parse();
        assertThat(value, equalTo(Optional.empty()));
    }

    @Test
    public void testParse_EmptyValueGiven() throws Exception {
        LongWrapper longWrapper = new LongWrapper(Optional.empty());
        Optional<Long> value = longWrapper.parse();
        assertThat(value, notNullValue());
        assertThat(value.isPresent(), is(false));
    }

    @Test
    public void testParse_NullValueGiven() throws Exception {
        LongWrapper longWrapper = new LongWrapper(Optional.ofNullable(null));
        Optional<Long> value = longWrapper.parse();
        assertThat(value, notNullValue());
        assertThat(value.isPresent(), is(false));
    }
}