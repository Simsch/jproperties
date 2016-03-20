package de.simsch.core;

import de.simsch.util.Constants;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author simsch
 */
public class CoreReaderIntegrationTest {

    @Test
    public void testCreate() throws Exception {
        CoreReader.create(Constants.getPropertiesFile());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetValue_InvalidKey() throws Exception {
        CoreReader reader = CoreReader.create(Constants.getPropertiesFile());
        reader.getValue(null);
    }

    @Test
    public void testGetValue_ValidKey() throws Exception {
        CoreReader reader = CoreReader.create(Constants.getPropertiesFile());
        Optional<String> value = reader.getValue(Constants.INTEGER_VALUE);
        assertThat(value, notNullValue());
        assertThat(value.isPresent(), is(true));
        assertThat(value.get(), notNullValue());
    }

    @Test
    public void testGetValue_UnknownKey() throws Exception {
        CoreReader reader = CoreReader.create(Constants.getPropertiesFile());
        Optional<String> value = reader.getValue("asdf");
        assertThat(value, notNullValue());
        assertThat(value.isPresent(), is(false));
    }
}
