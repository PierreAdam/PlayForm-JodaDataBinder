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

package com.jackson42.play.form.databinders.joda.formatter;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import play.data.format.Formats;

import java.text.ParseException;
import java.util.Locale;

/**
 * DateTimeBasicAnnotatedFormatter.
 *
 * @author Pierre Adam
 * @version 17.02.07
 * @since 16.01.31
 */
public class DateTimeBasicAnnotatedFormatter extends DateTimeWithAnnotation<Formats.DateTime> {

    /**
     * Instantiates a new Date time with annotation.
     *
     * @param inputTimeZone  the input time zone
     * @param outputTimeZone the output time zone
     */
    public DateTimeBasicAnnotatedFormatter(final DateTimeZone inputTimeZone, final DateTimeZone outputTimeZone) {
        super(inputTimeZone, outputTimeZone);
    }

    /**
     * Parse the text from a form to a Joda {@code DateTime} using the play
     * {@code Formats.DateTime} annotation in order to obtain the correct format.
     *
     * @param annotation The annotation
     * @param text       The literal date
     * @param locale     The current locale
     * @return Null if there is no text. The DateTime otherwise
     * @throws ParseException Parse error
     * @see DateTime
     * @since 16.01.31
     */
    @Override
    public DateTime parse(final Formats.DateTime annotation, final String text, final Locale locale) throws ParseException {
        return this.parse(annotation.pattern(), text, locale, this.inputTimeZone);
    }

    /**
     * Put the Joda {@code DateTime} to a {@code String} using the the play
     * {@code Formats.DateTime} annotation in order to obtain the correct format.
     *
     * @param annotation The annotation
     * @param value      The DateTime
     * @param locale     The current locale
     * @return An empty string if the DateTime is null. The Correctly formatted string otherwise
     * @since 16.01.31
     */
    @Override
    public String print(final Formats.DateTime annotation, final DateTime value, final Locale locale) {
        return this.print(annotation.pattern(), value, locale, this.outputTimeZone);
    }
}
