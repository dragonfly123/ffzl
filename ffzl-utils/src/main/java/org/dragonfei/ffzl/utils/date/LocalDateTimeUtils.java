package org.dragonfei.ffzl.utils.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * Created by longfei on 16/5/5.
 */
public abstract class LocalDateTimeUtils {

    public static LocalDateTime now(){
        return LocalDateTime.now();
    }

    public static LocalDateTime of(LocalDateTime localDateTime){
        return localDateTime;
    }

    public static LocalDateTime of(String datetime){
        return LocalDateTime.parse(datetime);
    }

    public static LocalDateTime of(String date,DateTimeFormatter formatter){
        return LocalDateTime.parse(date,formatter);
    }

    public static LocalDateTime ofDb(String date){
        return LocalDateTime.parse(date,TimeFormatter.DATETIME_PATTERN_DB);
    }

    public static LocalDateTime addDay(LocalDateTime localDateTime,int day){
        return localDateTime.plusDays(day);
    }

    public static LocalDateTime addDay(String localDate,int day){
        return LocalDateTime.parse(localDate).plusDays(day);
    }

    public static LocalDateTime firstOfdatetime(LocalDateTime localDateTime){
        return localDateTime.withHour(0).withMinute(0).withSecond(0).withNano(0);
    }
}
