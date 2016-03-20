package de.simsch;

import de.simsch.util.Constants;
import de.simsch.util.TestObject;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

/**
 * @author Simon Schober <simon.schober@live.com>
 */
public class JPropertyWriterTest {

    private JPropertyWriter writer;

    @Before
    public void setUp() throws Exception {
        writer = JPropertyWriter.create(PropertyFile.createExternal(Constants.getPropertiesFileForWriting()));
    }

    @Test
    public void testCreate_ValidParameter() throws Exception {
        JPropertyWriter writer = JPropertyWriter.create(PropertyFile.createExternal(Constants.getPropertiesFileForWriting()));
        assertThat(writer, notNullValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreate_NullParameter() throws Exception {
        JPropertyWriter.create(null);
    }

    @Test
    public void testWrite_ValidParameter() throws Exception {
        boolean isSuccess = writer.write("intKey", "123");
        assertThat(isSuccess, is(true));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrite_NullKey() throws Exception {
        writer.write(null, "123");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrite_EmptyKey() throws Exception {
        writer.write(" ", "123");
    }

    @Test
    public void testWrite_ValidDateTimeParameter() throws Exception {
        boolean isSuccess = writer.write("dateKey", LocalDateTime.now());
        assertThat(isSuccess, is(true));
    }

    @Test
    public void testWrite_ValidObjectParameter() throws Exception {
        boolean isSuccess = writer.write("objectKey", new TestObject(), TestObject.class);
        assertThat(isSuccess, is(true));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrite_NullObjectParameter() throws Exception {
        writer.write("objectKey", null, TestObject.class);
    }

    @Test
    public void testWrite_NullClassParameter() throws Exception {
        boolean isSuccess = writer.write("objectKey", new TestObject(), null);
        assertThat(isSuccess, is(true));
    }

    @Test
    public void testDelete_EntryPresent() throws Exception {
        writer.write("dateKey", LocalDateTime.now());
        boolean isSuccess = writer.delete("dateKey");
        assertThat(isSuccess, is(true));
    }

    @Test
    public void testDelete_EntryNotPresent() throws Exception {
        boolean isSuccess = writer.delete("objectKey");
        assertThat(isSuccess, is(true));
    }
}