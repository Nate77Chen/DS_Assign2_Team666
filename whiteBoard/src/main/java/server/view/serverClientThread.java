package server.view;

import java.net.*;
import java.util.HashMap;
import java.io.*;
import org.json.JSONObject;
import org.json.JSONTokener;


public class serverClientThread extends Thread {

    private Socket serverClient;
    //    private HashMap<String,String> map;
    private DataInputStream reader;
    private DataOutputStream writer;
    private String dict_name;

    public serverClientThread(Socket inSocket, HashMap<String,String> hash) {
        super();
        this.serverClient=inSocket;
        try {
            reader=new DataInputStream(serverClient.getInputStream());
            writer = new DataOutputStream(serverClient.getOutputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void run() {
        System.out.println("Remote Port: " + serverClient.getPort());
        System.out.println("Remote Hostname: " + serverClient.getInetAddress().getHostName());
        System.out.println("Local Port: " + serverClient.getLocalPort());

        String message;
        try {

            while ((message = reader.readUTF()) != null) {
                System.out.println("client: "+message);

                //This should send the message back to each client, however, how to design the message still need to be worked on
//                writer.writeUTF(message);


            }
        } catch (IOException e1) {
            System.out.println("Some mistakes here");

        }
    }






}