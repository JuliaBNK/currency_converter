/**
 * Iuliia Buniak
 * Controller for currency converter application
 */
 
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ChoiceBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ConvController implements Initializable {
    
    ObservableList<String>baseCurrencyList = FXCollections.observableArrayList("USD", "EUR", "CAD", "MXN", "RUB", "UAH", "JPY");
    
    // Tab1
   
    @FXML
    private TextField txtAmount;        // amount to be converted
    
    @FXML
    private TextField txtConverted;     // converted amount
    
    @FXML 
    private ChoiceBox cbBaseCurrency;   // base currency 
      
    @FXML 
    private ChoiceBox cbTargetCurrency; // in which currency amount should be converted
    
    @FXML
    private Button btnConvert;          // button to convert 
    
    @FXML
    private Button btnClear;            // button to clear the screen
    
    @FXML
    private TextField txtExchRate;      // display currenct exchange rate
    
    @FXML
    private TextField txtTime;          // display the time when the data were collected

    //Tab2
    
    @FXML
    private Label lblHint1;             // to display hint about time range
    
    @FXML
    private Label lblHint2;             // to display hint about date format
    
    @FXML
    private TextField txtDate;          // enter a date to check the rate this day
    
    @FXML 
    private ChoiceBox cbBaseCurrency2;  //base currency 
    
    @FXML 
    private ChoiceBox cbTargetCurrency2;// in which currency amount should be converted
   
    @FXML
    private Button btnDisplayRate;      // button to display exchange rate

    @FXML
    private TextField txtHistRate;      // historical exchange rate
    
    @FXML
    private void handleButtonAction(ActionEvent e) {
        // Create object to access the Model (create connection to the Model)
        ConvModel converter = new ConvModel();  
        //Has the Convert button been pressed?
        if (e.getSource() == btnConvert) {
            // Get amount
            String amount = txtAmount.getText();
            // Get base rate from drop down menu 
            String baseStr = String.valueOf(cbBaseCurrency.getValue());
            // Get target rate from drop down menu 
            String targetStr = String.valueOf(cbTargetCurrency.getValue());
                  
            // Use the model to get the weather information
            if (converter.getConv(baseStr, targetStr, amount)){
                txtConverted.setText(converter.getConvAmount());
                txtExchRate.setText("1 " + baseStr + " = " + converter.getCRate() + " " + targetStr);
                txtTime.setText("Last update: " + converter.getTime());
            }
            else{
                txtConverted.setText("Enter a number greater than 0");
                txtExchRate.setText("");
                txtTime.setText("");
            }
        }
        // Has the Clear button been pressed?
        else if (e.getSource() == btnClear){
            txtAmount.setText("");
            txtConverted.setText("");
            txtExchRate.setText("");
            txtTime.setText("");
            cbBaseCurrency.setValue("USD");
            cbTargetCurrency.setValue("EUR");
        
        }
        
        // Has the Display exchange rate button been pressed?
        else if(e.getSource() == btnDisplayRate) {
            // Get amount
            String date = txtDate.getText();
            // Get base rate from drop down menu 
            String baseStr = String.valueOf(cbBaseCurrency2.getValue());
            // Get target rate from drop down menu 
            String targetStr = String.valueOf(cbTargetCurrency2.getValue());
                  
            // Use the model to get the historical rate 
            if (converter.getHistory(baseStr, targetStr, date)){ 
                txtHistRate.setText("1 " + baseStr + " = " + converter.getHRate(targetStr) + " " + targetStr);
            }
            else{
                txtHistRate.setText("Invalid date");
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbBaseCurrency.setValue("USD");
        cbBaseCurrency.setItems(baseCurrencyList);
        cbTargetCurrency.setValue("EUR");
        cbTargetCurrency.setItems(baseCurrencyList);
        cbBaseCurrency2.setValue("USD");
        cbBaseCurrency2.setItems(baseCurrencyList);
        cbTargetCurrency2.setValue("EUR");
        cbTargetCurrency2.setItems(baseCurrencyList);
    }    
    
}
