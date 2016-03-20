package de.simsch.core;

import org.junit.Test;

import java.nio.file.Paths;

/**
 * @author simsch
 */
public class CoreWriterTest {

    @Test(expected = PropertyFileNotFoundException.class)
    public void testCreate_NullValue() throws Exception {
        CoreWriter.create(null);
    }

    @Test(expected = PropertyFileNotFoundException.class)
    public void testCreate_InvalidValue() throws Exception {
        CoreWriter.create(Paths.get("asdf/fdya.properties"));
    }
}