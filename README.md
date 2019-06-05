# currency_converter
This project is a graphical currency converter application written in Java. This application has two main features:
1) it takes a number of a certain amount of money in one currency and convert it into corresponding amount into another currency using current exchange rate retrieved from Fixer.io. 
2) it takes a date and display what exchange rate was effective that day for two selected currencies. 

Files

The following files are used to create this application:
1)ConvView.fxml – View for project
2)ConvController.java – Controller for project
3)ConvModel.java – Model for project
4)ConvMain.java – Contains public static void main to start the application
5)ConvModelTest.java – Junit tests for ConvModel.java
6)Gson-2.2.4.jar – Required to parse JSON code returned 
7)Hamcrest-core-1.3.jar – Required to run JUnit tests
8)junit-4.12.jar – Required to run JUnit tests

Operation

In Converter tab enter any number greater than 0 in the amount field, choose base currency and currency in which amount should be converted from two corresponding drop down lists, and press the Convert button. Converted amount, real-time exchange rate and date and time the given rate was collected will be displayed. To reset the view press Clear button.
In Historical data tab enter a date starting from 1999-01-01 in the format YYYY-MM-DD into the date field, choose base currency and currency for which historical rate will be requested and press the Display exchange rate button. Historical exchange rate will be displayed.















