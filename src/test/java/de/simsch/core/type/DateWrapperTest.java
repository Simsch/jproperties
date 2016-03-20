package de.simsch.core.type;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.typeCompatibleWith;
import static org.junit.Assert.assertThat;

/**
 * @author simsch
 */
public class DateWrapperTest {

    private static final List<DateTimeFormatter> FORMATTERS     = Collections.singletonList(DateTimeFormatter.ISO_DATE_TIME);
    private static final String                  EXPECTED_VALUE = LocalDateTime.now().format(FORMATTERS.get(0));

    @Test
    public void testSuperInterface_Value() throws Exception {
        DateWrapper dateWrapper = new DateWrapper(Optional.of(EXPECTED_VALUE));
        assertThat(dateWrapper.getClass(), typeCompatibleWith(Value.class));
    }

    @Test
    public void testSuperInterface_Convertible() throws Exception {
        DateWrapper dateWrapper = new DateWrapper(Optional.of(EXPECTED_VALUE));
        assertThat(dateWrapper.getClass(), typeCompatibleWith(Convertible.class));
    }

    @Test
    public void testParse_Working() throws Exception {
        DateWrapper dateWrapper = new DateWrapper(Optional.of(EXPECTED_VALUE));
        Optional<LocalDateTime> value = dateWrapper.parse();
        assertThat(value, notNullValue());
        assertThat(value.isPresent(), is(true));
        assertThat(value.get(), equalTo(LocalDateTime.parse(EXPECTED_VALUE)));
    }

    @Test
    public void testParse_NullGiven() throws Exception {
        DateWrapper dateWrapper = new DateWrapper(null);
        Optional<LocalDateTime> value = dateWrapper.parse();
        assertThat(value, equalTo(Optional.empty()));
    }

    @Test
    public void testParse_EmptyValueGiven() throws Exception {
        DateWrapper dateWrapper = new DateWrapper(Optional.empty());
        Optional<LocalDateTime> value = dateWrapper.parse();
        assertThat(value, notNullValue());
        assertThat(value.isPresent(), is(false));
    }

    @Test
    public void testParse_NullValueGiven() throws Exception {
        DateWrapper dateWrapper = new DateWrapper(Optional.ofNullable(null));
        Optional<LocalDateTime> value = dateWrapper.parse();
        assertThat(value, notNullValue());
        assertThat(value.isPresent(), is(false));
    }

    @Test
    public void testConvertToString_Working() throws Exception {
        DateWrapper dateWrapper = new DateWrapper(Optional.empty());
        LocalDateTime expectedTime = LocalDateTime.of(2016, 3, 20, 4, 13, 20);
        String convertedDate = dateWrapper.convertToString(expectedTime);
        assertThat(convertedDate, equalTo(DateTimeFormatter.ISO_DATE_TIME.format(expectedTime)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertToString_NullValue() throws Exception {
        DateWrapper dateWrapper = new DateWrapper(Optional.empty());
        dateWrapper.convertToString(null);
    }
}