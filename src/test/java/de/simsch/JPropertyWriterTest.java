package de.simsch;

import de.simsch.util.Constants;
import de.simsch.util.TestObject;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

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
        writer.write("intKey", "123");
        Integer result = JPropertyReader
                .create(PropertyFile.createExternal(Constants.getPropertiesFileForWriting()))
                .readInteger("intKey")
                .get();
        assertThat(result, is(123));
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
        LocalDateTime data = LocalDateTime.now();
        writer.write("dateKey", data);
        LocalDateTime result = JPropertyReader
                .create(PropertyFile.createExternal(Constants.getPropertiesFileForWriting()))
                .readDate("dateKey")
                .get();
        assertThat(result, equalTo(data));
    }

    @Test
    public void testWrite_ValidObjectParameter() throws Exception {
        writer.write("objectKey", new TestObject(), TestObject.class);
        TestObject result = JPropertyReader
                .create(PropertyFile.createExternal(Constants.getPropertiesFileForWriting()))
                .readObject("objectKey", TestObject.class)
                .get();
        assertThat(result, equalTo(new TestObject()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrite_NullObjectParameter() throws Exception {
        writer.write("objectKey", null, TestObject.class);
    }

    @Test
    public void testWrite_NullClassParameter() throws Exception {
        writer.write("objectKey", new TestObject(), null);
        TestObject result = JPropertyReader
                .create(PropertyFile.createExternal(Constants.getPropertiesFileForWriting()))
                .readObject("objectKey", TestObject.class)
                .get();
        assertThat(result, is(new TestObject()));
    }

    @Test
    public void testDelete_EntryPresent() throws Exception {
        writer.write("dateKey", LocalDateTime.now()).delete("dateKey");
        Optional<LocalDateTime> result = JPropertyReader
                .create(PropertyFile.createExternal(Constants.getPropertiesFileForWriting()))
                .readDate("dateKey");
        assertThat(result.isPresent(), is(false));
    }

    @Test
    public void testDelete_EntryNotPresent() throws Exception {
        writer.delete("objectKey");
        Optional<LocalDateTime> result = JPropertyReader
                .create(PropertyFile.createExternal(Constants.getPropertiesFileForWriting()))
                .readObject("dateKey", LocalDateTime.class);
        assertThat(result.isPresent(), is(false));
    }
}