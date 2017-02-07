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
play.modules.enabled += "com.jackson42.play.form.databinders.JodaDataBinder"
```


### DateTime

By default the format of the DateTime is "yyyy-MM-dd". If you want to use a custom format you can use the annotation from Play ```Formats.DateTime``` or the custom annotation ```JodaDateTimeFormat```
The annotation ```JodaDateTimeFormat``` let you accept several format when parsing and you can also set an explicit format then printing the DateTime. 

##### Example

```java
import com.jackson42.play.form.databinders.joda.annotation.JodaDateTimeFormat;
import org.joda.time.DateTime;
import play.data.format.Formats;

public class MyForm {
    @Formats.DateTime(pattern = "yyyy-MM-dd HH:mm")
    public DateTime my_date;

    // Will use YYYY-MM-dd to parse and print
    @JodaDateTimeFormat(patterns = "YYYY-MM-dd")
    public DateTime single;

    // Will use yyyy-MM-dd'T'HH:mm:ss or yyyy-MM-dd'T'HH:mm:ss.SSS'Z' to parse
    // and yyyy-MM-dd'T'HH:mm:ss to print
    @JodaDateTimeFormat(patterns = {"yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"})
    public DateTime multiple;

    // Will use yyyy-MM-dd'T'HH:mm:ss or yyyy-MM-dd'T'HH:mm:ss.SSS'Z' to parse
    // and 'The' yyyy-MM-dd 'at' HH:mm:ss 'and' SSS 'ms' to print.
    @JodaDateTimeFormat(patterns = {"yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"}, printPattern = "'The' yyyy-MM-dd 'at' HH:mm:ss 'and' SSS 'ms'")
    public DateTime multipleAdvanced;
}
```


## License
This project is released under terms of the [MIT license](https://raw.githubusercontent.com/PierreAdam/PlayForm-JodaDataBinder/master/LICENSE).
