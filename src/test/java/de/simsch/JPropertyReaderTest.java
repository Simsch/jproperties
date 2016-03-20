package de.simsch;

import de.simsch.util.Constants;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * @author simsch
 */
public class JPropertyReaderTest {

    private JPropertyReader reader;

    @Before
    public void setUp() throws Exception {
        reader = JPropertyReader.create(PropertyFile.createForClasspath("test.properties"));
    }

    @Test
    public void testCreate_ValidParameter() throws Exception {
        JPropertyReader jPropertyReader = JPropertyReader.create(PropertyFile.createForClasspath("test.properties"));
        assertThat(jPropertyReader, notNullValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreate_NullParameter() throws Exception {
        JPropertyReader.create(null);
    }

    @Test
    public void testReadInteger() throws Exception {
        Optional<Integer> value = reader.readInteger(Constants.INTEGER_VALUE);
        assertThat(value, notNullValue());
        assertThat(value.isPresent(), is(true));
        assertThat(value.get(), notNullValue());
        assertThat(value.get().getClass(), equalTo(Integer.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReadInteger_NullValue() throws Exception {
        reader.readInteger(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReadInteger_EmptyValue() throws Exception {
        reader.readInteger("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReadInteger_WhitespaceValue() throws Exception {
        reader.readInteger(" ");
    }

    @Test
    public void testReadDouble() throws Exception {
        Optional<Double> value = reader.readDouble(Constants.DOUBLE_VALUE);
        assertThat(value, notNullValue());
        assertThat(value.isPresent(), is(true));
        assertThat(value.get(), notNullValue());
        assertThat(value.get().getClass(), equalTo(Double.class));
    }

    @Test
    public void testReadDouble_ValidLocale() throws Exception {
        Optional<Double> value = reader.readDouble(Constants.DOUBLE_VALUE, Locale.ENGLISH);
        assertThat(value, notNullValue());
        assertThat(value.isPresent(), is(true));
        assertThat(value.get(), notNullValue());
        assertThat(value.get().getClass(), equalTo(Double.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReadDouble_NullLocale() throws Exception {
        reader.readDouble(Constants.DOUBLE_VALUE, null);
    }

    @Test
    public void testReadLong() throws Exception {
        Optional<Long> value = reader.readLong(Constants.LONG_VALUE);
        assertThat(value, notNullValue());
        assertThat(value.isPresent(), is(true));
        assertThat(value.get(), notNullValue());
        assertThat(value.get().getClass(), equalTo(Long.class));
    }

    @Test
    public void testReadString() throws Exception {
        Optional<String> value = reader.readString(Constants.STRING_VALUE);
        assertThat(value, notNullValue());
        assertThat(value.isPresent(), is(true));
        assertThat(value.get(), notNullValue());
        assertThat(value.get().getClass(), equalTo(String.class));
    }

    @Test
    public void testReadDate() throws Exception {
        Optional<LocalDateTime> value = reader.readDate(Constants.DATE_VALUE);
        assertThat(value, notNullValue());
        assertThat(value.isPresent(), is(true));
        assertThat(value.get(), notNullValue());
        assertThat(value.get().getClass(), equalTo(LocalDateTime.class));
    }
}