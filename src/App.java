
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
public class App extends Application 
{
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Tableview.fxml"));
        Scene scene = new Scene(root);
        //set title of the scene
        stage.setTitle("Staff Management Program");
        //set scene will show when open the program
        stage.setScene(scene);
        //show the scene
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}