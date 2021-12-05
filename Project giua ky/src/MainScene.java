import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.*;


public class MainScene implements Initializable{
    @FXML
    //declare a table view for Staff object
    private TableView<Staff> table1;

    /* For each and every column, declare a TableColumn<Class_name,Attribute_datatype>
    this will create a column for a property each */
    @FXML
    private TableColumn<Staff, Integer> workingdayColumn;
    
    @FXML
    private TableColumn<Staff, Double> basicsal1Column;

    @FXML
    private TableColumn<Staff, String> name1Column;

    @FXML
    private TableColumn<Staff, String> workunit1Column;

    @FXML
    private TableColumn<Staff, Double> bonussal1Column;

    @FXML
    private TableColumn<Staff, Double> salarycolumn;

    @FXML
    private TableColumn<Staff, String> categoriColumn;

    /* Create an ObservabelList<Class_name> object, this will act as a List that 
    store the object of the Class, including its attribute and method */
    private ObservableList<Staff> staffList;
    
    int index = -1;
    
    //in the GUI, TextField will be the place you input your data
    @FXML
    private TextField daysText;

    @FXML
    private TextField nameText;

    @FXML
    private TextField basicsalText;

    @FXML
    private TextField workunitText;

    @FXML
    private TextField bonussalText;

    @FXML
    private TextField salaryText;

    @FXML
    private TextField categoriText;

    @FXML
    private TextField searchCoeffi;

    @FXML
    private TextField searchName;

    @FXML
    private TextField searchWorku;

    @Override
    /* This method is gonna be called whenever the view load : App.java 
    line 18 */
    public void initialize(URL location, ResourceBundle resources)
    {
        staffList = FXCollections.observableArrayList(
                new Staff( "Chau","Lop 1-A",3, 100000, 45, "Teacher"),
                new Staff( "Duc","Lop 2-B",4, 200000, 40, "Staff")
        );

        name1Column.setCellValueFactory(new PropertyValueFactory<Staff, String>("name1"));
        
        workunit1Column.setCellValueFactory(new PropertyValueFactory<Staff, String>("worku1"));
        
        basicsal1Column.setCellValueFactory(new PropertyValueFactory<Staff, Double>("basic1"));
        
        bonussal1Column.setCellValueFactory(new PropertyValueFactory<Staff, Double>("bonus1"));
        
        workingdayColumn.setCellValueFactory(new PropertyValueFactory<Staff, Integer>("day1"));
        
        salarycolumn.setCellValueFactory(new PropertyValueFactory<Staff, Double>("salary"));
        
        categoriColumn.setCellValueFactory(new PropertyValueFactory<Staff, String>("categori1"));
        
        table1.setItems(staffList);
        //set the table editable
        table1.setEditable(true);

        name1Column.setCellFactory(TextFieldTableCell.forTableColumn());
        name1Column.setOnEditCommit(event ->{
            Staff newStaff = event.getRowValue();
            newStaff.setName1(event.getNewValue());
        });

        //wrap the whole ObservableList inside a FilteredList object,initialy keeping the list
        FilteredList<Staff> filteredData = new FilteredList<>(staffList, b -> true);

        //set the filter predicate whenever there is change in the search textfield
        searchName.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(staff ->{
                if(newValue == null|| newValue.isEmpty()){
                    return true; //keeping the list the way it is
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (staff.getName1().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true; //only the found are in the table view
                }
                else
                return false; // nothing will be appeared
                
            });
        });
        searchWorku.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(staff ->{
                if(newValue == null|| newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (staff.getWorku1().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                else
                return false;
                
            });
        });
        searchCoeffi.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(staff ->{
                if(newValue == null|| newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(staff.getBasic1()).indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                else
                return false;
                
            });
        });

        //wrap the FilteredList inside SortedList, help sorted out the object
        SortedList<Staff> sortedData = new SortedList<>(filteredData);

        //compare to the table, and bind both comparator
        sortedData.comparatorProperty().bind(table1.comparatorProperty());

        //add sorted and filtered data to the table
        table1.setItems(sortedData);

    }

    public void addTeacher (ActionEvent e)
    {
        Staff newStaff = new Staff();
        newStaff.setName1(nameText.getText());
        newStaff.setWorku1(workunitText.getText());
        newStaff.setBasic1(Double.parseDouble(basicsalText.getText()));
        newStaff.setBonus1(Double.parseDouble(bonussalText.getText()));
        newStaff.setDay1(Integer.parseInt(daysText.getText()));
        double money1 = newStaff.getBasic1() * 750000 + newStaff.getBonus1() + newStaff.getDay1() * 45000;
        newStaff.setSalary(money1);
        newStaff.setCategori1("Teacher");
        staffList.add(newStaff);
      
    }

    public void addStaff (ActionEvent e)
    {
        Staff newStaff = new Staff();
        newStaff.setName1(nameText.getText());
        newStaff.setWorku1(workunitText.getText());
        newStaff.setBasic1(Double.parseDouble(basicsalText.getText()));
        newStaff.setBonus1(Double.parseDouble(bonussalText.getText()));
        newStaff.setDay1(Integer.parseInt(daysText.getText()));
        double money2 = newStaff.getBasic1() * 750000 + newStaff.getBonus1() + newStaff.getDay1() * 200000;
        newStaff.setSalary(money2);
        newStaff.setCategori1("Staff");
        staffList.add(newStaff);
    }

    public void delete (ActionEvent e)
    {
        Staff selected1 = table1.getSelectionModel().getSelectedItem();
        staffList.remove(selected1);
        table1.getSelectionModel().clearSelection();
    }

    @FXML
    void getSelected1(MouseEvent event)
    {
        index= table1.getSelectionModel().getSelectedIndex();
        if(index<=-1){
            return;
        }
        nameText.setText(name1Column.getCellData(index).toString());
        workunitText.setText(workunit1Column.getCellData(index).toString());
        basicsalText.setText(basicsal1Column.getCellData(index).toString());
        bonussalText.setText(bonussal1Column.getCellData(index).toString());
        daysText.setText(workingdayColumn.getCellData(index).toString());
    }
    
    @FXML
    void fix(ActionEvent event) 
    {
        Staff selected1 = table1.getSelectionModel().getSelectedItem();
        if (staffList.remove(selected1)){
        table1.getSelectionModel().clearSelection(0);
        Staff newStaff = new Staff();
        newStaff.setName1(nameText.getText());
        newStaff.setWorku1(workunitText.getText());
        newStaff.setBasic1(Double.parseDouble(basicsalText.getText()));
        newStaff.setBonus1(Double.parseDouble(bonussalText.getText()));
        newStaff.setDay1(Integer.parseInt(daysText.getText()));
        if (selected1.getCategori1() == "Teacher")
        {
            double money1 = newStaff.getBasic1() * 750000 + newStaff.getBonus1() + newStaff.getDay1() * 45000;
            newStaff.setSalary(money1);
            newStaff.setCategori1("Teacher");
        }
        else 
        {
            double money2 = newStaff.getBasic1() * 750000 + newStaff.getBonus1() + newStaff.getDay1() * 200000;
            newStaff.setSalary(money2);
            newStaff.setCategori1("Staff");
        }
        staffList.add(newStaff);
        table1.getSelectionModel().clearSelection();
        }
    }

    public void gonext(ActionEvent event) throws IOException {
        Stage window = new Stage();
        FXMLLoader loading = new FXMLLoader();
        loading.setLocation(getClass().getResource("Caculateview.fxml"));
        Parent root = loading.load();
        Scene scene = new Scene(root);
        
        SecondScene controlScene = loading.getController();
        controlScene.getInfo(staffList);

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("List of Staff'salary");
        window.setScene(scene);
        window.showAndWait();
    }

    public void gonext2(ActionEvent event) throws IOException {
        Stage window = new Stage();
        FXMLLoader loading = new FXMLLoader();
        loading.setLocation(getClass().getResource("Totalview.fxml"));
        Parent root = loading.load();
        Scene scene = new Scene(root);

        ThirdScene controlscene = loading.getController();
        controlscene.addmoney(staffList);

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Caculate result");
        window.setScene(scene);
        window.showAndWait();
    }

}
    
