package server.view;

import client.view.DrawCanvas;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DrawFrame extends JFrame{
    private int x1,y1,x2,y2;
    private BufferedImage image = new BufferedImage(1024,768,BufferedImage.TYPE_INT_BGR);

    private Graphics2D graphics2D= (Graphics2D) image.getGraphics();



    private JMenuBar menu;
    private JMenu tool;
    private JMenu shape;
    private JMenu brushSizeBar;
    private JMenuItem slim;
    private JMenuItem medium;
    private JMenuItem thick;
    private JMenu file;
    private JMenuItem save;
    private JMenuItem load;
    private JMenuItem quickLoad;


    private JPanel panel;
    private JTextArea onlinePeople;
    private JTextArea message;
    private JTextArea input;
    private JButton send;


    private JButton text;
    private JButton saveButton;
    private JButton loadButton;
    private JButton brush;
    private JButton eraser;
    private JButton clear;
    private JToolBar toolBar;
    private JButton joinChatRoom;
    private JButton foreColorSet;
    private JButton backColorSet;



    private DrawCanvas canvas = new DrawCanvas();
    private Color forecolor = Color.BLACK;
    private Color backcolor = Color.WHITE;



    private boolean isEraser = false;
    private boolean isBrush = false;
    private boolean isText = false;



    public DrawFrame(){
        super();
        setTitle("Online Draw Canvas");
        setBounds(0,0,1024,768);
        setResizable(true);

        init();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }


    public void init(){


        panel = new JPanel(new FlowLayout());
        panel.setPreferredSize(new Dimension(200,100));
        panel.setSize(200,768);
        onlinePeople = new JTextArea();
        onlinePeople.setPreferredSize(new Dimension(199,99));
        onlinePeople.setEditable(false);
        message = new JTextArea();
        message.setPreferredSize(new Dimension(199,400));
        message.setEditable(false);
        input = new JTextArea();
        input.setPreferredSize(new Dimension(199,99));
        send = new JButton("send");


        panel.add(onlinePeople,BorderLayout.EAST);
        panel.add(message,BorderLayout.EAST);
        panel.add(input,BorderLayout.EAST);
        panel.add(send,BorderLayout.AFTER_LINE_ENDS);
        panel.setBackground(Color.GRAY);

        getContentPane().add(panel, BorderLayout.EAST);



        // menu part
        menu = new JMenuBar();
        file = new JMenu("File");
        save = new JMenuItem("Save");
        load = new JMenuItem("Load");
        quickLoad = new JMenuItem("Quick Load");
        file.add(save);
        file.add(load);
        file.add(quickLoad);
        tool = new JMenu("Tool");
        shape = new JMenu("Shape");
        brushSizeBar = new JMenu("BrushSize");
        menu.add(file);
        menu.add(tool);
        tool.add(shape);
        tool.add(brushSizeBar);
        slim = new JMenuItem("Slim");
        medium  = new JMenuItem("Medium");
        thick = new JMenuItem("Thick");
        brushSizeBar.add(slim);
        brushSizeBar.add(medium);
        brushSizeBar.add(thick);
        setJMenuBar(menu);



        //button part
        brush = new JButton("Brush");
        eraser = new JButton("Eraser");
        text = new JButton("text");
        clear = new JButton("Clear");
        joinChatRoom = new JButton("joinChatRoom");
        foreColorSet = new JButton("ForeColorSet");
        backColorSet = new JButton("BackColorSet");
        toolBar = new JToolBar();
        toolBar.add(brush);
        toolBar.add(eraser);
        toolBar.add(text);
        toolBar.add(clear);
        toolBar.add(joinChatRoom);
        toolBar.add(foreColorSet);
        toolBar.add(backColorSet);
        getContentPane().add(toolBar, BorderLayout.NORTH);



        // color part
        graphics2D.setColor(backcolor);
        graphics2D.fillRect(0, 0, 1024, 768);
        graphics2D.setColor(forecolor);
        canvas.setBackground(backcolor);
        canvas.setImage(image);
        canvas.paint(graphics2D);
        getContentPane().add(canvas, BorderLayout.CENTER);
        addListener();

    }


    public void addListener(){
        //preparing
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x1 = e.getX();
                y1 = e.getY();

            }


        });
        //text filed








        //brush listener
        canvas.addMouseMotionListener(new MouseMotionAdapter(){
            @Override
            public void mouseDragged(MouseEvent e){
                if(isBrush){
                    x2 = e.getX();
                    y2 = e.getY();
                    graphics2D.setColor(forecolor);
                    graphics2D.drawLine(x1,y1,x2,y2);
                }
                if(isEraser){
                    x2 = e.getX();
                    y2 = e.getY();
                    graphics2D.setColor(backcolor);
                    graphics2D.drawLine(x1,y1,x2,y2);
                }
                if(isText){
                    JTextField ta = new JTextField(1);
                    getContentPane().add(ta, BorderLayout.CENTER);
                    canvas.repaint();
                }
                x1 = e.getX();
                y1 = e.getY();
                canvas.repaint();

            }
        });


        //eraser button listener
        eraser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isEraser = true;
                isBrush = false;
                isText = false;
            }
        });

        //brush button listener
        brush.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                isBrush = true;
                isEraser = false;
                isText = false;
            }
        });


        //text button listener
        text.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                isBrush = false;
                isEraser = false;
                isText = true;
            }
        });

        //clear the canvas
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphics2D.setColor(backcolor);
                graphics2D.fillRect(0, 0, 1024, 768);
                graphics2D.setColor(forecolor);
                canvas.repaint();
            }
        });



        //slim size
        slim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphics2D.setStroke(new BasicStroke(1));
            }
        });

        //medium size
        medium.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphics2D.setStroke(new BasicStroke(5));
            }
        });

        //thick size
        thick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphics2D.setStroke(new BasicStroke(10));
            }
        });

        //color set
        foreColorSet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color frColor = JColorChooser.showDialog(DrawFrame.this, "Fore Color", Color.BLACK);
                if (!(frColor == null)) {
                    forecolor = frColor;
                }
                graphics2D.setColor(forecolor);
                canvas.repaint();
            }
        });
        backColorSet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color bcColor = JColorChooser.showDialog(DrawFrame.this, "Back Color", Color.WHITE);
                if(!(bcColor == null)){
                    backcolor = bcColor;
                }
                graphics2D.setColor(backcolor);
                graphics2D.fillRect(0,0,1024,768);
                graphics2D.setColor(forecolor);
                canvas.repaint();
            }
        });


        //file propose
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter jpgFilter = new FileNameExtensionFilter(".png", "png");
                chooser.setFileFilter(jpgFilter);
                int returnVal = chooser.showSaveDialog(DrawFrame.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    String selectPath = chooser.getSelectedFile().getPath() + ".png";
                    try {
                        ImageIO.write(image, "png", new File(selectPath));
                        File f = new File(selectPath);
                        if (f.exists()) {
                            JOptionPane.showMessageDialog(DrawFrame.this, "save successfully", "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        load.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter jpgFilter = new FileNameExtensionFilter(".png", "png");
                chooser.setFileFilter(jpgFilter);
                int returnVal = chooser.showOpenDialog(DrawFrame.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File path = chooser.getSelectedFile();
                    Image tempImage = null;
                    try {
                        tempImage = ImageIO.read(path);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    tempImage = tempImage.getScaledInstance(824,768,Image.SCALE_SMOOTH);
                    graphics2D.drawImage(tempImage,0,0,null);
                    canvas.repaint();
                    System.out.println(path);
                }
            }
        });


    }




    public static void main(String[] args) {
        // TODO Auto-generated method stub
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                DrawFrame test = new DrawFrame();


            }

        });
    }

    }
