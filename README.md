# PlayForm-JodaDataBinder

[![Build Status](https://travis-ci.com/PierreAdam/PlayForm-JodaDataBinder.svg?branch=master)](https://travis-ci.com/PierreAdam/PlayForm-JodaDataBinder)
[![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/PierreAdam/PlayForm-JodaDataBinder/master/LICENSE)

PlayForm-JodaDataBinder is a module for Play Framework 2.x which allows you to use Joda DateTime object inside your form to retrieve the Date or Time !
*****

## Build the module

```shell
$> mvn compile
$> mvn package
```


## How to use the module

In your ```build.sbt``` file, you need to add a resolver to jitpack and the dependency for the module. This translate to the following lines.

```sbtshell
resolvers += "jitpack" at "https://jitpack.io"

libraryDependencies += "com.github.PierreAdam" % "PlayForm-JodaDataBinder" % "release~20.09"
```

### Configuration

By default, nothing is required in the play file application.conf. But you can however customize the behavior of the plugin using the following configuration.

```
playForm {
  jodaDataBinder {
    # All the parsed DateTime will be converted to the timezone bellow.
    # Can accept "UTC" or a standard timezone representation like "Europe/Paris"
    inputTimezone = "UTC"

    # All the outputed DateTime will be converted to this timezone before being displayed.
    # If the value "LOCAL" is used, the DateTime WILL NOT BE CONVERTED FOR DISPLAY !
    # Can accept LOCAL disabling the feature, "UTC" or a standard timezone representation like "Europe/Paris"
    outputTimezone = "LOCAL"
  }
}
```


### DateTime

By default the format of the DateTime is "yyyy-MM-dd". If you want to use a custom format you can use the annotation from Play ```Formats.DateTime``` or the custom annotation ```JodaDateTimeFormat```
The annotation ```JodaDateTimeFormat``` let you accept several format when parsing and you can also set an explicit format then printing the DateTime. 

Alternatively you can use ```JodaISO8601DateTimeFormat``` or ```JodaISO8601TimeFormat```. These annotations don't take any parameters but accept a given number of format compliant with the ISO-8601. 

#### Important to notice !

All the dates and times are by default retrieved in UTC. If the timezone was explicit in the input, the dates and time will be converted to UTC.
Take a look at the tests to better understand the behavior of Joda and this plugin.

##### Example

```java
import com.jackson42.play.form.databinders.joda.annotation.JodaDateTimeFormat;
import com.jackson42.play.form.databinders.joda.annotation.JodaISO8601TimeFormat;
import com.jackson42.play.form.databinders.joda.annotation.JodaISO8601DateTimeFormat;
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
    @JodaDateTimeFormat(patterns = {"yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"},
                        printPattern = "'The' yyyy-MM-dd 'at' HH:mm:ss 'and' SSS 'ms'")
    public DateTime multipleAdvanced;
    
    // Will take all the possible formats defined by the annotation JodaISO8601DateTimeFormat.
    @JodaISO8601DateTimeFormat
    public DateTime dateTimeIso8601;
    
    // Will take all the possible formats defined by the annotation JodaISO8601TimeFormat.
    @JodaISO8601TimeFormat
    public DateTime timeIso8601;
}
```


### ISO-8601 annotations

If you want to build an API or simply a route that takes some free arguments from the user, you can use the annotions ```JodaISO8601DateTimeFormat``` or ```JodaISO8601TimeFormat```. These annotations allow a flexible format from the user. Not all ISO-8601 format will work but the most common / most reasonable ones are available.

##### @JodaISO8601DateTimeFormat

| Joda Format                  | Example                       | Comment                                                   |
|------------------------------|-------------------------------|-----------------------------------------------------------|
| YYYY-MM-dd                   | 1985-01-20                    | The year, the month and the day                           |
| yyyy-MM-dd'T'HH:mm:ssZZ      | 1985-01-20T20:45:30Z          | The full date in UTC                                      |
| yyyy-MM-dd'T'HH:mm:ssZZ      | 1985-01-20T22:45:30+02:00     | The full date at the given timezone                       |
| yyyy-MM-dd'T'HH:mm:ss.SSSZZ  | 1985-01-20T20:45:30.995Z      | The full date with the milliseconds in UTC                |
| yyyy-MM-dd'T'HH:mm:ss.SSSZZ  | 1985-01-20T22:45:30.995+02:00 | The full date with the milliseconds at the given timezone |

##### @JodaISO8601TimeFormat

| Joda Format        | Example             | Comment                                                          |
|--------------------|---------------------|------------------------------------------------------------------|
| 'T'HH:mm:ssZZ      | T20:45:30Z          | The hour, minute and seconds in UTC                              |
| 'T'HH:mm:ssZZ      | T22:45:30+02:00     | The hour, minute and seconds at the given timezone               |
| 'T'HH:mm:ss.SSSZZ  | T20:45:30.995Z      | The hour, minute, seconds and milliseconds in UTC                |
| 'T'HH:mm:ss.SSSZZ  | T22:45:30.995+02:00 | The hour, minute, seconds and milliseconds at the given timezone |


## License
This project is released under terms of the [MIT license](https://raw.githubusercontent.com/PierreAdam/PlayForm-JodaDataBinder/master/LICENSE).
