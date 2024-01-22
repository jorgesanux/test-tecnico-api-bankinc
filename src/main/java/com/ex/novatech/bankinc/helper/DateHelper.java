package com.ex.novatech.bankinc.helper;

import java.time.LocalDate;

public class DateHelper {
    public static LocalDate generateCurrentFirstDayDate(){
        LocalDate current = LocalDate.now();
        return LocalDate.of(current.getYear(), current.getMonth(), 1);
    }
}
