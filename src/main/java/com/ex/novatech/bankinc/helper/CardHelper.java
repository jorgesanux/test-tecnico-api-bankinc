package com.ex.novatech.bankinc.helper;

import java.time.Instant;
import java.time.LocalDate;

public class CardHelper {
    public static int VALIDITY_YEARS = 3;
    public static String generateRandomCardNumber(String productId){
        long milliSeconds = Instant.now().toEpochMilli(); //Tiene una longitud de 13 digitos
        String payload = String.valueOf(milliSeconds).substring(3);
        return productId + payload;
    }

    public static LocalDate generateCardExpirationDate(){
        LocalDate currentDate = LocalDate.now();
        return LocalDate.of(
                currentDate.getYear() + CardHelper.VALIDITY_YEARS,
                currentDate.getMonth(),
                currentDate.getDayOfYear()
        );
    }
}
