package com.example.demo.localDateTime.handleRequest.global;

import lombok.NonNull;
import org.springframework.lang.Nullable;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.regex.Pattern;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_TIME;

public abstract class LocalDateTimeUtils {

    private final static Pattern DATE_TIME_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}[T ]\\d{2}:\\d{2}.*$");

    private final static ZoneId LOCAL_ZONE_ID = ZoneId.systemDefault();

    private final static DateTimeFormatter LOCAL_DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .append(ISO_LOCAL_DATE)
            .optionalStart()
            .appendLiteral('T')
            .optionalEnd()
            .optionalStart()
            .appendLiteral(' ')
            .optionalEnd()
            .append(ISO_LOCAL_TIME)
            .toFormatter();

    private static boolean isTimestamp(@NonNull String resolver) {
        for (int i = 0; i < resolver.length(); i++) {
            char ch = resolver.charAt(i);
            if (!Character.isDigit(ch)) {
                return false;
            }
        }
        return resolver.length() == 10 || resolver.length() == 13;
    }

    private static boolean isOffsetDateTime(@NonNull String resolver) {
        for (int i = 10; i < resolver.length(); i++) {
            char ch = resolver.charAt(i);
            if (ch == 'Z' || ch == '+' || ch == '-') {
                return true;
            }
        }
        return false;
    }

    private static boolean isZonedDateTime(@NonNull String resolver) {
        return resolver.endsWith("]");
    }

    @NonNull
    private static String getISOTimeStr(@NonNull String resolver) {
        if (resolver.charAt(10) == ' ') {
            return resolver.substring(0, 10) + "T" + resolver.substring(11);
        } else {
            return resolver;
        }
    }

    @Nullable
    public static LocalDateTime convert(@Nullable String resolver) {
        if (resolver == null) {
            return null;
        }
        if (isTimestamp(resolver)) {
            Instant instant;
            if (resolver.length() == 10) {
                instant = Instant.ofEpochSecond(Long.parseLong(resolver));
            } else {
                instant = Instant.ofEpochMilli(Long.parseLong(resolver));
            }
            return LocalDateTime.ofInstant(instant, LOCAL_ZONE_ID);
        }
        if (DATE_TIME_PATTERN.matcher(resolver).matches()) {
            // compatibility RFC 3339
            resolver = getISOTimeStr(resolver);
            boolean isZoned = isZonedDateTime(resolver);
            boolean isOffset = isOffsetDateTime(resolver);
            if (isOffset && isZoned) {
                return ZonedDateTime.parse(resolver, DateTimeFormatter.ISO_ZONED_DATE_TIME)
                        .withZoneSameInstant(LOCAL_ZONE_ID)
                        .toLocalDateTime();
            } else if (isOffset) {
                return ZonedDateTime.parse(resolver, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                        .withZoneSameInstant(LOCAL_ZONE_ID)
                        .toLocalDateTime();
            } else {
                return LocalDateTime.parse(resolver, LOCAL_DATE_TIME_FORMATTER);
            }
        }
        return null;
    }

}
