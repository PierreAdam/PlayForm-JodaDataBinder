/*
 * Copyright (C) 2014 - 2020 PayinTech, SAS - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

import com.jackson42.play.form.databinders.joda.annotation.JodaDateTimeFormat;
import com.jackson42.play.form.databinders.joda.annotation.JodaISO8601DateTimeFormat;
import com.jackson42.play.form.databinders.joda.formatter.DateTimeExtendedAnnotatedFormatter;
import com.jackson42.play.form.databinders.joda.formatter.DateTimeISO8601DateTimeFormatter;
import com.jackson42.play.form.databinders.joda.formatter.DateTimeSimpleFormatter;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.text.ParseException;
import java.util.Locale;

/**
 * TimeZoneTest.
 *
 * @author Pierre Adam
 * @since 20.07.24
 */
@org.junit.FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TimeZoneTest {

    private static final DateTimeSimpleFormatter utcSimpleFormatter;
    private static final DateTimeSimpleFormatter frenchSimpleFormatter;
    private static final DateTimeSimpleFormatter usEastSimpleFormatter;

    private static final DateTimeExtendedAnnotatedFormatter utcAnnotatedFormatter;
    private static final DateTimeExtendedAnnotatedFormatter frenchAnnotatedFormatter;
    private static final DateTimeExtendedAnnotatedFormatter usEastAnnotatedFormatter;

    private static final DateTimeExtendedAnnotatedFormatter utcInputFrenchOutput;
    private static final DateTimeExtendedAnnotatedFormatter utcInputUsEastOutput;

    private static final DateTimeISO8601DateTimeFormatter utcStandardReader;

    static {
        utcSimpleFormatter = new DateTimeSimpleFormatter("yyyy-MM-dd'T'HH:mm:ss", DateTimeZone.UTC, null);
        frenchSimpleFormatter = new DateTimeSimpleFormatter("yyyy-MM-dd'T'HH:mm:ss", DateTimeZone.forID("Europe/Paris"), null);
        usEastSimpleFormatter = new DateTimeSimpleFormatter("yyyy-MM-dd'T'HH:mm:ss", DateTimeZone.forID("America/New_York"), null);

        utcAnnotatedFormatter = new DateTimeExtendedAnnotatedFormatter(DateTimeZone.UTC, null);
        frenchAnnotatedFormatter = new DateTimeExtendedAnnotatedFormatter(DateTimeZone.forID("Europe/Paris"), null);
        usEastAnnotatedFormatter = new DateTimeExtendedAnnotatedFormatter(DateTimeZone.forID("America/New_York"), null);

        utcInputFrenchOutput = new DateTimeExtendedAnnotatedFormatter(DateTimeZone.UTC, DateTimeZone.forID("Europe/Paris"));
        utcInputUsEastOutput = new DateTimeExtendedAnnotatedFormatter(DateTimeZone.UTC, DateTimeZone.forID("America/New_York"));

        utcStandardReader = new DateTimeISO8601DateTimeFormatter(DateTimeZone.UTC, null);
    }

    @Test
    public void T01_TzWithSimpleFormatter() throws ParseException {
        final String value = "2020-07-24T19:01:32";
        final DateTime utcRead = TimeZoneTest.utcSimpleFormatter.parse(value, Locale.ENGLISH);
        final DateTime frenchRead = TimeZoneTest.frenchSimpleFormatter.parse(value, Locale.ENGLISH);
        final DateTime usEastRead = TimeZoneTest.usEastSimpleFormatter.parse(value, Locale.ENGLISH);

        Assert.assertNotNull(utcRead);
        Assert.assertNotNull(frenchRead);
        Assert.assertNotNull(usEastRead);

        Assert.assertEquals(DateTimeZone.UTC, utcRead.getZone());
        Assert.assertEquals(2020, utcRead.getYear());
        Assert.assertEquals(7, utcRead.getMonthOfYear());
        Assert.assertEquals(24, utcRead.getDayOfMonth());
        Assert.assertEquals(19, utcRead.getHourOfDay());
        Assert.assertEquals(1, utcRead.getMinuteOfHour());
        Assert.assertEquals(32, utcRead.getSecondOfMinute());

        Assert.assertEquals(DateTimeZone.forID("Europe/Paris"), frenchRead.getZone());
        Assert.assertEquals(2020, frenchRead.getYear());
        Assert.assertEquals(7, frenchRead.getMonthOfYear());
        Assert.assertEquals(24, frenchRead.getDayOfMonth());
        Assert.assertEquals(19, frenchRead.getHourOfDay());
        Assert.assertEquals(1, frenchRead.getMinuteOfHour());
        Assert.assertEquals(32, frenchRead.getSecondOfMinute());

        final DateTime frenchToUTC = frenchRead.withZone(DateTimeZone.UTC);
        Assert.assertEquals(DateTimeZone.UTC, frenchToUTC.getZone());
        Assert.assertEquals(2020, frenchToUTC.getYear());
        Assert.assertEquals(7, frenchToUTC.getMonthOfYear());
        Assert.assertEquals(24, frenchToUTC.getDayOfMonth());
        Assert.assertEquals(17, frenchToUTC.getHourOfDay());
        Assert.assertEquals(1, frenchToUTC.getMinuteOfHour());
        Assert.assertEquals(32, frenchToUTC.getSecondOfMinute());

        Assert.assertEquals(DateTimeZone.forID("America/New_York"), usEastRead.getZone());
        Assert.assertEquals(2020, usEastRead.getYear());
        Assert.assertEquals(7, usEastRead.getMonthOfYear());
        Assert.assertEquals(24, usEastRead.getDayOfMonth());
        Assert.assertEquals(19, usEastRead.getHourOfDay());
        Assert.assertEquals(1, usEastRead.getMinuteOfHour());
        Assert.assertEquals(32, usEastRead.getSecondOfMinute());

        final DateTime usEastToUTC = usEastRead.withZone(DateTimeZone.UTC);
        Assert.assertEquals(DateTimeZone.UTC, usEastToUTC.getZone());
        Assert.assertEquals(2020, usEastToUTC.getYear());
        Assert.assertEquals(7, usEastToUTC.getMonthOfYear());
        Assert.assertEquals(24, usEastToUTC.getDayOfMonth());
        Assert.assertEquals(23, usEastToUTC.getHourOfDay());
        Assert.assertEquals(1, usEastToUTC.getMinuteOfHour());
        Assert.assertEquals(32, usEastToUTC.getSecondOfMinute());
    }

    @Test
    public void T02_TzWithAnnotatedFormatter() throws ParseException, NoSuchFieldException {
        final JodaDateTimeFormat annotation = TimeZoneTest.AnnotationTest.class.getField("simpleDateTime").getAnnotation(JodaDateTimeFormat.class);
        final String value = "2020-07-24T19:01:32";
        final DateTime utcRead = TimeZoneTest.utcAnnotatedFormatter.parse(annotation, value, Locale.ENGLISH);
        final DateTime frenchRead = TimeZoneTest.frenchAnnotatedFormatter.parse(annotation, value, Locale.ENGLISH);
        final DateTime usEastRead = TimeZoneTest.usEastAnnotatedFormatter.parse(annotation, value, Locale.ENGLISH);

        Assert.assertNotNull(utcRead);
        Assert.assertNotNull(frenchRead);
        Assert.assertNotNull(usEastRead);

        Assert.assertEquals(DateTimeZone.UTC, utcRead.getZone());
        Assert.assertEquals(2020, utcRead.getYear());
        Assert.assertEquals(7, utcRead.getMonthOfYear());
        Assert.assertEquals(24, utcRead.getDayOfMonth());
        Assert.assertEquals(19, utcRead.getHourOfDay());
        Assert.assertEquals(1, utcRead.getMinuteOfHour());
        Assert.assertEquals(32, utcRead.getSecondOfMinute());

        Assert.assertEquals(DateTimeZone.forID("Europe/Paris"), frenchRead.getZone());
        Assert.assertEquals(2020, frenchRead.getYear());
        Assert.assertEquals(7, frenchRead.getMonthOfYear());
        Assert.assertEquals(24, frenchRead.getDayOfMonth());
        Assert.assertEquals(19, frenchRead.getHourOfDay());
        Assert.assertEquals(1, frenchRead.getMinuteOfHour());
        Assert.assertEquals(32, frenchRead.getSecondOfMinute());

        final DateTime frenchToUTC = frenchRead.withZone(DateTimeZone.UTC);
        Assert.assertEquals(DateTimeZone.UTC, frenchToUTC.getZone());
        Assert.assertEquals(2020, frenchToUTC.getYear());
        Assert.assertEquals(7, frenchToUTC.getMonthOfYear());
        Assert.assertEquals(24, frenchToUTC.getDayOfMonth());
        Assert.assertEquals(17, frenchToUTC.getHourOfDay());
        Assert.assertEquals(1, frenchToUTC.getMinuteOfHour());
        Assert.assertEquals(32, frenchToUTC.getSecondOfMinute());

        Assert.assertEquals(DateTimeZone.forID("America/New_York"), usEastRead.getZone());
        Assert.assertEquals(2020, usEastRead.getYear());
        Assert.assertEquals(7, usEastRead.getMonthOfYear());
        Assert.assertEquals(24, usEastRead.getDayOfMonth());
        Assert.assertEquals(19, usEastRead.getHourOfDay());
        Assert.assertEquals(1, usEastRead.getMinuteOfHour());
        Assert.assertEquals(32, usEastRead.getSecondOfMinute());

        final DateTime usEastToUTC = usEastRead.withZone(DateTimeZone.UTC);
        Assert.assertEquals(DateTimeZone.UTC, usEastToUTC.getZone());
        Assert.assertEquals(2020, usEastToUTC.getYear());
        Assert.assertEquals(7, usEastToUTC.getMonthOfYear());
        Assert.assertEquals(24, usEastToUTC.getDayOfMonth());
        Assert.assertEquals(23, usEastToUTC.getHourOfDay());
        Assert.assertEquals(1, usEastToUTC.getMinuteOfHour());
        Assert.assertEquals(32, usEastToUTC.getSecondOfMinute());
    }

    @Test
    public void T03_TzOutput() throws ParseException, NoSuchFieldException {
        final JodaDateTimeFormat annotation = TimeZoneTest.AnnotationTest.class.getField("simpleDateTime").getAnnotation(JodaDateTimeFormat.class);
        final String value = "2020-07-24T19:01:32";
        final DateTime read = TimeZoneTest.utcInputFrenchOutput.parse(annotation, value, Locale.ENGLISH);

        Assert.assertNotNull(read);

        Assert.assertEquals(DateTimeZone.UTC, read.getZone());
        Assert.assertEquals(2020, read.getYear());
        Assert.assertEquals(7, read.getMonthOfYear());
        Assert.assertEquals(24, read.getDayOfMonth());
        Assert.assertEquals(19, read.getHourOfDay());
        Assert.assertEquals(1, read.getMinuteOfHour());
        Assert.assertEquals(32, read.getSecondOfMinute());

        Assert.assertEquals("2020-07-24T19:01:32", read.toString(annotation.patterns()[0]));
        Assert.assertEquals("2020-07-24T21:01:32", TimeZoneTest.utcInputFrenchOutput.print(annotation, read, Locale.ENGLISH));
        Assert.assertEquals("2020-07-24T15:01:32", TimeZoneTest.utcInputUsEastOutput.print(annotation, read, Locale.ENGLISH));
    }

    @Test
    public void T04_TzInput() throws ParseException, NoSuchFieldException {
        final JodaISO8601DateTimeFormat annotation = TimeZoneTest.AnnotationTest.class.getField("iso8601").getAnnotation(JodaISO8601DateTimeFormat.class);
        final String value = "2020-07-24T19:01:32.44+02:00";
        final String value2 = "2020-07-24T19:01:32.44-10:00";
        final DateTime read = TimeZoneTest.utcStandardReader.parse(annotation, value, Locale.ENGLISH);
        final DateTime read2 = TimeZoneTest.utcStandardReader.parse(annotation, value2, Locale.ENGLISH);

        Assert.assertNotNull(read);
        Assert.assertNotNull(read2);

        Assert.assertEquals(DateTimeZone.UTC, read.getZone());
        Assert.assertEquals(2020, read.getYear());
        Assert.assertEquals(7, read.getMonthOfYear());
        Assert.assertEquals(24, read.getDayOfMonth());
        Assert.assertEquals(17, read.getHourOfDay());
        Assert.assertEquals(1, read.getMinuteOfHour());
        Assert.assertEquals(32, read.getSecondOfMinute());

        Assert.assertEquals(DateTimeZone.UTC, read2.getZone());
        Assert.assertEquals(2020, read2.getYear());
        Assert.assertEquals(7, read2.getMonthOfYear());
        Assert.assertEquals(25, read2.getDayOfMonth());
        Assert.assertEquals(5, read2.getHourOfDay());
        Assert.assertEquals(1, read2.getMinuteOfHour());
        Assert.assertEquals(32, read2.getSecondOfMinute());
    }

    /**
     * Test class only use to hold the annotations.
     */
    private static class AnnotationTest {
        @JodaDateTimeFormat(patterns = "yyyy-MM-dd'T'HH:mm:ss")
        public DateTime simpleDateTime;

        @JodaISO8601DateTimeFormat
        public DateTime iso8601;
    }
}
