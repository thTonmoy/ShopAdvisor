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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Please sign in");
        alert.setTitle("Registration");
        alert.setHeaderText("Registration Successful");
        alert.setGraphic(null);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene scene = new Scene(root);
            Stage signinStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            signinStage.setScene(scene);
            signinStage.show();
        }

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
