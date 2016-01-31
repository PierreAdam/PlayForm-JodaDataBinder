package com.jackson42.play.form.databinders;

import com.google.inject.AbstractModule;
import com.jackson42.play.form.databinders.joda.DateTimeBinders;
import org.joda.time.DateTime;
import play.data.format.Formatters;

/**
 * JodaDataBinders.
 *
 * @author Pierre Adam
 * @version 16.01
 * @since 16.01
 */
public class JodaDataBinders extends AbstractModule {

    @Override
    protected void configure() {
        Formatters.register(DateTime.class, new DateTimeBinders.DateTimeSimpleFormatter("yyyy-MM-dd"));
        Formatters.register(DateTime.class, new DateTimeBinders.DateTimeAnnotationFormatter());
    }
}
