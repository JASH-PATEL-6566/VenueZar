package utils;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class IsDatePass {

    public static boolean checkDates(String storedDate, String todaysDate) throws NoSuchAlgorithmException {
        int todaysdate = Integer.parseInt(todaysDate.split("-")[2]) ;
        int storedate = Integer.parseInt(storedDate.split("-")[2]);
        int todaysmonth = Integer.parseInt(todaysDate.split("-")[1]);
        int storedmonth = Integer.parseInt(storedDate.split("-")[1]);
        int storedyear = Integer.parseInt(storedDate.split("-")[0]);
        int todaysyear = Integer.parseInt(todaysDate.split("-")[0]);
        
        if(todaysmonth <= storedmonth){
            if(todaysdate <= storedate){
                return false;
            }
        }
        return true;
    }
}
