package com.devsu.demo.utility;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Utility {
    public static LocalDate convertToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
