package utility;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {

    public static boolean isOverlappedTerm(LocalDateTime startTerm1, LocalDateTime endTerm1,
                                           LocalDateTime startTerm2, LocalDateTime endTerm2) {
        if (endTerm2.compareTo(startTerm1) <= 0) {
            return false;
        }
        if (startTerm2.compareTo(endTerm1) >= 0) {
            return false;
        }
        return true;
    }

    public static String convertDateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return date.format(formatter);
    }

    public static String convertDateTimeToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        return dateTime.format(formatter);
    }

    public static LocalDate convertStringToDate(String stringDate)
            throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/M/d");
        return LocalDate.parse(stringDate, formatter);
    }

    public static LocalDateTime convertStringToDateTime(String dateTimeStr)
            throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/M/d H:m");
        return LocalDateTime.parse(dateTimeStr, formatter);
    }
}
