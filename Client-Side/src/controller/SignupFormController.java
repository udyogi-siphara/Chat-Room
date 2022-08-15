/**
 * @author : Udyogi Siphara
 * Project Name: Chat-Room
 * Date        : 8/6/2022
 * Time        : 1:20 PM
 */

package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.User;
import util.ValidationUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class SignupFormController {

    public JFXTextField txtUserName;
    public Label lblLogin;
    public ImageView imgBack;
    public JFXButton btnSignup;
    public AnchorPane signUpContext;
    public Label lblAlert;
    public Label lblGoodAlert;

    public static ArrayList<User> users = new ArrayList<>();


    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();
    Pattern NamePattern = Pattern.compile("^[a-z]{3,}$");

    public void initialize(){

        storeValidations();
    }
    private void storeValidations() {
        map.put(txtUserName, NamePattern);

    }

    public void btnSignupOnAction(ActionEvent actionEvent) {
        if(!txtUserName.getText().equalsIgnoreCase("")){
            if (checkUser(txtUserName.getText())){
                User newUser =  new User();
                newUser.userName = txtUserName.getText();
                users.add(newUser);
                System.out.println(users);
                lblGoodAlert.setText("Registration Successfully!");
                clearAllText();

            }else{
                lblAlert.setText("User Name is Exist!");

            }
        }else{
           lblAlert.setText("Please Enter All Information!");

        }
    }

    private boolean checkUser(String username) {
        for(User user : users) {
            if(user.userName.equalsIgnoreCase(username)) {
                return false;
            }
        }
        return true;
    }

    private void clearAllText() {
        txtUserName.setText(null);

    }

    public void textFieldValidationOnAction(KeyEvent keyEvent) {
        Object response = ValidationUtil.validateJFXTextFields(map, btnSignup);
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof JFXTextField) {
                JFXTextField errorText = (JFXTextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {

            }
        }
    }



    public void lblLogin(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../view/LoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        signUpContext.getChildren().clear();
        signUpContext.getChildren().add(load);
    }

    public void imgBack(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../view/LoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        signUpContext.getChildren().clear();
        signUpContext.getChildren().add(load);
    }
}
