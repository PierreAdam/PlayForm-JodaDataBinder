package com.jackson42.play.form.databinders;

import play.api.Configuration;
import play.api.Environment;
import play.api.inject.Binding;
import play.api.inject.Module;
import scala.collection.Seq;

/**
 * JodaDataBinder.
 *
 * @author Pierre Adam
 * @author Thibault Meyer
 * @version 16.05.16
 * @since 16.01.31
 */
public class JodaDataBinder extends Module {

    @Override
    public Seq<Binding<?>> bindings(final Environment environment, final Configuration configuration) {
        return seq(bind(JodaDataModule.class).toSelf());
    }
}
