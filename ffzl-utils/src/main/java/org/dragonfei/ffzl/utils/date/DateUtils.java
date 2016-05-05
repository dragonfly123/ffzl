package org.dragonfei.ffzl.utils.date;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

/**
 *Created by longfei on 16/4/20.
 */
public abstract class DateUtils {
    public final static String DATE_FORMAT_PATTERN = "yyyy-MM-dd";
    public final static String DATETIME_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * the time of now
     * @param pattern
     * @return
     */
    public static String now(String pattern){
        return DateFormatUtils.format(Calendar.getInstance(),pattern);
    }

    public static LocalDate dateNow(){
        return LocalDate.now();
    }


    /**
     * the date of now
     * @return
     */
    public static String now(){
        return now(DATE_FORMAT_PATTERN);
    }

    /**
     * the datetime of now
     * @return
     */
    public static String datetimeNow(){
        return now(DATETIME_FORMAT_PATTERN);
    }
}
