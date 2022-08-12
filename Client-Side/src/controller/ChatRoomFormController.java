/**
 * @author : Udyogi Siphara
 * Project Name: Chat-Room
 * Date        : 8/6/2022
 * Time        : 1:21 PM
 */

package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.User;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.rmi.RemoteException;
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
    public AnchorPane txtDownPane;

    public static ArrayList<User> users = SignupFormController.users;


    Socket socket;
    BufferedReader reader;
    PrintWriter writer;

    private FileChooser fileChooser;
    private File filePath;

    public void initialize(){
        txtDownPane.setVisible(false);

        for(Node text : emojiList.getChildren()){
            text.setOnMouseClicked(event -> {
                txtMsgFiled.setText(txtMsgFiled.getText()+" "+((Text)text).getText());
                emojiList.setVisible(false);
            });
        }

        scrollPane.vvalueProperty().bind(chatBox.heightProperty());


        for (User ReqUser : users) {
            userName.setText(ReqUser.userName + "");
        }
        try {
            socket = new Socket("localhost", 5009);
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


                StringBuilder fullMsg = new StringBuilder();
                for (int i = 1; i < tokens.length; i++) {
                    fullMsg.append(tokens[i]);
                }

                String[] msgToAr = msg.split(" ");
                String st = "";
                for (int i = 0; i < msgToAr.length - 1; i++) {
                    st += msgToAr[i + 1] + " ";
                }
//======================================================================


                Text text = new Text(st);
                String firstChars = "";
                if (st.length() > 3) {
                    firstChars = st.substring(0, 3);

                }

                if (firstChars.equalsIgnoreCase("img")) {
                    //Code For Images

                    st = st.substring(3, st.length() - 1);



                    File file = new File(st);
                    Image image = new Image(file.toURI().toString());

                    ImageView imageView = new ImageView(image);

                    imageView.setFitHeight(150);
                    imageView.setFitWidth(150);


                    HBox hBox = new HBox(10);
                    hBox.setAlignment(Pos.BOTTOM_RIGHT);


                    if (!cmd.equalsIgnoreCase(userName.getText())) {

                        chatBox.setAlignment(Pos.TOP_LEFT);
                        hBox.setAlignment(Pos.CENTER_LEFT);


                        Text text1=new Text("  "+cmd+" :");
                        hBox.getChildren().add(text1);
                        hBox.getChildren().add(imageView);

                    } else {
                        hBox.setAlignment(Pos.BOTTOM_RIGHT);
                        hBox.getChildren().add(imageView);
                        Text text1=new Text(": Me ");
                        hBox.getChildren().add(text1);

                    }

                    Platform.runLater(() -> chatBox.getChildren().addAll(hBox));


                } else {
                    //Code For Texts
                    text.setFill(Color.BLACK);
                    text.getStyleClass().add("message");
                    TextFlow tempFlow = new TextFlow();

                    if (!cmd.equalsIgnoreCase(userName.getText() + ":")) {
                        Text txtName = new Text(cmd + "\n");
                        txtName.getStyleClass().add("txtName");
                        tempFlow.getChildren().add(txtName);
                    }

                    tempFlow.getChildren().add(text);
                    tempFlow.setMaxWidth(200); //200

                    TextFlow flow = new TextFlow(tempFlow);

                    HBox hBox = new HBox(15); //12

                    // =================================================


                    if (!cmd.equalsIgnoreCase(userName.getText() + ":")) {

                        tempFlow.getStyleClass().add("tempFlowFlipped");
                        flow.getStyleClass().add("textFlowFlipped");
                        chatBox.setAlignment(Pos.TOP_LEFT);
                        hBox.setAlignment(Pos.CENTER_LEFT);
                        hBox.getChildren().add(flow);

                    } else {
                        text.setFill(Color.BLACK);
                        tempFlow.getStyleClass().add("tempFlow");
                        flow.getStyleClass().add("textFlow");
                        hBox.setAlignment(Pos.BOTTOM_RIGHT);
                        hBox.getChildren().add(flow);
                    }
                    hBox.getStyleClass().add("hbox");
                    Platform.runLater(() -> chatBox.getChildren().addAll(hBox));
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

            txtMsgFiled.setText("");
            if (message.equalsIgnoreCase("By") || message.equalsIgnoreCase("Bye") || message.equalsIgnoreCase("by") || message.equalsIgnoreCase("bye")){
                Stage stage = (Stage) txtMsgFiled.getScene().getWindow();
                stage.close();
            }
        }

    }

    public void handleSendEvent(MouseEvent mouseEvent) throws IOException {
        String msg = txtMsgFiled.getText();
        writer.println(userName.getText() + ": " + txtMsgFiled.getText());

        txtMsgFiled.clear();
        if (msg.equalsIgnoreCase("Bye") || (msg.equalsIgnoreCase("logout"))) {
            Stage stage = (Stage) txtMsgFiled.getScene().getWindow();
            stage.close();
        }
        
    }

    public void btnEmojiOnAction(ActionEvent actionEvent) {
        if(emojiList.isVisible()){

            emojiList.setVisible(false);
        }else {
            emojiList.setVisible(true);
        }
    }

    public void imgChooseImageSend(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
        this.filePath = fileChooser.showOpenDialog(stage);
        writer.println(userName.getText() + " " + "img" + filePath.getPath());
    }

    public void lblNewMember(MouseEvent mouseEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"))));
        stage.setResizable(false);
        stage.getIcons().add(new Image("assets/image/massenger.png"));
        stage.setTitle("Messenger");
        stage.centerOnScreen();
        stage.show();
    }

    public void upDownClickOnAction(MouseEvent mouseEvent) {
        txtDownPane.setVisible(true);
    }

    public void downPaneCloseOnAction(MouseEvent mouseEvent) {
        txtDownPane.setVisible(false);
    }
}
