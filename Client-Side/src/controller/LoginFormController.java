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
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.User;
import util.ValidationUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class LoginFormController {

    public JFXTextField txtUserName;
    public Label lblSignup;
    public FontAwesomeIconView icnEye;
    public JFXPasswordField pwdPassword;
    public JFXTextField txtPassword;
    public JFXButton btnLogin;
    public AnchorPane loginContext;
    public Label lblIncorrect;
    public Label lblSuccess;

    public static String username,password;
    public static ArrayList<User> users = SignupFormController.users;
    public static ArrayList<User> loggedInUser = new ArrayList<>();

    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();
    Pattern usernamePattern = Pattern.compile("^[A-z0-9]{6,10}$");
    LinkedHashMap<JFXPasswordField, Pattern> map1 = new LinkedHashMap<>();
    Pattern passwordPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$");

    public void initialize(){
        txtPassword.setVisible(false);
        storeValidation();
    }
    private void storeValidation() {
        map.put(txtUserName, usernamePattern);
        map1.put(pwdPassword, passwordPattern);

    }
    public void btnLoginOnAction(ActionEvent actionEvent) {
        System.out.println(users);
        username = txtUserName.getText();
        password = pwdPassword.getText();
        boolean login = false;
        for (User ReqUser : users) {
            if (ReqUser.userName.equalsIgnoreCase(username) && ReqUser.password.equalsIgnoreCase(password)) {
                login = true;
                loggedInUser.add(ReqUser);
                System.out.println(ReqUser.userName);
                break;
            }
        }
        if (login) {
            lblSuccess.setText("Logging Successfully!");
            changeWindow();
        } else {
            lblIncorrect.setText("Incorrect User name or Password!");
        }

    }

    private void changeWindow() {
        try {
            Stage stage = (Stage) txtUserName.getScene().getWindow();
            Parent root = FXMLLoader.load(this.getClass().getResource("../view/ChatRoomForm.fxml"));

            stage.setScene(new Scene(root, 311, 510));
            for (User ReqUser : users) {
                stage.setTitle(ReqUser.userName + "");
            }
            stage.setOnCloseRequest(event -> {
                System.exit(0);
            });
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void passwordFieldValidationOnAction(KeyEvent keyEvent) {
        Object response = ValidationUtil.validateJFXPasswordField(map1, btnLogin);
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof JFXPasswordField) {
                JFXPasswordField errorText = (JFXPasswordField) response;
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

    public void textFieldValidationOnAction(KeyEvent keyEvent) {
        Object response = ValidationUtil.validateJFXTextFields(map, btnLogin);
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof JFXTextField) {
                JFXTextField errorText = (JFXTextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {

            }
        }
    }

    public void lblSignup(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../view/SignupForm.fxml");
        Parent load = FXMLLoader.load(resource);
        loginContext.getChildren().clear();
        loginContext.getChildren().add(load);
    }
}
