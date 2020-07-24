/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2013 - 2018 PayinTech
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import com.jackson42.play.form.databinders.joda.formatter.DateTimeSimpleFormatter;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.text.ParseException;
import java.util.Locale;

/**
 * DateTimeTest.
 *
 * @author Pierre Adam
 * @since 16.05.18
 */
@org.junit.FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SimpleFormatterTest {

    private static final DateTimeSimpleFormatter simpleFormatter;
    private static final DateTimeSimpleFormatter simpleFormatterLongFormat;

    static {
        simpleFormatter = new DateTimeSimpleFormatter("yyyy-MM-dd", DateTimeZone.UTC, null);
        simpleFormatterLongFormat = new DateTimeSimpleFormatter("yyyy-MM-dd'T'HH:mm:ss", DateTimeZone.UTC, null);
    }

    /**
     * Test a valid DateTime.
     *
     * @since 16.05.18
     */
    @Test
    public void T01_ValidDate() throws ParseException {
        final String value = "2016-03-10";
        final DateTime realValue = DateTime.parse(value).withZoneRetainFields(DateTimeZone.UTC);
        final DateTime dateTime = SimpleFormatterTest.simpleFormatter.parse(value, Locale.FRANCE);

        Assert.assertNotNull(dateTime);
        Assert.assertEquals(realValue, dateTime);
    }

    /**
     * Test an invalid DateTime.
     *
     * @since 16.05.18
     */
    @Test(expected = ParseException.class)
    public void T02_InvalidDate() throws ParseException {
        SimpleFormatterTest.simpleFormatter.parse("2016-13-10", Locale.FRANCE);
    }

    /**
     * Test an invalid DateTime.
     *
     * @since 16.05.18
     */
    @Test(expected = ParseException.class)
    public void T03_InvalidDate() throws ParseException {
        SimpleFormatterTest.simpleFormatter.parse("qwerty", Locale.FRANCE);
    }

    /**
     * Test a valid DateTime with an other default setting.
     *
     * @since 16.05.18
     */
    @Test
    public void T04_ValidDateLongFormat() throws ParseException {
        final DateTime realValue = DateTime.now();
        final DateTime dateTime = SimpleFormatterTest.simpleFormatterLongFormat.parse(realValue.toString("yyyy-MM-dd'T'HH:mm:ss"), Locale.FRANCE);

        Assert.assertNotNull(dateTime);
        Assert.assertEquals(realValue.withMillisOfSecond(0).withZoneRetainFields(DateTimeZone.UTC), dateTime);
    }

    /**
     * Test an invalid DateTime with an other default setting.
     *
     * @since 16.05.18
     */
    @Test(expected = ParseException.class)
    public void T04_InvalidDateLongFormat() throws ParseException {
        SimpleFormatterTest.simpleFormatterLongFormat.parse("2016-03-10T01:02:60", Locale.FRANCE);
    }
}
