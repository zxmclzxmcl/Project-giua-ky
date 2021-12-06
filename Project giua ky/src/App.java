import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
public class App extends Application 
{
    Stage window;
    @Override
    //this method will be called when lauch
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        
        //import the scene design from the Tableview.fxml
        Parent root = FXMLLoader.load(getClass().getResource("Tableview.fxml"));
        //created a new scene using the constructor
        Scene scene = new Scene(root);
        
        //set the title of the window
        window.setTitle("Staff Management Program");
        //set the initial scene
        window.setScene(scene);
        //show the window
        window.show();
    }

    public static void main(String[] args) {
        //this method will jump to the start method above
        launch(args);
    }

}