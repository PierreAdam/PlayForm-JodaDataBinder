package com.jackson42.play.form.databinders.joda.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.text.SimpleDateFormat;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * JodaFormat.
 *
 * @author Pierre Adam
 * @version 17.02.07
 * @since 17.02.03
 */
@Target({FIELD})
@Retention(RUNTIME)
@play.data.Form.Display(name = "format.date", attributes = {"patterns"})
public @interface JodaDateTimeFormat {

    /**
     * Date pattern, as specified for {@link SimpleDateFormat}.
     * When importing the patterns are tried sequentially.
     * When exporting, the first pattern is used.
     *
     * @return the list date pattern
     */
    String patterns()[] default {};

    /**
     * Explicit definition of the pattern use to print.
     *
     * @return the pattern use to print
     */
    String printPattern() default "";
}
