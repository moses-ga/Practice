package io.kenzo.learning.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public final class Utils {

    private static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private Utils() {
    }

    public static String formatDateGeneral(LocalDateTime date) {
        return date == null ? null : DATE_TIME.format(date);
    }

    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, DATE);
    }

    public static LocalDateTime parseDateTime(String date) {
        return LocalDateTime.parse(date, DATE_TIME);
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public static String generateOtp() {
        int otp = (int) (Math.random() * 9000) + 1000; // 4-digit OTP
        return String.valueOf(otp);
    }

    public static boolean isNumeric(String value) {
        if (value == null) return false;
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static Map<String, Object> sendMapResponse(Integer action, String message, Object data) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("action", action);
        response.put("message", message);
        response.put("data", data);
        return response;
    }
}
