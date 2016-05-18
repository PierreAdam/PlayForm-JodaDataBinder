import com.jackson42.play.form.databinders.joda.DateTimeBinders;
import org.joda.time.DateTime;
import org.joda.time.IllegalFieldValueException;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.util.Locale;

/**
 * DateTimeTest.
 *
 * @author Pierre Adam
 * @version 16.05.18
 * @since 16.05.18
 */
public class DateTimeTest {

    private static final DateTimeBinders.DateTimeSimpleFormatter simpleFormatter;
    private static final DateTimeBinders.DateTimeSimpleFormatter simpleFormatterLongFormat;
    private static final DateTimeBinders.DateTimeAnnotationFormatter annotedFormatter;


    static {
        simpleFormatter = new DateTimeBinders.DateTimeSimpleFormatter("yyyy-MM-dd");
        simpleFormatterLongFormat = new DateTimeBinders.DateTimeSimpleFormatter("yyyy-MM-dd'T'HH:mm:ss");
        annotedFormatter = new DateTimeBinders.DateTimeAnnotationFormatter();
    }

    /**
     * Test a valid DateTime.
     *
     * @since 16.05.18
     */
    @Test
    public void simpleParse_01() throws ParseException {
        final String value = "2016-03-10";
        final DateTime realValue = DateTime.parse(value);
        final DateTime dateTime = simpleFormatter.parse(value, Locale.FRANCE);

        Assert.assertNotNull(dateTime);
        Assert.assertEquals(realValue, dateTime);
    }

    /**
     * Test an invalid DateTime.
     *
     * @since 16.05.18
     */
    @Test(expected = IllegalFieldValueException.class)
    public void simpleParse_02() throws ParseException {
        simpleFormatter.parse("2016-13-10", Locale.FRANCE);
    }

    /**
     * Test an invalid DateTime.
     *
     * @since 16.05.18
     */
    @Test(expected = IllegalArgumentException.class)
    public void simpleParse_03() throws ParseException {
        simpleFormatter.parse("qwerty", Locale.FRANCE);
    }

    /**
     * Test a valid DateTime with an other default setting.
     *
     * @since 16.05.18
     */
    @Test
    public void simpleParse_04() throws ParseException {
        final DateTime realValue = DateTime.now();
        final DateTime dateTime = simpleFormatterLongFormat.parse(realValue.toString("yyyy-MM-dd'T'HH:mm:ss"), Locale.FRANCE);

        Assert.assertNotNull(dateTime);
        Assert.assertEquals(realValue.withMillisOfSecond(0), dateTime);
    }

    /**
     * Test an invalid DateTime with an other default setting.
     *
     * @since 16.05.18
     */
    @Test(expected = IllegalFieldValueException.class)
    public void simpleParse_05() throws ParseException {
        simpleFormatterLongFormat.parse("2016-03-10T01:02:60", Locale.FRANCE);
    }
}
