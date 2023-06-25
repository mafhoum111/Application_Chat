//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.mafhoumfz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ChatWithServer extends Thread {
    private int ClientNumber;
    private List<Communication> clientConnecter = new ArrayList();

    public ChatWithServer() {
    }

    public static void main(String[] args) {
        (new ChatWithServer()).start();
    }

    public void run() {
        try {
            ServerSocket ss = new ServerSocket(2001);
            System.out.println("le serveur essai de demarrer ....");

            while(true) {
                Socket s = ss.accept();
                ++this.ClientNumber;
                Communication newCommunication = new Communication(s, this.ClientNumber);
                this.clientConnecter.add(newCommunication);
                newCommunication.start();
            }
        } catch (IOException var4) {
            var4.printStackTrace();
        }
    }

    public class Communication extends Thread {
        private Socket s;
        private int ClientNumber;

        Communication(Socket s, int ClientNumber) {
            this.s = s;
            this.ClientNumber = ClientNumber;
        }

        public void run() {
            try {
                InputStream is = this.s.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                OutputStream os = this.s.getOutputStream();
                String Ip = this.s.getRemoteSocketAddress().toString();
                System.out.println("le client numero: " + this.ClientNumber + "et so Ip: " + Ip);
                PrintWriter pw = new PrintWriter(os, true);
                pw.println("Vous etes le client: " + this.ClientNumber);
                pw.println("CHAT : ");

                while(true) {
                    while(true) {
                        String UserRequest = br.readLine();
                        if (UserRequest.contains("=>")) {
                            String[] usermessage = UserRequest.split("=>");
                            if (usermessage.length == 2) {
                                String msg = usermessage[1];
                                int numeroClient = Integer.parseInt(usermessage[0]);
                                this.Send(msg, this.s, numeroClient);
                            }
                        } else {
                            this.Send(UserRequest, this.s, -1);
                        }
                    }
                }
            } catch (IOException var11) {
                var11.printStackTrace();
            }
        }

        void Send(String UserRequest, Socket socket, int nbre) throws IOException {
            Iterator var4 = ChatWithServer.this.clientConnecter.iterator();

            while(true) {
                Communication client;
                do {
                    do {
                        if (!var4.hasNext()) {
                            return;
                        }

                        client = (Communication)var4.next();
                    } while(client.s == socket);
                } while(client.ClientNumber != nbre && nbre != -1);

                PrintWriter pw = new PrintWriter(client.s.getOutputStream(), true);
                pw.println(UserRequest);
            }
        }
    }
}
