package com.example.demo.localDateTime.basic;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

public class LocalDateTimeApplication {

    //系統時區
    private static final ZoneId ZONE = ZoneId.systemDefault();
    private static final ZoneId TW_ZONE = ZoneId.of("Asia/Taipei");

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String YYYY_MM_DD = "yyyy-MM-dd";


    //-------------->Local* To String
    public static String nowLocalDateTimeToString(String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime now = LocalDateTime.now();
        return now.format(dateTimeFormatter);
    }

    public static String localDateTimeToString(LocalDateTime localDateTime, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(dateTimeFormatter);
    }

    public static String localDateToString(LocalDate localDate, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return localDate.format(dateTimeFormatter);
    }

    //----------------->Date To Local*
    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZONE);
        return localDateTime;
    }

    public static LocalDate dateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZONE);
        return localDateTime.toLocalDate();
    }

    public static LocalTime dateToLocalTime(Date date) {
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZONE);
        return localDateTime.toLocalTime();
    }


    //---------------->Local* to date
    public static Date localDateToDate(LocalDate localDate) {
        Instant instant = localDate.atStartOfDay().atZone(ZONE).toInstant();
        return Date.from(instant);
    }

    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        Instant instant = localDateTime.atZone(ZONE).toInstant();
        return Date.from(instant);
    }


    //---------------->String to Local*
    public static LocalDate stringToLocalDate(String time) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern(YYYY_MM_DD);
        return LocalDate.parse(time, f);
    }

    public static LocalDateTime stringToLocalDateTime(String time) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS);
        return LocalDateTime.parse(time, f);
    }


    //---------------- 本周開始 或 結束
    public static LocalDateTime getWeekFirstDay(LocalDateTime localDateTime) {
        TemporalAdjuster FIRST_OF_WEEK =
                TemporalAdjusters.ofDateAdjuster(localDate -> localDate.minusDays(localDate.getDayOfWeek().getValue()- DayOfWeek.MONDAY.getValue()));
        return localDateTime.with(FIRST_OF_WEEK);
    }

    public static LocalDateTime getWeekLastDay(LocalDateTime localDateTime) {
        TemporalAdjuster LAST_OF_WEEK =
                TemporalAdjusters.ofDateAdjuster(localDate -> localDate.plusDays(DayOfWeek.SUNDAY.getValue() - localDate.getDayOfWeek().getValue()));
        return localDateTime.with(LAST_OF_WEEK);
    }


    public static void application() {
        //現在時間
        LocalDate now = LocalDate.now();
        //年月日
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        //特定日期
        LocalDate date = LocalDate.of(2022, 12, 24);
        //日期是否相等
        boolean equals = now.equals(date);
        //時間增減
        LocalDate localDate = date.plusWeeks(1).plusDays(-1);
        //比較大小
        boolean isAfter = now.isAfter(date);
        //閏年
        boolean isLeapYear = date.isLeapYear();
        //計算LocalDate
        Period period = Period.between(now, date);
        int months = period.getMonths();
        //計算LocalDateTime
        Duration between = Duration.between(LocalDateTime.now(), LocalDateTime.now().plusHours(5));
        long seconds = between.toSeconds();
        //LocalDate -> LocalDateTime (指定時間)
        LocalDateTime assignDateTime = date.withMonth(3).atTime(23, 59, 59);
        //轉換
        LocalDate toLocalDate = LocalDateTime.now().toLocalDate();
        LocalDateTime toLocalDateTime = LocalDate.now().atStartOfDay();
        //星期幾
        int dayOfWeek = LocalDateTime.now().getDayOfWeek().getValue();

        // 本月結束日期
        LocalDateTime startMonth = LocalDateTime.now().with(TemporalAdjusters.lastDayOfMonth());


    }

}
