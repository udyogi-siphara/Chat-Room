/**
 * @author : Udyogi Siphara
 * Project Name: Chat-Room
 * Date        : 8/6/2022
 * Time        : 1:21 PM
 */

package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.User;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;


public class ChatRoomFormController extends Thread {

    public Circle proPic;
    public Label userName;
    public JFXTextArea txtMsgBox;
    public TextField txtMsgFiled;
    public AnchorPane chatRoomContext;
    public JFXButton btnEmoji;
    public TextFlow emojiList;
    public ScrollPane scrollPane;
    public VBox chatBox;

    public static ArrayList<User> users = SignupFormController.users;
    Socket socket;
    BufferedReader reader;
    PrintWriter writer;

    public void initialize(){
        for (User ReqUser : users) {
            userName.setText(ReqUser.userName + "");
        }
        try {
            socket = new Socket("localhost", 5000);
            System.out.println("Socket is connected with server!");
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            this.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        try {
            while (true) {

                String msg = reader.readLine();
                String[] tokens = msg.split(" ");
                String cmd = tokens[0];
                System.out.println(cmd);
                StringBuilder fullMsg = new StringBuilder();
                for (int i = 1; i < tokens.length; i++) {
                    fullMsg.append(tokens[i]);
                }
                System.out.println(fullMsg);

                System.out.println(msg);
                System.out.println("cmd="+cmd+"-----"+"UserName"+userName.getText());
                System.out.println(msg);
                if(!cmd.equalsIgnoreCase(userName.getText()+":")){
                    txtMsgBox.appendText(msg + "\n");
                    System.out.println(msg);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void sendMessageByKey(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)){
            String message = txtMsgFiled.getText();
            writer.println(userName.getText() + ": " + txtMsgFiled.getText());
            txtMsgBox.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
            txtMsgBox.appendText("Me :"+message+"\n");
            txtMsgFiled.setText("");
            if (message.equalsIgnoreCase("By") || message.equalsIgnoreCase("Bye") || message.equalsIgnoreCase("by") || message.equalsIgnoreCase("bye")){
                System.exit(0);
                Stage stage = (Stage) txtMsgFiled.getScene().getWindow();
                stage.close();
            }
        }

    }

    public void handleSendEvent(MouseEvent mouseEvent) throws IOException {
        String message = txtMsgFiled.getText();
        writer.println(userName.getText() + ": " + txtMsgFiled.getText());
        txtMsgBox.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        txtMsgBox.appendText("Me :"+message+"\n");
        txtMsgFiled.setText("");
        if (message.equalsIgnoreCase("By") || message.equalsIgnoreCase("Bye") || message.equalsIgnoreCase("by") || message.equalsIgnoreCase("bye")){
                System.exit(0);
                Stage stage = (Stage) txtMsgFiled.getScene().getWindow();
                stage.close();
        }
    }

    public void btnEmojiOnAction(ActionEvent actionEvent) {
    }
}
