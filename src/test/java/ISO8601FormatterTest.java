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

import com.jackson42.play.form.databinders.joda.annotation.JodaISO8601DateTimeFormat;
import com.jackson42.play.form.databinders.joda.annotation.JodaISO8601TimeFormat;
import com.jackson42.play.form.databinders.joda.formatter.DateTimeISO8601DateTimeFormatter;
import com.jackson42.play.form.databinders.joda.formatter.DateTimeISO8601TimeFormatter;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.text.ParseException;
import java.util.Locale;

/**
 * ISO8601FormatterTest.
 *
 * @author Pierre Adam
 * @since 18.09.05
 */
@org.junit.FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ISO8601FormatterTest {

    /**
     * The ISO8601 date/time Formatter.
     */
    private static final DateTimeISO8601DateTimeFormatter dateTimeFormatter;

    /**
     * The ISO8601 time formatter.
     */
    private static final DateTimeISO8601TimeFormatter timeFormatter;

    static {
        dateTimeFormatter = new DateTimeISO8601DateTimeFormatter();
        timeFormatter = new DateTimeISO8601TimeFormatter();
    }

    /**
     * Test a valid ISO8601 DateTime.
     *
     * @since 18.09.05
     */
    @Test
    public void T01_ValidDateTime() throws ParseException, NoSuchFieldException {
        final JodaISO8601DateTimeFormat annotation = ISO8601FormatterTest.AnnotationTest.class.getField("dateTime").getAnnotation(JodaISO8601DateTimeFormat.class);

        final DateTime date1 = dateTimeFormatter.parse(annotation, "1985-01-20", Locale.FRANCE);
        Assert.assertEquals(1985, date1.getYear());
        Assert.assertEquals(1, date1.getMonthOfYear());
        Assert.assertEquals(20, date1.getDayOfMonth());

        final DateTime date2 = dateTimeFormatter.parse(annotation, "1985-01-20T20:45:30Z", Locale.FRANCE);
        Assert.assertEquals(1985, date2.getYear());
        Assert.assertEquals(1, date2.getMonthOfYear());
        Assert.assertEquals(20, date2.getDayOfMonth());
        Assert.assertEquals(20, date2.getHourOfDay());
        Assert.assertEquals(45, date2.getMinuteOfHour());
        Assert.assertEquals(30, date2.getSecondOfMinute());

        final DateTime date3 = dateTimeFormatter.parse(annotation, "1985-01-20T22:45:30+02:00", Locale.FRANCE);
        Assert.assertEquals(1985, date3.getYear());
        Assert.assertEquals(1, date3.getMonthOfYear());
        Assert.assertEquals(20, date3.getDayOfMonth());
        Assert.assertEquals(20, date3.getHourOfDay());
        Assert.assertEquals(45, date3.getMinuteOfHour());
        Assert.assertEquals(30, date3.getSecondOfMinute());

        final DateTime date4 = dateTimeFormatter.parse(annotation, "1985-01-20T20:45:30.420Z", Locale.FRANCE);
        Assert.assertEquals(1985, date4.getYear());
        Assert.assertEquals(1, date4.getMonthOfYear());
        Assert.assertEquals(20, date4.getDayOfMonth());
        Assert.assertEquals(20, date4.getHourOfDay());
        Assert.assertEquals(45, date4.getMinuteOfHour());
        Assert.assertEquals(30, date4.getSecondOfMinute());
        Assert.assertEquals(420, date4.getMillisOfSecond());

        final DateTime date5 = dateTimeFormatter.parse(annotation, "1985-01-21T01:45:30.420+05:00", Locale.FRANCE);
        Assert.assertEquals(1985, date5.getYear());
        Assert.assertEquals(1, date5.getMonthOfYear());
        Assert.assertEquals(20, date5.getDayOfMonth());
        Assert.assertEquals(20, date5.getHourOfDay());
        Assert.assertEquals(45, date5.getMinuteOfHour());
        Assert.assertEquals(30, date5.getSecondOfMinute());
        Assert.assertEquals(420, date5.getMillisOfSecond());
    }

    /**
     * Test a valid ISO8601 DateTime.
     *
     * @since 18.09.05
     */
    @Test
    public void T02_ValidTime() throws ParseException, NoSuchFieldException {
        final JodaISO8601TimeFormat annotation = ISO8601FormatterTest.AnnotationTest.class.getField("time").getAnnotation(JodaISO8601TimeFormat.class);

        final DateTime time1 = timeFormatter.parse(annotation, "T20:45:30Z", Locale.FRANCE);
        Assert.assertEquals(20, time1.getHourOfDay());
        Assert.assertEquals(45, time1.getMinuteOfHour());
        Assert.assertEquals(30, time1.getSecondOfMinute());


        final DateTime time2 = timeFormatter.parse(annotation, "T20:45:30-10:00", Locale.FRANCE);
        Assert.assertEquals(6, time2.getHourOfDay());
        Assert.assertEquals(45, time2.getMinuteOfHour());
        Assert.assertEquals(30, time2.getSecondOfMinute());

        final DateTime time3 = timeFormatter.parse(annotation, "T20:45:30.420Z", Locale.FRANCE);
        Assert.assertEquals(20, time3.getHourOfDay());
        Assert.assertEquals(45, time3.getMinuteOfHour());
        Assert.assertEquals(30, time3.getSecondOfMinute());
        Assert.assertEquals(420, time3.getMillisOfSecond());

        final DateTime time4 = timeFormatter.parse(annotation, "T01:45:30.420+05:00", Locale.FRANCE);
        Assert.assertEquals(20, time4.getHourOfDay());
        Assert.assertEquals(45, time4.getMinuteOfHour());
        Assert.assertEquals(30, time4.getSecondOfMinute());
        Assert.assertEquals(420, time4.getMillisOfSecond());
    }

    private static class AnnotationTest {
        @JodaISO8601DateTimeFormat
        public DateTime dateTime;

        @JodaISO8601TimeFormat
        public DateTime time;
    }
}
