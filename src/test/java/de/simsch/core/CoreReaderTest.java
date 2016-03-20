package de.simsch.core;

import org.junit.Test;

import java.nio.file.Paths;

/**
 * @author simsch
 */
public class CoreReaderTest {

    @Test(expected = PropertyFileNotFoundException.class)
    public void testCreate_NullValue() throws Exception {
        CoreReader.create(null);
    }

    @Test(expected = PropertyFileNotFoundException.class)
    public void testCreate_InvalidValue() throws Exception {
        CoreReader.create(Paths.get("asdf/fdsa.properties"));
    }
}