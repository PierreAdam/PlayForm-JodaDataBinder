# PlayForm-JodaDataBinder

[![Build](https://img.shields.io/travis-ci/PierreAdam/PlayForm-JodaDataBinder.svg?branch=master&style=flat)](https://travis-ci.org/PierreAdam/PlayForm-JodaDataBinder)
[![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/PierreAdam/PlayForm-JodaDataBinder/master/LICENSE)

PlayForm-JodaDataBinder is a module for Play Framework 2.x which allows you to use Joda objects inside your form.
*****

## Build the module

```shell
$> mvn compile
$> mvn package
```

## Usage

Add the following line on your ```application.conf```

```
play.modules.enabled += "com.jackson42.play.form.databinders.JodaDataBinders"
```


### DateTime

By default the format of the DateTime is "yyyy-MM-dd". If you want to use a custom format you can use the annotation from Play ```Formats.DateTime```.


##### Example

```java
import org.joda.time.DateTime;
import play.data.format.Formats;

public class MyForm {
    @Formats.DateTime(pattern = "yyyy-MM-dd HH:mm")
    public DateTime my_date;
}
```


## License
This project is released under terms of the [MIT license](https://raw.githubusercontent.com/PierreAdam/PlayForm-JodaDataBinder/master/LICENSE).