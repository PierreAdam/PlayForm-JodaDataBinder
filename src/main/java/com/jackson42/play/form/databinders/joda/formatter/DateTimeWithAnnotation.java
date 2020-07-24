/*
 * Copyright (C) 2014 - 2020 PayinTech, SAS - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.jackson42.play.form.databinders.joda.formatter;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import play.data.format.Formatters;

import java.lang.annotation.Annotation;

/**
 * DateTimeWithAnnotation.
 *
 * @author Pierre Adam
 * @since 20.07.24
 */
public abstract class DateTimeWithAnnotation<A extends Annotation> extends Formatters.AnnotationFormatter<A, DateTime> implements DateTimeParser {

    /**
     * The time zone used for the inputs.
     */
    protected final DateTimeZone inputTimeZone;

    /**
     * The time zone used for the outputs.
     */
    protected final DateTimeZone outputTimeZone;

    /**
     * Instantiates a new Date time with annotation.
     *
     * @param inputTimeZone  the input time zone
     * @param outputTimeZone the output time zone
     */
    public DateTimeWithAnnotation(final DateTimeZone inputTimeZone, final DateTimeZone outputTimeZone) {
        this.inputTimeZone = inputTimeZone;
        this.outputTimeZone = outputTimeZone;
    }
}
