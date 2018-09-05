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

package com.jackson42.play.form.databinders.joda.annotation;

import play.data.Form;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * JodaISO8601TimeFormat will automatically accept the following format compliant with the ISO8601 :
 *
 * <table>
 *     <tr>
 *         <th>Format</th>
 *         <th>Example</th>
 *         <th>Comment</th>
 *     </tr>
 *     <tr>
 *         <td>'T'HH:mm:ss'Z'</td>
 *         <td>T20:45:30Z</td>
 *         <td>The hour, minute and seconds in UTC</td>
 *     </tr>
 *     <tr>
 *         <td>'T'HH:mm:ssZZ</td>
 *         <td>T22:45:30+02:00</td>
 *         <td>The hour, minute and seconds at the given timezone</td>
 *     </tr>
 *     <tr>
 *         <td>'T'HH:mm:ss.SSS'Z'</td>
 *         <td>T20:45:30.995Z</td>
 *         <td>The hour, minute, seconds and milliseconds in UTC</td>
 *     </tr>
 *     <tr>
 *         <td>'T'HH:mm:ss.SSSZZ</td>
 *         <td>T22:45:30.995+02:00</td>
 *         <td>The hour, minute, seconds and milliseconds at the given timezone</td>
 *     </tr>
 * </table>
 *
 * @author Pierre Adam
 * @version 18.08.08
 * @since 18.08.08
 */
@Target({FIELD})
@Retention(RUNTIME)
@Form.Display(name = "format.date", attributes = {"patterns"})
public @interface JodaISO8601TimeFormat {

    /**
     * Explicit definition of the pattern use to print.
     *
     * @return the pattern use to print
     */
    String printPattern() default "'T'HH:mm:ss'Z'";
}
