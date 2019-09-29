package server.view;
import java.net.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.*;


import java.io.*;


public class Server {

    private static HashMap<String,String> hash;


    public HashMap<String, String> getHash() {
        return hash;
    }



    public void setHash(HashMap<String, String> hash) {
        this.hash = hash;
    }



    public static void main(String args[]){

        hash = new HashMap<String,String>();
        ServerSocket listenSocket = null;
        Socket client = null;
        boolean run = true;
        int port = 4333;
        System.out.println("I am waiting for connection");
        try {
            listenSocket = new ServerSocket(4333);
            int i=0;

            //always waiting for connection
            while(run) {

                client = listenSocket.accept();
                i++;
                System.out.println("Client conection number " + i + " accepted:");

                serverClientThread clientThread = new serverClientThread(client, hash);
                clientThread.start();


            }
        }
        catch(SocketException e) {
            e.printStackTrace();

        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        finally
        {
            if(listenSocket != null)
            {
                try
                {
                    listenSocket.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }

    }

}