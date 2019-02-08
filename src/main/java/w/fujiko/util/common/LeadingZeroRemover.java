package w.fujiko.util.common;

public class LeadingZeroRemover{

    public static String remove(String number){
        return number.replaceFirst("^0+(?!$)", "");
    }

    public static String remove(int number){
        return String.valueOf(number)
                     .replaceFirst("^0+(?!$)", "");
    }
    
}