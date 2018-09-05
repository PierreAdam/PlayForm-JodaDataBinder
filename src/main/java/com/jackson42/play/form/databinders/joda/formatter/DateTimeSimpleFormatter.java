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
import play.data.format.Formatters;

import java.text.ParseException;
import java.util.Locale;

/**
 * DateTimeSimpleFormatter.
 *
 * @author Pierre Adam
 * @version 17.02.07
 * @since 16.01.31
 */
public class DateTimeSimpleFormatter extends Formatters.SimpleFormatter<DateTime> implements DateTimeParser {

    /**
     * The pattern to use when parsing or printing the joda datetime.
     *
     * @since 16.01.31
     */
    private final String pattern;

    /**
     * Simple constructor.
     *
     * @param pattern The pattern to use
     * @since 16.01.31
     */
    public DateTimeSimpleFormatter(final String pattern) {
        this.pattern = pattern;
    }

    /**
     * Parse the text from a form to a Joda {@code DateTime} using the internal pattern.
     *
     * @param text   The literal date
     * @param locale The current locale
     * @return Null if there is no text. The DateTime otherwise
     * @throws ParseException Parse error
     * @see DateTime
     * @since 16.01.31
     */
    @Override
    public DateTime parse(final String text, final Locale locale) throws ParseException {
        return this.parse(this.pattern, text, locale);
    }

    /**
     * Put the Joda {@code DateTime} to a {@code String} using the internal pattern.
     *
     * @param value  The DateTime
     * @param locale The current locale
     * @return An empty string if the DateTime is null. The Correctly formatted string otherwise
     * @since 16.01.31
     */
    @Override
    public String print(final DateTime value, final Locale locale) {
        return this.print(this.pattern, value, locale);
    }
}
