package org.dragonfei.ffzl.utils.date;

import org.dragonfei.ffzl.utils.string.StringUtils;

import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;

/**
 * Created by longfei on 16/5/5.
 */
public abstract class TimeFormatter {

    public final static DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ISO_DATE;
    public final static DateTimeFormatter DATETIME_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public final static DateTimeFormatter DATETIME_PATTERN_DB = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");

}
