/*
 * ConvMain
 * This code initiates the View and Controller.
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;


public class ConvMain extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("./ConvView.fxml"));
        
       
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Converter - Iuliia Buniak");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
