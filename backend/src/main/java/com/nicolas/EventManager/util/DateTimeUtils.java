package com.nicolas.EventManager.util;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTimeUtils {
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm", Locale.US);
}
