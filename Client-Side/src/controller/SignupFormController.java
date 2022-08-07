/**
 * @author : Udyogi Siphara
 * Project Name: Chat-Room
 * Date        : 8/6/2022
 * Time        : 1:20 PM
 */

package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
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
    public FontAwesomeIconView icnEye;
    public JFXPasswordField pwdPassword;
    public JFXTextField txtPassword;
    public JFXTextField txtFullName;
    public JFXTextField txtEmail;
    public JFXTextField txtPhoneNumber;
    public ImageView imgBack;
    public JFXButton btnSignup;
    public AnchorPane signUpContext;
    public Label lblAlert;
    public Label lblGoodAlert;

    public static ArrayList<User> users = new ArrayList<>();

    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();
    Pattern NamePattern = Pattern.compile("^[A-z]{5,}$");
    Pattern userNamePattern = Pattern.compile("^[A-z0-9]{6,10}$");
    Pattern emailPattern = Pattern.compile("[a-z0-9]{3,}@(gmail|yahoo).com");
    Pattern phonePattern = Pattern.compile("^(\\+\\d{1,3}[- ]?)?\\d{10}$");


    LinkedHashMap<JFXPasswordField, Pattern> map1 = new LinkedHashMap<>();
    Pattern passwordPattern = Pattern.compile("[A-z0-9]{8,}");

    public void initialize(){
        storeValidations();
    }
    private void storeValidations() {
        map.put(txtFullName, NamePattern);
        map.put(txtUserName, userNamePattern);
        map.put(txtEmail, emailPattern);
        map.put(txtPhoneNumber, phonePattern);
        map1.put(pwdPassword, passwordPattern);

    }

    public void btnSignupOnAction(ActionEvent actionEvent) {
        if(!txtFullName.getText().equalsIgnoreCase("")&&
           !txtUserName.getText().equalsIgnoreCase("")&&
           !pwdPassword.getText().equalsIgnoreCase("")&&
           !txtEmail.getText().equalsIgnoreCase("")&&
           !txtPhoneNumber.getText().equalsIgnoreCase("")){

            if (checkUser(txtUserName.getText())){
                if (checkPassword(pwdPassword.getText())){
                    User newUser =  new User();
                    newUser.userName = txtUserName.getText();
                    newUser.fullName = txtFullName.getText();
                    newUser.password = pwdPassword.getText();
                    newUser.email = txtEmail.getText();
                    newUser.phoneNo = txtPhoneNumber.getText();

                    users.add(newUser);
                    System.out.println(users);
                    lblGoodAlert.setText("Registration Successfully!");
                    clearAllText();
                }else{
                    lblAlert.setText("Password is Exist!");
                }
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

    private boolean checkPassword(String password) {
        for(User user : users) {
            if(user.password.equalsIgnoreCase(password)) {
                return false;
            }
        }
        return true;
    }

    private void clearAllText() {
        txtUserName.setText(null);
        txtFullName.setText(null);
        pwdPassword.setText(null);
        txtEmail.setText(null);
        txtPhoneNumber.setText(null);

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

    public void eyeClickOnAction(MouseEvent mouseEvent) {
        if(icnEye.getGlyphName().equals("EYE_SLASH")){ // must show password
            icnEye.setGlyphName("EYE");

            txtPassword.setText(pwdPassword.getText()); //copy PwdPassword data to  txtPW
            pwdPassword.setVisible(false);  //PWField hidden
            txtPassword.setVisible(true);   //txtField Shown

        }else if(icnEye.getGlyphName().equals("EYE")){  // must hide  password
            icnEye.setGlyphName("EYE_SLASH");

            pwdPassword.setText(txtPassword.getText());
            txtPassword.setVisible(false); //txtField hide
            pwdPassword.setVisible(true);  //PWField shown

        }
    }

    public void passwordFieldValidationOnAction(KeyEvent keyEvent) {
        Object response = ValidationUtil.validateJFXPasswordField(map1, btnSignup);
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof JFXPasswordField) {
                JFXPasswordField errorText = (JFXPasswordField) response;
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
