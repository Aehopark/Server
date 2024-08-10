package umc.aehopark.domain.delivery.function;

import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateCalc {
    public String formatDate(LocalDateTime pastDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return pastDateTime.format(formatter);

    }
    public static String formatDate2(LocalDateTime pastDateTime) {
        if(ObjectUtils.isEmpty(pastDateTime))
            return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy.MM.dd");
        return pastDateTime.format(formatter);
    }
}
