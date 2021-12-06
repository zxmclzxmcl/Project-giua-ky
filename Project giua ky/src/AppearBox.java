import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;


public class AppearBox {

    static boolean answer;
    
    public static boolean choice(String title, String message){
        Stage window = new Stage() ;

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);
        
        //creat 2 button
        Button yesButton = new Button("Yes");
        Button noButton = new Button("no");

        //add functionality for button
        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });

        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);

        window.setScene(scene);
        //show the stage and wait for it to be closed, until is closed
        window.showAndWait();

        return answer;
    }
}
