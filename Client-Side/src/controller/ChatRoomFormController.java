/**
 * @author : Udyogi Siphara
 * Project Name: Chat-Room
 * Date        : 8/6/2022
 * Time        : 1:21 PM
 */

package controller;

import com.jfoenix.controls.JFXTextArea;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class ChatRoomFormController {

    public Circle proPic;
    public Label userName;
    public Pane profile;
    public Pane chat;
    public JFXTextArea txtChatbox;
    public TextField txtmsg;

    public void sendMessageByKey(KeyEvent keyEvent) {
    }

    public void handleSendEvent(MouseEvent mouseEvent) {
    }
}
