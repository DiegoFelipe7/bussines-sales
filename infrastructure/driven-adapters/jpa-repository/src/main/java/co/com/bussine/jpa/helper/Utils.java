package co.com.bussine.jpa.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {
    private Utils() {
        throw new IllegalStateException("Utility class");
    }

    public static String parseLocalDateTime(LocalDateTime data){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return data.format(formatter);
    }
}
