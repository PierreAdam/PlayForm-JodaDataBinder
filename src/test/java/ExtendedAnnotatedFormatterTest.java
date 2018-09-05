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

import com.jackson42.play.form.databinders.joda.annotation.JodaDateTimeFormat;
import com.jackson42.play.form.databinders.joda.formatter.DateTimeExtendedAnnotatedFormatter;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.text.ParseException;
import java.util.Locale;

/**
 * ExtendedAnnotatedFormatterTest.
 *
 * @author Pierre Adam
 * @since 17.02.07
 */
@org.junit.FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExtendedAnnotatedFormatterTest {

    /**
     * The DateTime formatter.
     */
    private static final DateTimeExtendedAnnotatedFormatter formatter;

    static {
        formatter = new DateTimeExtendedAnnotatedFormatter();
    }

    /**
     * Test to parse a simple date.
     *
     * @throws ParseException       if the date can't be parsed
     * @throws NoSuchFieldException if the field of the class AnnotationTest can't be found
     * @since 17.02.07
     */
    @Test
    public void T01_SinglePatternParse() throws ParseException, NoSuchFieldException {
        final JodaDateTimeFormat annotation = AnnotationTest.class.getField("single").getAnnotation(JodaDateTimeFormat.class);

        final DateTime date = formatter.parse(annotation, "2015-03-05", Locale.FRANCE);
        Assert.assertEquals(2015, date.getYear());
        Assert.assertEquals(3, date.getMonthOfYear());
        Assert.assertEquals(5, date.getDayOfMonth());

        final DateTime date2 = formatter.parse(annotation, "2016-12-08", Locale.FRANCE);
        Assert.assertEquals(2016, date2.getYear());
        Assert.assertEquals(12, date2.getMonthOfYear());
        Assert.assertEquals(8, date2.getDayOfMonth());
    }

    /**
     * Test to print a simple date using the default pattern.
     *
     * @throws ParseException       if the date can't be parsed
     * @throws NoSuchFieldException if the field of the class AnnotationTest can't be found
     * @since 17.02.07
     */
    @Test
    public void T02_SinglePatternPrint() throws ParseException, NoSuchFieldException {
        final JodaDateTimeFormat annotation = AnnotationTest.class.getField("single").getAnnotation(JodaDateTimeFormat.class);

        final DateTime date = new DateTime(2015, 3, 5, 0, 0);
        final String output = formatter.print(annotation, date, Locale.FRANCE);

        Assert.assertEquals("2015-03-05", output);
    }

    /**
     * Test to parse several patterns with a single formatter.
     *
     * @throws ParseException       if the date can't be parsed
     * @throws NoSuchFieldException if the field of the class AnnotationTest can't be found
     * @since 17.02.07
     */
    @Test(expected = ParseException.class)
    public void T03_MultiplePatternParse() throws ParseException, NoSuchFieldException {
        final JodaDateTimeFormat annotation = AnnotationTest.class.getField("multiple").getAnnotation(JodaDateTimeFormat.class);

        final DateTime date = formatter.parse(annotation, "2015-03-05T12:15:35", Locale.FRANCE);
        Assert.assertEquals(2015, date.getYear());
        Assert.assertEquals(3, date.getMonthOfYear());
        Assert.assertEquals(5, date.getDayOfMonth());
        Assert.assertEquals(12, date.getHourOfDay());
        Assert.assertEquals(15, date.getMinuteOfHour());
        Assert.assertEquals(35, date.getSecondOfMinute());
        Assert.assertEquals(0, date.getMillisOfSecond());

        final DateTime date2 = formatter.parse(annotation, "2015-03-05T12:15:35.666S", Locale.FRANCE);
        Assert.assertEquals(2015, date2.getYear());
        Assert.assertEquals(3, date2.getMonthOfYear());
        Assert.assertEquals(5, date2.getDayOfMonth());
        Assert.assertEquals(12, date2.getHourOfDay());
        Assert.assertEquals(15, date2.getMinuteOfHour());
        Assert.assertEquals(35, date2.getSecondOfMinute());
        Assert.assertEquals(666, date2.getMillisOfSecond());
    }

    /**
     * Test to print the date with an annotation which has several patterns.
     *
     * @throws ParseException       if the date can't be parsed
     * @throws NoSuchFieldException if the field of the class AnnotationTest can't be found
     * @since 17.02.07
     */
    @Test
    public void T04_MultiplePatternPrint() throws ParseException, NoSuchFieldException {
        final JodaDateTimeFormat annotation = AnnotationTest.class.getField("multiple").getAnnotation(JodaDateTimeFormat.class);

        final DateTime date = new DateTime(2015, 3, 5, 12, 15, 35, 666);
        final String output = formatter.print(annotation, date, Locale.FRANCE);

        Assert.assertEquals("2015-03-05T12:15:35", output);
    }

    /**
     * Test to print the date with an annotation which has several patterns and an explicit printing pattern.
     *
     * @throws ParseException       if the date can't be parsed
     * @throws NoSuchFieldException if the field of the class AnnotationTest can't be found
     * @since 17.02.07
     */
    @Test
    public void T05_MultiplePatternAdvancedPrint() throws ParseException, NoSuchFieldException {
        final JodaDateTimeFormat annotation = AnnotationTest.class.getField("multipleAdvanced").getAnnotation(JodaDateTimeFormat.class);

        final DateTime date = new DateTime(2015, 3, 5, 12, 15, 35, 666);
        final String output = formatter.print(annotation, date, Locale.FRANCE);

        Assert.assertEquals("The 2015-03-05 at 12:15:35 and 666 ms", output);
    }

    /**
     * Test with a correct format but an invalid date.
     *
     * @throws ParseException       if the date can't be parsed
     * @throws NoSuchFieldException if the field of the class AnnotationTest can't be found
     * @since 17.02.07
     */
    @Test(expected = ParseException.class)
    public void T06_InputDateError() throws ParseException, NoSuchFieldException {
        final JodaDateTimeFormat annotation = AnnotationTest.class.getField("single").getAnnotation(JodaDateTimeFormat.class);

        formatter.parse(annotation, "2015-03-44", Locale.FRANCE);
    }

    /**
     * Test with an invalid format.
     *
     * @throws ParseException       if the date can't be parsed
     * @throws NoSuchFieldException if the field of the class AnnotationTest can't be found
     * @since 17.02.07
     */
    @Test(expected = ParseException.class)
    public void T07_InputDateError() throws ParseException, NoSuchFieldException {
        final JodaDateTimeFormat annotation = AnnotationTest.class.getField("single").getAnnotation(JodaDateTimeFormat.class);

        formatter.parse(annotation, "qwerty", Locale.FRANCE);
    }

    /**
     * Test class only use to hold the annotations.
     */
    private static class AnnotationTest {
        @JodaDateTimeFormat(patterns = "YYYY-MM-dd")
        public DateTime single;

        @JodaDateTimeFormat(patterns = {"yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"})
        public DateTime multiple;

        @JodaDateTimeFormat(patterns = {"yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"}, printPattern = "'The' yyyy-MM-dd 'at' HH:mm:ss 'and' SSS 'ms'")
        public DateTime multipleAdvanced;
    }
}
