/*
 * Name:     Iuliia Buniak
 *
 * Course:   CS-13, Spring 2019
 *
 * Date:     05/14/19
 *
 * Filename: ConvModel.java
 *
 * Purpose:  To handle all data access and manipulation in MVC model 
 *           for currency converter application
 */
import com.google.gson.JsonElement;
import com.google.gson.JsonParser; 
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javafx.scene.image.Image;
import java.net.URLEncoder;
import java.net.URL;
import java.util.Date; 
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;


import com.google.gson.*;

public class ConvModel{

    private JsonElement jse;
    final private String appid = "aed6470f5d96cb0d295dea3998bfe5c0";
    
    // method to convert amount in one currency (baseStr) to another currency (targetStr) in JSON format 
    public boolean getConv(String baseStr, String targetStr, String amount){
	    try {
		    // Construct API URL
			URL convURL = new URL("https://data.fixer.io/api/convert?access_key=" + appid 
                                   + "&from=" + baseStr + "&to=" + targetStr + "&amount=" + amount);
            // Open the URL
			InputStream is = convURL.openStream(); // throws an IOException
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			// Read the result into a JSON Element
			jse = new JsonParser().parse(br);
      
			// Close the connection
			is.close();
			br.close();
		}
        
		catch (java.io.UnsupportedEncodingException uee){
		    return false;	
		}
		catch (java.net.MalformedURLException mue){
		    return false;
		}
		catch (java.io.IOException ioe){
            return false;
		}
        catch (java.lang.NullPointerException npe){
            return false;
        }
        
        return isNumber(amount);
        
    }// end getConv()

    // Method to check if a user enter a number   
    
     private boolean isNumber(String str) {
        try {
            double d = Double.parseDouble(str);
            // check if number is 0, for 0 or negative number conversion is impossible
            if (d <= 0.0){
                return false; 
            }
        } catch (NumberFormatException nfe) {
            return false;
        }
        
        return true;
    }
    
    
    // method to get historical exchange rate for base (baseStr) and target currencies (targetStr) 
    // for certain date in JSON format 
    public boolean getHistory(String baseStr, String targetStr, String date){
	    try {
		    // Construct API URL
			URL convURL = new URL("http://data.fixer.io/api/" + date + "?access_key=" + appid + "&base=" + baseStr + "&symbols=" + targetStr);
            // Open the URL
			InputStream is = convURL.openStream(); // throws an IOException
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			// Read the result into a JSON Element
			jse = new JsonParser().parse(br);
      
			// Close the connection
			is.close();
			br.close();
		}
        
		catch (java.io.UnsupportedEncodingException uee){
            return false;	
		}
		catch (java.net.MalformedURLException mue){
		    return false;
		}
		catch (java.io.IOException ioe){
            return false;
		}
        catch (java.lang.NullPointerException npe){
            return false;
        }
        
        return validateDate(date);
        
    }    
    
    // Method to validate if user enters a date in a correct format
    private boolean validateDate(String dateStr) { 
        try{
            // create date "1999-01-01"; there is no historic data before this date 
            Date date1999 = new SimpleDateFormat("yyyy-MM-dd").parse("1999-01-01");
            // create today date; there is no historic date after this day
            Date today = new Date(); 
            // create date from input string
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            // check if date is in correct format
            dateFormat.setLenient(false);
            Date date = dateFormat.parse(dateStr.trim());
            //if yes, to chak if date is in within valid time range (later than 1999-01-01 
            // and to later than today
            if (date.compareTo(date1999) < 0 ) {
                return false;
            } else if(date.compareTo(date1999) >= 0 && date.compareTo(today) <= 0) {
                return true;
            }else{
               return false;
            }
    
        } catch(ParseException pe){
            return false;
        }
        
    }
              
    // Getting all necessary information from JSON
    
    // Get current rate 
    public String getCRate(){
        String rate = jse.getAsJsonObject().get("info").getAsJsonObject().get("rate").getAsString();
        return rate ;
    }    
    
    // Time of collecting the exchange rate
    public String getTime(){
        String time = jse.getAsJsonObject().get("info").getAsJsonObject().get("timestamp").getAsString();
        //converting from Unix time to regular time
        long unixTime = Long.parseLong(time);
        Date date = new Date (unixTime*1000L);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
        // Converting date into string 
        String strDate = dateFormat.format(date);
        return strDate;
    }    
    

    // Get converted amount
    public String getConvAmount(){
        String resultFmt;
        try{
            String resultStr = jse.getAsJsonObject().get("result").getAsString();
            double result = Double.parseDouble(resultStr);
            resultFmt = String.format("%.2f", result);
        } catch (NullPointerException npe) {
            resultFmt = jse.getAsJsonObject().get("error").getAsJsonObject().get("info").getAsString(); 
        }
        return resultFmt;
    } 
    
    // get historic rate
    public String getHRate (String targetStr){
        String result; 
        try{
            result = jse.getAsJsonObject().get("rates").getAsJsonObject().get(targetStr).getAsString();
        } catch (NullPointerException npe) {
            result = "No rate available for"; 
        }    
        return result;
    }  
 	
}//end class