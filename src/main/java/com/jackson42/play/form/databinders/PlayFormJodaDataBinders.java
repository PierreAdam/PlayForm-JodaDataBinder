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

package com.jackson42.play.form.databinders;

import com.jackson42.play.form.databinders.joda.formatter.*;
import org.joda.time.DateTime;
import play.data.format.Formatters;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * PlayFormJodaDataBinders.
 *
 * @author Pierre Adam
 * @since 18.08.08
 */
@Singleton
public class PlayFormJodaDataBinders {

    /**
     * Build an instance.
     *
     * @param formatters The Play Formatters instance
     * @since 18.08.08
     */
    @Inject
    public PlayFormJodaDataBinders(final Formatters formatters) {
        formatters.register(DateTime.class, new DateTimeSimpleFormatter("yyyy-MM-dd"));
        formatters.register(DateTime.class, new DateTimeBasicAnnotatedFormatter());
        formatters.register(DateTime.class, new DateTimeExtendedAnnotatedFormatter());
        formatters.register(DateTime.class, new DateTimeISO8601DateTimeFormatter());
        formatters.register(DateTime.class, new DateTimeISO8601TimeFormatter());
    }
}
