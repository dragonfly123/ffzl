package org.dragonfei.ffzl.utils.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalUnit;

/**
 * Created by longfei on 16/5/5.
 */
public abstract class LocalDateUtils {


    public static LocalDate now(){
        return LocalDate.now();
    }

    public static LocalDate of(LocalDate localDate){
        return localDate;
    }

    public static LocalDate of(String date){
        return LocalDate.parse(date);
    }

    public static LocalDate of(String date,DateTimeFormatter formatter){
        return LocalDate.parse(date,formatter);
    }

    public static LocalDate ofDb(String date){
        return LocalDate.parse(date,TimeFormatter.DATETIME_PATTERN_DB);
    }

    public static LocalDate addDay(LocalDate localDate,int day){
        return localDate.plusDays(day);
    }

    public static LocalDate addDay(String localDate,int day){
        return LocalDate.parse(localDate).plusDays(day);
    }


    public static void main(String[] args) {

    }



}
