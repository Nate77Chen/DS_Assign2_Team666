package client.view;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

public class Login {
    private JFrame frame;
    private JLabel usernameLabel;
    private  JTextField userName;

    private Socket socket;
    private DataOutputStream writer;
    private DataInputStream reader;

    public Login(){
        initialize();
    }

    private void initialize() {

        frame = new JFrame();

        frame.setBounds(100, 150, 400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        usernameLabel = new JLabel("username");
        usernameLabel.setBounds(80, 95, 61, 16);
        frame.getContentPane().add(usernameLabel);


        userName = new JTextField();
        userName.setBounds(200, 95, 150, 30);
        frame.getContentPane().add(userName);
        userName.setColumns(10);




        JButton button1 = new JButton("login");
        button1.setBounds(140, 150, 117, 29);
        frame.getContentPane().add(button1);

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                //Try to change the login page and save the username in a hash table I guess.
                if (userName.getText().equals("apple")) {

                    try {
                        Socket socket = new Socket("localhost", 4333 );
                        DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
                        DataInputStream reader = new DataInputStream(socket.getInputStream());
                        JOptionPane.showMessageDialog(frame, "login successfully");

                        DrawFrame drawframe = new DrawFrame(socket,writer,reader);
                        drawframe.setVisible(true);






                    }
                    catch(ConnectException e2) {
                        System.out.println("There is a connection error, please check your port number! "
                                + "and start again");
                        System.exit(0);

                    }catch (IOException e3){
                        System.out.println("Some error");
                    }


                } else {
                    JOptionPane.showMessageDialog(frame, "This username is already in used");
                    userName.setText("");
                }
            }
        });
    }

    //This part suppose to receive message from server, but still need to be evaluated.

    public void draw(DrawFrame drawframe){
        String message;
        try {
            while ((message = reader.readUTF()) != null) {
                System.out.println(message);
                drawframe.run(message);
            }
        }catch (Exception e){
            System.out.println("error");
        }
    }






    public static void main(String[] args) {
        Login loginpage = new Login();
        loginpage.frame.setVisible(true);
        loginpage.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //not formally login





    }

}

