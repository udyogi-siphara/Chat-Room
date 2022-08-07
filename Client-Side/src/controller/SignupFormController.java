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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

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

    public void btnSignupOnAction(ActionEvent actionEvent) {
    }

    public void textFieldValidationOnAction(KeyEvent keyEvent) {
    }

    public void eyeClickOnAction(MouseEvent mouseEvent) {
    }

    public void passwordFieldValidationOnAction(KeyEvent keyEvent) {
    }
}
