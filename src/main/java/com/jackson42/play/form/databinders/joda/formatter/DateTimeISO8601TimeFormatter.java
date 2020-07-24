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

import com.jackson42.play.form.databinders.joda.annotation.JodaISO8601TimeFormat;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.text.ParseException;
import java.util.Locale;

/**
 * DateTimeISO8601TimeFormatter.
 *
 * @author Pierre Adam
 * @since 18.09.05
 */
public class DateTimeISO8601TimeFormatter extends DateTimeWithAnnotation<JodaISO8601TimeFormat> {

    /**
     * The Patterns.
     */
    private static final String[] patterns = {"'T'HH:mm:ssZZ", "'T'HH:mm:ss.SSSZZ"};

    /**
     * Instantiates a new Date time with annotation.
     *
     * @param inputTimeZone  the input time zone
     * @param outputTimeZone the output time zone
     */
    public DateTimeISO8601TimeFormatter(final DateTimeZone inputTimeZone, final DateTimeZone outputTimeZone) {
        super(inputTimeZone, outputTimeZone);
    }

    @Override
    public DateTime parse(final JodaISO8601TimeFormat annotation, final String text, final Locale locale) throws ParseException {
        return this.parse(DateTimeISO8601TimeFormatter.patterns, text, locale, this.inputTimeZone);
    }

    @Override
    public String print(final JodaISO8601TimeFormat annotation, final DateTime value, final Locale locale) {
        return this.print(annotation.printPattern(), DateTimeISO8601TimeFormatter.patterns, value, locale, this.outputTimeZone);
    }
}
