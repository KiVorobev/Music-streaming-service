package com.racers.euphmusic.utils;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.racers.euphmusic.utils.StringUtils.*;

@UtilityClass
public class LocalDateTimeUtils {

    private static final DateTimeFormatter dateTimeFormatter = java.time.format.DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_FORMAT_PATTERN);

    public static String format(LocalDateTime localDateTime) {
        return dateTimeFormatter.format(localDateTime);
    }
}
