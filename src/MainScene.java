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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.*;


public class MainScene implements Initializable{
    @FXML
    //this will declare a table for Staff object
    private TableView<Staff> table1;

    @FXML
    //creat different column display the method
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
    
    //create an ObservableList for the Staff object
    private ObservableList<Staff> staffList;
    int index = -1;
    
    //create Texfield for user to enter their input
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

    @FXML
    //create a choice box for enhanced categorizing
    ChoiceBox<String> staffCate;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {   //this is for testing only
        staffList = FXCollections.observableArrayList(
                new Staff( "Chau","Lop 1-A",3, 100000, 45, "Teacher"),
                new Staff( "Duc","Lop 2-B",4, 200000, 40, "Staff")
        );

        //link each column data to different attribute of object
        name1Column.setCellValueFactory(new PropertyValueFactory<Staff, String>("name1"));
        workunit1Column.setCellValueFactory(new PropertyValueFactory<Staff, String>("worku1"));
        basicsal1Column.setCellValueFactory(new PropertyValueFactory<Staff, Double>("basic1"));
        bonussal1Column.setCellValueFactory(new PropertyValueFactory<Staff, Double>("bonus1"));
        workingdayColumn.setCellValueFactory(new PropertyValueFactory<Staff, Integer>("day1"));
        salarycolumn.setCellValueFactory(new PropertyValueFactory<Staff, Double>("salary"));
        categoriColumn.setCellValueFactory(new PropertyValueFactory<Staff, String>("categori1"));
        
        //set the item: the ObservableList of Staff, for display
        table1.setItems(staffList);

        //set the item for choice box
        staffCate.getItems().addAll("Teacher", "Staff");
        staffCate.getSelectionModel().select(0);

        //wrap the ObservabelList in a FilteredList, initialy display all data
        FilteredList<Staff> filteredData = new FilteredList<>(staffList, b -> true);

        //set the filter predicate whenever there is a change in TextField: searchName
        searchName.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(staff ->{
                //if the TextField is empty, display all data
                if(newValue == null|| newValue.isEmpty()){
                    return true;
                }
                //lower case the value for easier searching, this will be temporary
                String lowerCaseFilter = newValue.toLowerCase();

                //compare the lowercase version of both value (the staff'name and the newValue)
                if (staff.getName1().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true; // if the values are alike, return the data
                }
                else
                return false; // if not, don't return the data
                
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

        //wrap the FilteredList in SortedList, this will sort out all the value that are not alike
        SortedList<Staff> sortedData = new SortedList<>(filteredData);

        //bind to the table use the comperator, otherwise, this won't have any effect
        sortedData.comparatorProperty().bind(table1.comparatorProperty());
        //set item of the table
        table1.setItems(sortedData);


    }

    // the actual add method
    public void add (ActionEvent e){
        
        Staff newStaff = new Staff();

        // from the value in the TextField, use set method to modify the object
        newStaff.setName1(nameText.getText());
        newStaff.setWorku1(workunitText.getText());
        newStaff.setBasic1(Double.parseDouble(basicsalText.getText()));
        newStaff.setBonus1(Double.parseDouble(bonussalText.getText()));
        newStaff.setDay1(Integer.parseInt(daysText.getText()));
        newStaff.setCategori1(staffCate.getValue());
        double money;
        if(newStaff.getCategori1() == "Teacher"){   
            money = newStaff.getBasic1() * 750000 + newStaff.getBonus1() + newStaff.getDay1() * 45000;
        }
        else{
            money = newStaff.getBasic1() * 750000 + newStaff.getBonus1() + newStaff.getDay1() * 200000;
        }
        newStaff.setSalary(money);

        //in the end add the the ObservableList for display
        staffList.add(newStaff);  
        //clear the TextField
        nameText.clear();
        workunitText.clear();
        basicsalText.clear();
        bonussalText.clear();
        daysText.clear();
    }

    //connected to the "Teacher" button, Tableview.fxml:30
    public void addTeacher (ActionEvent e)
    {
        Staff newStaff = new Staff();

        // from the value in the TextField, use set method to modify the object
        newStaff.setName1(nameText.getText());
        newStaff.setWorku1(workunitText.getText());
        newStaff.setBasic1(Double.parseDouble(basicsalText.getText()));
        newStaff.setBonus1(Double.parseDouble(bonussalText.getText()));
        newStaff.setDay1(Integer.parseInt(daysText.getText()));
        double money1 = newStaff.getBasic1() * 750000 + newStaff.getBonus1() + newStaff.getDay1() * 45000;
        newStaff.setSalary(money1);
        newStaff.setCategori1("Teacher");

        //in the end add the the ObservableList for display
        staffList.add(newStaff);  
        //clear the TextField
        nameText.clear();
        workunitText.clear();
        basicsalText.clear();
        bonussalText.clear();
        daysText.clear();
    }

    //connected to the staff button, Tableview.fxml: 31
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

        nameText.clear();
        workunitText.clear();
        basicsalText.clear();
        bonussalText.clear();
        daysText.clear();
        }
   
    // connected to the "Delete Imformation"  button, Tableview.fxml: 29
    public void delete (ActionEvent e)
    {   
        //on selected and after choose the delete button, remove the selected row out of the table 
        Staff selected1 = table1.getSelectionModel().getSelectedItem();
        staffList.remove(selected1);
        table1.getSelectionModel().clearSelection();
    }
    
    @FXML
    //stop selecting the row when click from the outside
    void clearSelected1(MouseEvent event)
    {
        nameText.clear();
        daysText.clear();
        workunitText.clear();
        basicsalText.clear();
        bonussalText.clear();
        table1.getSelectionModel().clearSelection();
    }

    @FXML
    // choose a row, this will also display all the value into the TextField, allow for fix() method to work
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

        // dong nay toi them vao 
        staffCate.setValue(categoriColumn.getCellData(index));
    }
    
    @FXML
    //more explaination in the  future readme.txt file 
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

    //connected to the "Listing Staff's Salary button", detail at Tableview.fxml:73
    //jump to the next window
    public void gonext(ActionEvent event) throws IOException {
        //make a new window
        Stage window = new Stage();
        //load the fxml design for the new window
        FXMLLoader loading = new FXMLLoader();
        loading.setLocation(getClass().getResource("Caculateview.fxml"));
        Parent root = loading.load();
        Scene scene = new Scene(root);
        
        //this scene will use SecondScene file as controller
        SecondScene controlScene = loading.getController();
        controlScene.getInfo(staffList);

        //this window won't be close until the user asked to 
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("List of Staff'salary");
        window.setScene(scene);
        window.showAndWait();
    }
    
    //connected to the "Total staff's salary", detail at Tableview.fxml:74
    // this method is alike to the previous one 
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
    
