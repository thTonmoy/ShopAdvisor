package ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import data.*;

/**
 * FXML Controller class
 *
 */
public class SignupController implements Initializable {

    @FXML
    private PasswordField passwordInput;
    @FXML
    private Button registerButton;
    @FXML
    private TextField userNameInput;
    @FXML
    private ChoiceBox typeChoiceBox;
    @FXML
    private TextField fullNameInput;
    @FXML
    private TextField emailInput;
    @FXML
    private TextField addressInput;
  
    @FXML
    private void registerButtonAction(ActionEvent event) throws IOException {
        registerUser();
        if(CommonControll.ConfirmationDialogueOK("Please sign in", "Registration", "Registration Successful")){
            CommonControll.changeScreen(FXMLLoader.load(getClass().getResource("login.fxml")), (Stage) ((Node) event.getSource()).getScene().getWindow());
        }

    }
    
    private void registerUser(){
        FileOperations fop = new FileOperations();
        String file;
        if (typeChoiceBox.getValue().equals("Buyer")){
            file = "customer.txt";
        }
        else{
            file = "sales.txt";
        }
        if(isUniqueUser(userNameInput.getText())){
            fop.addRecord(userNameInput.getText(),passwordInput.getText(),(String) typeChoiceBox.getValue(),fullNameInput.getText(),emailInput.getText(),addressInput.getText());
            //fop.addRecord("customer.txt",userNameInput.getText(),passwordInput.getText());
            LoginController.userInfo.updateMap(userNameInput.getText(),passwordInput.getText(),(String) typeChoiceBox.getValue(),fullNameInput.getText(),emailInput.getText(),addressInput.getText());
        }
        
    }
    private boolean isUniqueUser(String usrnm){
        if(LoginController.userInfo.recordMap.containsKey(usrnm)){
            System.out.println("Username not unique");
            return false;
        }
        return true;
    }
    @FXML
    private Button GoBackButton;
    @FXML
    void GoBackButtonAction(ActionEvent event) throws IOException {
        CommonControll.changeScreen(FXMLLoader.load(getClass().getResource("login.fxml")), (Stage) ((Node) event.getSource()).getScene().getWindow());
    }    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        typeChoiceBox.setItems(FXCollections.observableArrayList("Buyer", "Seller"));
        typeChoiceBox.setValue("Buyer");
    }

}
