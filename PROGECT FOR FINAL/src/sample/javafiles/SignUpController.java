package sample.javafiles;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController {

    @FXML
    private TextField signUpName;

    @FXML
    private TextField signUpLastname;
    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;
    @FXML
    private RadioButton checkBoxMale;

    @FXML
    private Button toAuthButton;

    @FXML
    private Button signUpButton;




    @FXML
    void initialize() {
        toAuthButton.setOnAction(actionEvent -> {
            openAuthPage();
        });

        signUpButton.setOnAction(actionEvent -> {
            String loginText = login_field.getText().trim();
            String loginPassword = password_field.getText().trim();
            if (!loginText.equals("")&& !loginPassword.equals("")){
                signUpNewUser();
                openAuthPage();
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Замечание");
                alert.setHeaderText(null);
                alert.setContentText("Логин и пароль пустые");
                alert.showAndWait();
                System.out.println("Login and password is empty");
            }
        });
    }

    private void signUpNewUser() {
        DatabaseHandler dbHandler = new DatabaseHandler();

        String firstName = signUpName.getText();
        String lastName = signUpLastname.getText();
        String userName = login_field.getText();
        String password = password_field.getText();
        String gender;
        if (checkBoxMale.isSelected())
            gender = "Мужской";
        else
            gender = "Женский";

        User user = new User(firstName,lastName,userName,password,gender);

        dbHandler.signUpUser(user);
    }
    private void openAuthPage(){
        signUpButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/views/sample.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
