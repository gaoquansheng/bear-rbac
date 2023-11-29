package com.bear.rbac.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private static final DateTimeFormatter YYYY_MM_DD = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String now() {

        return YYYY_MM_DD.format(LocalDate.now());
    }
}
