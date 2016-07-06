package ph.com.globe.csvparser.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {
	public static boolean isValidDateFormat(String format, String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(value);           
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        if (date !=null){
            return true;
        }else{
        	return false;        	
        }
        
    }
    
    public static Date convertStringToDate(String format, String value){
    	Date date = null;
    	try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(value);
                  
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
		return date;
    	
    }
}
