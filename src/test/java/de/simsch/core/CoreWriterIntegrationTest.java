package de.simsch.core;

import de.simsch.util.Constants;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author simsch
 */
public class CoreWriterIntegrationTest {

    @Before
    public void setUp() throws Exception {
        PrintWriter printWriter = new PrintWriter(Constants.getPropertiesFileForWriting().toAbsolutePath().toString());
        printWriter.print("");
        printWriter.close();
    }

    @Test
    public void testCreate() throws Exception {
        CoreWriter.create(Constants.getPropertiesFileForWriting());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetValue_InvalidKey() throws Exception {
        CoreWriter writer = CoreWriter.create(Constants.getPropertiesFile());
        writer.writeProperty(null);
    }

    @Test
    public void testGetValue_ValidKey() throws Exception {
        CoreWriter writer = CoreWriter.create(Constants.getPropertiesFile());
        boolean isSuccess = writer.writeProperty("intValue", "1234");
        assertThat(isSuccess, is(true));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetValue_NullKey() throws Exception {
        CoreWriter writer = CoreWriter.create(Constants.getPropertiesFile());
        writer.writeProperty(null, "1234");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetValue_NullValue() throws Exception {
        CoreWriter writer = CoreWriter.create(Constants.getPropertiesFile());
        writer.writeProperty("intValue", null);
    }

    @Test
    public void testGetValue_MapAsValue() throws Exception {
        CoreWriter writer = CoreWriter.create(Constants.getPropertiesFile());
        Map<String, String> map = new HashMap<>();
        map.put("intValue", "123");
        map.put("doubleValue", "123.45");
        boolean isSuccess = writer.writeProperty(map);
        assertThat(isSuccess, is(true));
    }

    @Test
    public void testDeleteProperty() throws Exception {
        CoreWriter writerMockData = CoreWriter.create(Constants.getPropertiesFile());
        writerMockData.writeProperty("intValue", "1234");
        CoreWriter writer = CoreWriter.create(Constants.getPropertiesFile());
        boolean isSuccess = writer.deleteProperty("intValue");
        assertThat(isSuccess, is(true));
    }
}
