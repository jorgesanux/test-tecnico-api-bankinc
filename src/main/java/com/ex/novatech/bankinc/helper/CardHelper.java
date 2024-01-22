package com.ex.novatech.bankinc.helper;

import com.ex.novatech.bankinc.dto.CardBalanceDTO;
import com.ex.novatech.bankinc.model.Card;

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
        LocalDate currentDate = DateHelper.generateCurrentFirstDayDate();
        return currentDate.plusYears(CardHelper.VALIDITY_YEARS);
    }

    public static LocalDate mapCardExpirationDate(Card card){
        return LocalDate.of(card.getExpirationYear(), card.getExpirationMonth(), 1);
    }

    public static CardBalanceDTO mapCardToBalanceDTO(Card card){
        return new CardBalanceDTO(card.getId(), card.getBalance());
    }
}
