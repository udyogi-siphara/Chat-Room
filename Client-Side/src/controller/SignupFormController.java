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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import util.ValidationUtil;

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

    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();
    Pattern NamePattern = Pattern.compile("^[A-z]{50,}$");
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
}
