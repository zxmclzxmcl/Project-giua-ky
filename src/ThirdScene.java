import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.TextField;

public class ThirdScene implements Initializable {

    @FXML
    public TextField moneyText;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void btnback(ActionEvent event) throws IOException {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }

    public void addmoney(ObservableList<Staff> staffList){
        int i;
        Staff person = new Staff();
        double tong = 0;
        for (i = 0; i < staffList.size() ; i++) {
            person = staffList.get(i);
            tong = tong + person.getSalary();
        }
        moneyText.setText(String.valueOf(tong));
    }
}
