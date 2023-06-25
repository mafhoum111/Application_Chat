package com.example.mafhoumfz;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.Socket;

public class Scene2Controller {
    @FXML
    private TextField hostid;
    @FXML
    private TextField mymessageid;
    @FXML
    private TextField portid;
    @FXML
    private ListView  testview;
    PrintWriter pw;
    @FXML
    protected void onConnect() throws IOException {
       String host = hostid.getText();
       int port = Integer.parseInt(portid.getText());
        Socket s = new Socket(host,port);
        InputStream is = s.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        OutputStream os = s.getOutputStream();
        String Ip = s.getRemoteSocketAddress().toString();
        pw = new PrintWriter(os,true);

        new Thread(()->{
            while (true){
                try {
                    String reponse = br.readLine();
                    Platform.runLater(()->{
                        testview.getItems().add(reponse);
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                    //throw new RuntimeException(e);
                }
            }
        }).start();
    }
            @FXML
    public void onSubmit(){
        String message = mymessageid.getText();
                pw.println(message);
            }


}
